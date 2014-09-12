package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class QuestionUseful extends BaseObject {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private Integer questionId;

    private java.util.Date createTime;

    public void copy(QuestionUseful questionuseful){
               this.uid = questionuseful.uid;
               this.questionId = questionuseful.questionId;
               this.createTime = questionuseful.createTime;
          }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    
    public static QuestionUseful getInstantce(Integer uid,Integer questionId,java.util.Date createTime){
      QuestionUseful questionuseful = new QuestionUseful();
               questionuseful.setUid(uid);
               questionuseful.setQuestionId(questionId);
               questionuseful.setCreateTime(createTime);
            return questionuseful;
    }

}