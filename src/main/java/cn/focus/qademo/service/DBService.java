package cn.focus.qademo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.focus.qademo.dao.QuestionDAO;
import cn.focus.qademo.model.Question;

@Service
public class DBService {
    @Autowired
    private QuestionDAO questionDAO;
    
    public ArrayList<ArrayList<String>> query1(Integer build_id,Integer type){
        if(build_id==null || build_id<0)
        {
            System.out.println("请输入正确的Build_id");
            return null;
        }
        if(type==null || type<0)
        {
            System.out.println("请输入正确的Type");
            return null;
        }
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> questionList = new ArrayList<String>();
        ArrayList<String> idList = new ArrayList<String>();
        List<Question> temp = questionDAO.getListByBuild_idAndType(build_id,type);
        for(Question q : temp)
        {
            idList.add(q.getId().toString());
            questionList.add(q.getQuestion());
        }
        result.add(idList);
        result.add(questionList);
        return result;
        
    }
}
