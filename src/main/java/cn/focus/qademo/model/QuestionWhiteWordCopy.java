package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class QuestionWhiteWordCopy extends BaseObject {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private Integer id;

    private Long questionId;

    private Integer wordId;

    private java.util.Date createTime;

    public void copy(QuestionWhiteWordCopy questionwhitewordcopy){
               this.id = questionwhitewordcopy.id;
               this.questionId = questionwhitewordcopy.questionId;
               this.wordId = questionwhitewordcopy.wordId;
               this.createTime = questionwhitewordcopy.createTime;
          }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    
    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }
    
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    
    public static QuestionWhiteWordCopy getInstantce(Integer id,Long questionId,Integer wordId,java.util.Date createTime){
      QuestionWhiteWordCopy questionwhitewordcopy = new QuestionWhiteWordCopy();
               questionwhitewordcopy.setId(id);
               questionwhitewordcopy.setQuestionId(questionId);
               questionwhitewordcopy.setWordId(wordId);
               questionwhitewordcopy.setCreateTime(createTime);
            return questionwhitewordcopy;
    }

}