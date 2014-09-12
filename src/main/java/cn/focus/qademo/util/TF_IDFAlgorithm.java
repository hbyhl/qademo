package cn.focus.qademo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.focus.qademo.service.DBService;
@Component
public class TF_IDFAlgorithm {
    private static Map<Integer, ArrayList<String>> questionsMap = new HashMap<Integer, ArrayList<String>>();
    //问题分好词列表
    private static ArrayList<ArrayList<String>> cutwordsList = new ArrayList<ArrayList<String>>();
   //原始问题，包含问题id和问题
    private static ArrayList<ArrayList<String>> sourceData = null;
    @Autowired
    private DBService DBService;
    private static int questionCount;

    private final static int BUILD_ID = 2504;

    public static void main(String[] args) {
        System.out.println("初始化成功");
    }
    
    private void init(Integer build_id,Integer type){
        cutwordsList.clear();
        questionsMap.clear();
      sourceData = DBService.query1(build_id,type);
      ArrayList<String> idList = sourceData.get(0);
      ArrayList<String> questionList = sourceData.get(1);
      setQuestionCount(questionList.size());
      for (int i = 0; i < questionCount; i++) {
          ArrayList<String> cutwords = WordSeg.parse(questionList.get(i));
          // 查看分好词的问题
          System.out.println(cutwords);
          // 将分好的分词序列放入list中
          cutwordsList.add(cutwords);
          questionsMap.put(Integer.valueOf(idList.get(i)), cutwords);

      }
  }
    public static HashMap<String, Integer> intTF(ArrayList<String> cutwords) {
        HashMap<String, Integer> resTF = new HashMap<String, Integer>();
        for (String word : cutwords) {
            if (resTF.get(word) == null) {
                resTF.put(word, 1);
               //System.out.println(word);
            } else {
                resTF.put(word, resTF.get(word) + 1);
               // System.out.println(word.toString());
            }
        }
        return resTF;
    }

    public static HashMap<String, Float> floatTF(HashMap<String, Integer> intTFCutwords, Float wordLen) {
        ArrayList<String> cutwords = new ArrayList(intTFCutwords.keySet());
        HashMap<String, Float> resTF = new HashMap<String, Float>();
        for (String word : cutwords) {
            resTF.put(word, Float.parseFloat(intTFCutwords.get(word).toString()) / wordLen);
        }
        return resTF;
    }

    public static HashMap<String, Float> tf(ArrayList<String> cutwords) {
        HashMap<String, Float> result = floatTF(intTF(cutwords), Float.valueOf(cutwords.size()));
        return result;
    }

    // cutwords 不可重复
    public static HashMap<String, Integer> intIDF(ArrayList<String> cutwords) {
        HashMap<String, Integer> resIDF = new HashMap<String, Integer>();
        // 去掉重复词
        for (String word : cutwords) {
            Iterator questionMapIter = questionsMap.entrySet().iterator();
            while (questionMapIter.hasNext()) {
                Map.Entry entry = (Map.Entry) questionMapIter.next();
                ArrayList<String> question = (ArrayList<String>) entry.getValue();
                if (question.contains(word)) {
                    if (resIDF.get(word) == null) {
                        resIDF.put(word, 1);
                    } else {
                        resIDF.put(word, resIDF.get(word) + 1);
                    }
                }
            }
        }
        return resIDF;
    }

    public static HashMap<String, Float> floatIDF(HashMap<String, Integer> cutwords) {
        // 去掉重复词
        ArrayList<String> filteredWords = new ArrayList<String>(cutwords.keySet());
        HashMap<String, Float> floatIDF = new HashMap<String, Float>();
        for (String word : filteredWords) {
            floatIDF.put(word, (float) Math.log(questionCount / (1 + cutwords.get(word))));
        }
        return floatIDF;
    }

    public static HashMap<String, Float> idf(ArrayList<String> cutwords) {
        HashMap<String, Float> result = floatIDF(intIDF(cutwords));
        return result;
    }

    // cutwords可重复
    public static HashMap<String, Float> tf_idf(ArrayList<String> cutwords) {
        HashMap<String, Float> tf = tf(cutwords);
        ArrayList<String> tfList = new ArrayList<String>(tf.keySet());
        HashMap<String, Float> idf = idf(tfList);
        ArrayList<String> idfList = new ArrayList<String>(idf.keySet());
        HashMap<String, Float> tf_idf = new HashMap<String, Float>();
        if (tfList.size() == idfList.size()) {
            for (int i = 0; i < tfList.size(); i++) {
                if (tfList.get(i).equals(idfList.get(i))) {
                    tf_idf.put(tfList.get(i), tf.get(tfList.get(i)) * idf.get(idfList.get(i)));
                } else {
                    System.out.println("算法异常");
                }
            }
        } else {
            System.out.println("算法异常");
        }
        return tf_idf;
    }

    public HashMap<Integer, HashMap<String, Float>> BUILD_ID_TFIDF_result(Integer build_id,Integer type) {
        init(build_id, type);
        HashMap<Integer, HashMap<String, Float>> BUILD_ID_TFIDF_result = new HashMap<Integer, HashMap<String, Float>>();
        for (int i = 0; i < cutwordsList.size(); i++) {
            BUILD_ID_TFIDF_result.put(Integer.valueOf(sourceData.get(0).get(i)), tf_idf(cutwordsList.get(i)));
        }
        return BUILD_ID_TFIDF_result;
    }

    public static HashMap<String, Float> tf_idf(String question) {
        ArrayList<String> cutwords = WordSeg.parse(question);
        return tf_idf(cutwords);
    }

    public static void setQuestionCount(int questionCount) {
        TF_IDFAlgorithm.questionCount = questionCount;
    }

}
