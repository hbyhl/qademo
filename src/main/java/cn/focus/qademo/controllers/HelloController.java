package cn.focus.qademo.controllers;

import java.util.List;

import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.HttpFeatures;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import net.paoding.rose.web.var.Model;

import org.springframework.beans.factory.annotation.Autowired;

import cn.focus.qademo.dao.QuestionDAO;
import cn.focus.qademo.model.Constant;
import cn.focus.qademo.model.Question;
import cn.focus.qademo.service.MatchService;
import cn.focus.qademo.service.QuestionClassifierService;
import cn.focus.qademo.service.WhiteWordService;
import cn.focus.qademo.service.recommendService;

@Path("qademo")
@HttpFeatures(charset = "utf-8", contentType = "application/json")
public class HelloController {
    @Autowired
    private recommendService recommendService;

    @Autowired
    private QuestionDAO q;

    /**
     * @param args
     */

    @Autowired
    private QuestionClassifierService questionClassifierService;

    @Autowired
    private WhiteWordService whiteWordService;

    @Autowired
    private MatchService matchService;

    private int count = 0;

    // 测试方法。。如，在网址上附加quesion。如http://localhost:8080/qademo/list?question=房价是多少呢
    // 决策树
    @Get("soundUpdate")
    public String soundUpdate(Model model) {

        if (count != 0) {
            if (Constant.result != null & Constant.sm != null & Constant.question != null) {
                Constant.lastresult = Constant.result;
                Constant.lastquestion = Constant.question;
                Constant.lastsm = Constant.sm;
                model.add("result", Constant.result);
                model.add("array", Constant.sm);
                model.add("question", Constant.question);
                Constant.result = null;
                Constant.sm = null;
                Constant.question = null;
                return "soundautoanswer";
            } else {
                model.add("result", Constant.lastresult);
                model.add("array", Constant.lastsm);
                model.add("question", Constant.lastquestion);
                return "soundautoanswer";
            }
        }else{
            count++;
            return "soundautoanswer";
        }


    }

    @Post("sound")
    @Get("sound")
    public void listFromSound(Model model, @Param("question") String question) throws Exception {
        int type = (int) questionClassifierService.classifyByFre(question);
        matchService.init(2955, type);
        Constant.sm = matchService.match(question);
        // Constant.sm = recommendService.demo(question, 2955L, type);
        if (whiteWordService.parse(question).isEmpty()) {
            Constant.result = "该问题为无意义问题。";
        } else {
            Constant.result = "该问题为有意义问题。";
            Constant.result = Constant.result + "该问题划分到<" + questionClassifierService.getClassName(type) + ">类";
        }
        Constant.question = question;
    }

    @Post("list")
    @Get("list")
    public String list(Model model, @Param("question")  String question) throws Exception {

        int type = (int) questionClassifierService.classifyByFre(question);
//        matchService.init(2955, type);
//         List<Question> sm = matchService.match(question);
        List<Question> sm = recommendService.demo(question, 2955L, type);
        String result = null;
        if (whiteWordService.parse(question).isEmpty()) {
            result = "该问题为无意义问题。";
        } else {
            result = "该问题为有意义问题。";
            result = result + "该问题划分到<" + questionClassifierService.getClassName(type) + ">类";
            model.add("array", sm);
        }

        model.add("result", result);
        model.add("question", question);

        return "autoanswer";

    }

    // 测试方法。。如，在网址上附加question。如http://localhost:8080/qademo/list2?question=房价是多少呢
    // 频率方法
    @Get("list2")
    public String list2(Model model, @Param("question") @DefValue("户型如何") String question) throws Exception {
        System.out.println("薛庆元");
        int type = (int) questionClassifierService.classifyByFre(question);

        List<Question> sm = recommendService.demo(question, 2955L, type);
        model.add("array", sm);
        return "index";

    }

    // 测试方法。。如，在网址上附加question。如http://localhost:8080/qademo/list3?question=房价是多少呢
    // KNN方法
    @Get("list3")
    public String list3(Model model, @Param("question") @DefValue("户型如何") String question) throws Exception {
        System.out.println("薛庆元");
        int type = (int) questionClassifierService.classifyByKnn(question);

        List<Question> sm = recommendService.demo(question, 2955L, type);
        model.add("array", sm);

        return "index";

    }

    // 测试准确率
    @Get("test")
    public String test(@Param("question") @DefValue("房价是多少呢") String question) throws Exception {
        // 测试方法。。如，在网址上附加quesion。如http://localhost:8080/qademo/test?question=房价是多少呢

        questionClassifierService.evaluateClassifyMethod();
        int type = (int) questionClassifierService.classifyByBayes(question);
        matchService.init(2955, type);
        // String s = matchService.match(question);
        // System.out.print(s);
        return "@" + questionClassifierService.classifyByBayes(question);
    }

    @Get("classifybybayes")
    public String classifybybayes(@Param("question") @DefValue("房价是多少呢") String question) throws Exception {
        // 测试方法。。如，在网址上附加question。如http://localhost:8080/qademo/classifybybayes?question=房价是多少呢
        double[] temp = questionClassifierService.getQuestionVector(question);
        for (double i : temp) {
            System.out.println(i);
        }
        return "@" + questionClassifierService.classifyByBayes(question);
    }

    @Get("classifybyfre")
    public String classifybyfre(@Param("question") @DefValue("房价是多少呢") String question) throws Exception {
        // 测试方法。。如，在网址上附加question。如http://localhost:8080/qademo/classifybyfre?question=房价是多少呢
        double[] temp = questionClassifierService.getQuestionVector(question);
        System.out.println("向量化结果是");
        for (int i = 0; i < 11; i++) {
            System.out.println(temp[i]);
        }
        return "@" + questionClassifierService.classifyByFre(question);
    }

    @Get("classifybytree")
    public String classifybyTree(@Param("question") @DefValue("房价是多少呢") String question) throws Exception {
        // 测试方法。。如，在网址上附加question。如http://localhost:8080/qademo/classifybytree?question=房价是多少呢
        double[] temp = questionClassifierService.getQuestionVector(question);
        System.out.println("向量化结果是");
        for (double i : temp) {
            System.out.println(i);
        }
        return "@" + questionClassifierService.classifyByTree(question);

    }

    @Get("classifybyknn")
    public String classifybyKnn(@Param("question") @DefValue("房价是多少呢") String question) throws Exception {
        // 测试方法。。如，在网址上附加question。如http://localhost:8080/qademo/classifybyknn?question=房价是多少呢
        double[] temp = questionClassifierService.getQuestionVector(question);
        for (double i : temp) {
            System.out.println(i);
        }
        return "@" + questionClassifierService.classifyByKnn(question);

    }

    @Get("home")
    public String homepage() {
        return "homepage";
    }

    @Get("autoanswer")
    public String autoanswer() {
        return "autoanswer";
    }

    
    @Get("evaluate")
    public String evaluate() throws Exception{
        questionClassifierService.evaluateClassifyMethod();
        return "@请查看控制台。";
    }
    @Get("addDBData")
    public String addDBData(){
        whiteWordService.addDBData();
        return "@请查看数据库验证";
    }


}
