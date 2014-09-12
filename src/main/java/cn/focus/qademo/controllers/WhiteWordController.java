package cn.focus.qademo.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.paoding.rose.web.annotation.HttpFeatures;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import net.paoding.rose.web.var.Model;

import org.springframework.beans.factory.annotation.Autowired;

import cn.focus.qademo.model.Question;
import cn.focus.qademo.service.PreprocessWhiteWordService;
import cn.focus.qademo.service.QuestionService;
import cn.focus.qademo.service.WhiteWordService;
import cn.focus.qademo.util.ObjectToXMLUtil;

@Path("whiteword")
@HttpFeatures(charset = "utf-8", contentType = "application/json")
public class WhiteWordController {

    @Autowired
    private WhiteWordService whiteWordService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PreprocessWhiteWordService preprocessWhiteWordService;

    private File whiteWordFile = new File("temp/whiteworld.xml");

    @Get("question")
    public String priseQuestion(@Param("questionId") long questionId) {
        Question q = questionService.getQuestionById(questionId);
        System.out.println(q.toString());
        whiteWordService.analyseQuestion(q);
        return "index";
    }

    @Get("init")
    public String initQuestionWhiteWords() {
        whiteWordService.praseQuestions();
        return "index";
    }

    @Get("test")
    public String test() {

        return "index";
    }

    @Get("addwhiteword")
    public String whiteword(Model model) throws FileNotFoundException, IOException, Exception {
        String[][] whiteWordList = null;
        if(!whiteWordFile.exists())
        {
            whiteWordList = preprocessWhiteWordService.getWhiteWordList();
            ObjectToXMLUtil.objectXmlEncoder(whiteWordList, whiteWordFile.getAbsolutePath());
        }else{
            whiteWordList = ObjectToXMLUtil.XmlDecoderToStringArray(whiteWordFile);
        }
        addDataToModel(model,whiteWordList);
        return "whiteword.jsp";
    }

    @Get("add")
    @Post("add")
    public String addWhiteWord(Model model, @Param("policy") String[] policy, @Param("location") String[] location,
            @Param("fengshui") String[] fengshui, @Param("price") String[] price, @Param("floor") String[] floor,
            @Param("housetype") String[] housetype, @Param("facilities") String[] facilities,
            @Param("loan") String[] loan, @Param("occasion") String[] occasion, @Param("developers") String[] developers)
            throws FileNotFoundException, IOException, Exception {
        //待添加的白词
        String[][] whiteWordList = ObjectToXMLUtil.XmlDecoderToStringArray(whiteWordFile);
        //记录未添加的白词
        String[][] newArray = new String[11][PreprocessWhiteWordService.getNum];
        //将whiteWordList【i】对应类别下未添加的白词，复制到newArray【i】中
        dealWhiteWord(whiteWordList[1],policy,newArray[1],1);
        dealWhiteWord(whiteWordList[2],location,newArray[2],2);
        dealWhiteWord(whiteWordList[3],fengshui,newArray[3],3);
        dealWhiteWord(whiteWordList[4],price,newArray[4],4);
        dealWhiteWord(whiteWordList[5],floor,newArray[5],5);
        dealWhiteWord(whiteWordList[6],housetype,newArray[6],6);
        dealWhiteWord(whiteWordList[7],facilities,newArray[7],7);
        dealWhiteWord(whiteWordList[8],loan,newArray[8],8);
        dealWhiteWord(whiteWordList[9],occasion,newArray[9],9);
        dealWhiteWord(whiteWordList[10],developers,newArray[10],10);
        addDataToModel(model,newArray);
        //用未填加的白词更新待添加的白词文件
        updateFile(newArray);
        //将已添加的白词放入model，以显示在页面
        model.add("policy1", policy);
        model.add("location1", location);
        model.add("fengshui1", fengshui);
        model.add("price1", price);
        model.add("floor1", floor);
        model.add("housetype1", housetype);
        model.add("facilities1", facilities);
        model.add("loan1", loan);
        model.add("occasion1", occasion);
        model.add("developers1", developers);
        
        return "whiteword.jsp";
    }
    public void updateFile(String[][] array) throws FileNotFoundException, IOException, Exception
    {
        if(whiteWordFile.exists())
        {
            whiteWordFile.delete();
            ObjectToXMLUtil.objectXmlEncoder(array, whiteWordFile.getAbsolutePath());
        }
    }
    public void addDataToModel(Model model,String[][] whiteWordList)
    {
        model.add("policy", whiteWordList[1]);
        model.add("location", whiteWordList[2]);
        model.add("fengshui", whiteWordList[3]);
        model.add("price", whiteWordList[4]);
        model.add("floor", whiteWordList[5]);
        model.add("housetype", whiteWordList[6]);
        model.add("facilities", whiteWordList[7]);
        model.add("loan", whiteWordList[8]);
        model.add("occasion", whiteWordList[9]);
        model.add("developers", whiteWordList[10]);
    }
    public void dealWhiteWord(String[] local, String[] passed, String[] newArray, int type) {
        int i = 0;
        for (String l : local) {
            if(!hasString(l,passed))
            {
                newArray[i++] = l;
            }
        }
        whiteWordService.save(passed, type);
    }

    public boolean hasString(String str, String[] strArray) {
        for (String s : strArray) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
