package cn.focus.qademo.service;

import java.lang.reflect.Array;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.recognition.NatureRecognition;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.util.FilterModifWord;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cn.focus.qademo.dao.QuestionDAO;
import cn.focus.qademo.dao.QuestionWhiteWordDAO;
import cn.focus.qademo.dao.WhiteWordDAO;
import cn.focus.qademo.model.Question;
import cn.focus.qademo.model.QuestionWhiteWord;
import cn.focus.qademo.model.WhiteWord;

/**
 * @author changhuhuang@sohu-inc.com
 * @version 创建时间：2014-6-9 上午11:37:52
 */
@Service
public class WhiteWordService {
    private static Logger logger = LoggerFactory.getLogger(WhiteWordService.class);

    /** 分词库的词性分隔符 */
    private static final String NATURE_SEPERATOR = ",";

    /**
     * 白词到白词库的映射关系
     */
    private static Map<String, String> word2natures;

    @Autowired
    private WhiteWordDAO whiteWordDAO;

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private QuestionWhiteWordDAO questionWhiteWordDAO;

    private Date lastUpdatedTime = new Date(0);

    private List<Question> questions = null;

    /**
     * 初始化白词库，向切词器添加用户词典
     */
    @PostConstruct
    @Scheduled(cron = "*/30 * * * * ?")
    public void reloadWords() {
        if (!hasNewWords()) {
            return;
        }

        // 将切词库中原有的白词清除
        if (word2natures != null) {
            for (String word : word2natures.keySet()) {
                UserDefineLibrary.removeWord(word);
            }
        }

        List<WhiteWord> whiteWords = whiteWordDAO.getWords();
        word2natures = new ConcurrentHashMap<String, String>();
        // 白词分类，多个白词库可能包含相同的白词, 多个分类用逗号分隔
        for (WhiteWord w : whiteWords) {
            String nature = word2natures.get(w.getName());
            nature = StringUtils.isBlank(nature) ? w.getParentId() + "" : nature + NATURE_SEPERATOR + w.getParentId();
            word2natures.put(w.getName(), nature);
        }
        for (Entry<String, String> en : word2natures.entrySet()) {
            UserDefineLibrary.insertWord(en.getKey(), en.getValue(), 10000);
        }
        // 触发初始化操作，加快首次访问的速度
        IndexAnalysis.parse("初始化");
    }

    /**
     * 白词库是否有更新
     */
    private boolean hasNewWords() {
        Date update = whiteWordDAO.hasUpdate(lastUpdatedTime);
        if (update == null) {
            return false;
        }
        lastUpdatedTime = update;
        return true;
    }

