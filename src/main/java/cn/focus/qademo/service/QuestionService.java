package cn.focus.qademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.focus.qademo.dao.QuestionDAO;
import cn.focus.qademo.model.Question;

@Service
public class QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    public Question getQuestionById(Long questionId) {
        return questionDAO.get(questionId);
    }

}
