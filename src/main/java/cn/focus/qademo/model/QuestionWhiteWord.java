package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class QuestionWhiteWord extends BaseObject {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private Integer id;

    private Long questionId;

    private Integer wordId;

    private Integer status;

    private java.util.Date createTime;

    private Integer cityId;

    private String cityName;

    public void copy(QuestionWhiteWord questionwhiteword){
               this.id = questionwhiteword.id;
               this.questionId = questionwhiteword.questionId;
               this.wordId = questionwhiteword.wordId;
               this.status = questionwhiteword.status;
               this.createTime = questionwhiteword.createTime;
               this.cityId = questionwhiteword.cityId;
               this.cityName = questionwhiteword.cityName;
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
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public static QuestionWhiteWord getInstantce(Integer id,Long questionId,Integer wordId,Integer status,java.util.Date createTime,Integer cityId,String cityName){
      QuestionWhiteWord questionwhiteword = new QuestionWhiteWord();
               questionwhiteword.setId(id);
               questionwhiteword.setQuestionId(questionId);
               questionwhiteword.setWordId(wordId);
               questionwhiteword.setStatus(status);
               questionwhiteword.setCreateTime(createTime);
               questionwhiteword.setCityId(cityId);
               questionwhiteword.setCityName(cityName);
            return questionwhiteword;
    }

}