    /**
     * 对问答内容进行分词并归类
     * 
     * @param q
     */
    public void analyseQuestion(Question q) {
        try {
            Set<Integer> wordIds = parse(q.getQuestion());
            if (CollectionUtils.isEmpty(wordIds)) {
                return;
            }

            List<QuestionWhiteWord> words = new ArrayList<QuestionWhiteWord>(wordIds.size());
            for (Integer wordId : wordIds) {
                QuestionWhiteWord w = new QuestionWhiteWord();
                w.setQuestionId(q.getId());
                w.setWordId(wordId);
                w.setCreateTime(new Date());
                words.add(w);
            }

            whiteWordDAO.saveQuestionWhiteWord(words);
        } catch (Exception e) {
            logger.error("分词失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 对问答内容进行分词，并从词性中提取白词类别id.非空则表示有意义
     * 
     * @param content
     * @return
     */
    public Set<Integer> parse(String content) {
        Set<Integer> categoryIds = new HashSet<Integer>();

        List<Term> terms = IndexAnalysis.parse(content);
        // 修正词性，用户自定义词典的词性优先
        new NatureRecognition(terms).recognition();
        FilterModifWord.modifResult(terms);

        System.out.println(word2natures);
        // 从词性中提取白词分类的id
        for (Term t : terms) {
            // String idStr = t.getNatrue().natureStr;
            String idStr = t.getNatureStr();
            System.out.print(idStr + "/");
            System.out.println(t.getRealName());

            if (!word2natures.values().contains(idStr)) {
                continue;
            }

            for (String id : idStr.split(NATURE_SEPERATOR)) {
                Integer categoryId;
                try {
                    categoryId = Integer.valueOf(id);
                    categoryIds.add(categoryId);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return categoryIds;
    }

    /**
     * 添加白词，对上次白词库更新以后提出的问题进行关联，存入数据库
     * 
     * @param whiteWord , type , lastUpdateDateOfQuestion
     * @return
     */
    /*
     * public void save(WhiteWord whiteWord, int type, Date lastUpdateDateOfQuestion) { long wwid =
     * whiteWordDAO.save(whiteWord); List<Question> questions = questionDAO.getList(type, lastUpdateDateOfQuestion);
     * List<QuestionWhiteWord> questionWhiteWords = new ArrayList<QuestionWhiteWord>(); for (Question question :
     * questions) { if (question.getQuestion() != null && question.getQuestion().contains(whiteWord.getName())) {
     * QuestionWhiteWord questionWhiteWord = new QuestionWhiteWord(); questionWhiteWord.setCityId(question.getCityId());
     * questionWhiteWord.setQuestionId(question.getId()); questionWhiteWord.setWordId((int) wwid); // status/cityName
     * questionWhiteWords.add(questionWhiteWord); } } whiteWordDAO.saveQuestionWhiteWord(questionWhiteWords); }
     */

    public boolean save(String word, int type) {

        boolean success = false;
        List<WhiteWord> whiteWordList = whiteWordDAO.getWordByName(word);
        WhiteWord whiteWord;
        int wwid;
        if (whiteWordList == null || whiteWordList.size() == 0) {
            whiteWord = new WhiteWord();
            whiteWord.setName(word);
            int type_key[] = { 0, 442, 391, 459, 332, 460, 346, 367, 434, 406, 461 };
            whiteWord.setParentId(type_key[type]);
            wwid = whiteWordDAO.save(whiteWord);
            success = true ;
        } else if (whiteWordList.size() == 1 && whiteWordList.get(0).getId() == 0) {
            WhiteWord ww = new WhiteWord();
            ww.setName(whiteWordList.get(0).getName());
            ww.setParentId(whiteWordList.get(0).getId());
            wwid = whiteWordDAO.save(ww);
            success = true;
        } else {
         
            return success;
        }

       

        if (questions == null) {
            questions = questionDAO.getList();
        }
        List<QuestionWhiteWord> questionWhiteWords = new ArrayList<QuestionWhiteWord>();
        for (Question question : questions) {
            if (question.getQuestion() != null && question.getQuestion().contains(word)) {
                QuestionWhiteWord questionWhiteWord = new QuestionWhiteWord();
                questionWhiteWord.setCityId(question.getCityId());
                questionWhiteWord.setQuestionId(question.getId());
                questionWhiteWord.setWordId(wwid);
                // status/cityName
                questionWhiteWords.add(questionWhiteWord);
            }
        }
        whiteWordDAO.saveQuestionWhiteWord(questionWhiteWords);
        
        return success;
    }

    public void save(String[] wordlist, int type) {
        for(int i = 0;i<wordlist.length;i++){
            if(!save(wordlist[i], type)){
                wordlist[i] = null;
            } 
        }
    }

    public void praseQuestions() {
        List<Question> questions = questionDAO.getTypedList();
        for (Question question : questions) {
            analyseQuestion(question);
        }
    }

    public void addDBData() {
        // TODO Auto-generated method stub
        List<Question> questions = questionDAO.getQuestionsLabeled();
        for (int i = 0; i < questions.size(); i++) {
            analyseQuestion(questions.get(i));
        }
        System.out.println("更新数据结束，共插入新问题条目：" + questions.size());

    }
}
