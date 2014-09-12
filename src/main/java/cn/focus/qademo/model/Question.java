package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class Question extends BaseObject {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private Long id;

    private Integer uid;

    private String question;

    private String answer;

    private Integer cityId;

    private Integer buildId;

    private Integer type;

    private Integer status;

    private Integer editorId;

    private Integer auditId;

    private Boolean isAnswered;

    private Boolean isAnonymous;

    private Integer collectionsCount;

    private Integer usefulCount;

    private Integer isRead;

    private java.util.Date createTime;

    private java.util.Date updateTime;

    private Integer groupId;

    private java.util.Date auditTime;

    private java.util.Date esTime;

    private java.util.Date answerTime;

    private java.util.Date delTime;

    private Integer antispamStatus;

    private String antispamMid;

    private Integer client;
    private Integer price;

    public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void copy(Question question){
               this.id = question.id;
               this.uid = question.uid;
               this.question = question.question;
               this.answer = question.answer;
               this.cityId = question.cityId;
               this.buildId = question.buildId;
               this.type = question.type;
               this.status = question.status;
               this.editorId = question.editorId;
               this.auditId = question.auditId;
               this.isAnswered = question.isAnswered;
               this.isAnonymous = question.isAnonymous;
               this.collectionsCount = question.collectionsCount;
               this.usefulCount = question.usefulCount;
               this.isRead = question.isRead;
               this.createTime = question.createTime;
               this.updateTime = question.updateTime;
               this.groupId = question.groupId;
               this.auditTime = question.auditTime;
               this.esTime = question.esTime;
               this.answerTime = question.answerTime;
               this.delTime = question.delTime;
               this.antispamStatus = question.antispamStatus;
               this.antispamMid = question.antispamMid;
               this.client = question.client;

          }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    
    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }
    
    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }
    
    public Boolean getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(Boolean isAnswered) {
        this.isAnswered = isAnswered;
    }
    
    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
    
    public Integer getCollectionsCount() {
        return collectionsCount;
    }

    public void setCollectionsCount(Integer collectionsCount) {
        this.collectionsCount = collectionsCount;
    }
    
    public Integer getUsefulCount() {
        return usefulCount;
    }

    public void setUsefulCount(Integer usefulCount) {
        this.usefulCount = usefulCount;
    }
    
    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
    
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    
    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    public java.util.Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(java.util.Date auditTime) {
        this.auditTime = auditTime;
    }
    
    public java.util.Date getEsTime() {
        return esTime;
    }

    public void setEsTime(java.util.Date esTime) {
        this.esTime = esTime;
    }
    
    public java.util.Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(java.util.Date answerTime) {
        this.answerTime = answerTime;
    }
    
    public java.util.Date getDelTime() {
        return delTime;
    }

    public void setDelTime(java.util.Date delTime) {
        this.delTime = delTime;
    }
    
    public Integer getAntispamStatus() {
        return antispamStatus;
    }

    public void setAntispamStatus(Integer antispamStatus) {
        this.antispamStatus = antispamStatus;
    }
    
    public String getAntispamMid() {
        return antispamMid;
    }

    public void setAntispamMid(String antispamMid) {
        this.antispamMid = antispamMid;
    }
    
    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }
    
    public static Question getInstantce(Long id,Integer uid,String question,String answer,Integer cityId,Integer buildId,Integer type,Integer status,Integer editorId,Integer auditId,Boolean isAnswered,Boolean isAnonymous,Integer collectionsCount,Integer usefulCount,Integer isRead,java.util.Date createTime,java.util.Date updateTime,Integer groupId,java.util.Date auditTime,java.util.Date esTime,java.util.Date answerTime,java.util.Date delTime,Integer antispamStatus,String antispamMid,Integer client,Integer price){
      Question question1 = new Question();
               question1.setId(id);
               question1.setUid(uid);
               question1.setQuestion(question);
               question1.setAnswer(answer);
               question1.setCityId(cityId);
               question1.setBuildId(buildId);
               question1.setType(type);
               question1.setStatus(status);
               question1.setEditorId(editorId);
               question1.setAuditId(auditId);
               question1.setIsAnswered(isAnswered);
               question1.setIsAnonymous(isAnonymous);
               question1.setCollectionsCount(collectionsCount);
               question1.setUsefulCount(usefulCount);
               question1.setIsRead(isRead);
               question1.setCreateTime(createTime);
               question1.setUpdateTime(updateTime);
               question1.setGroupId(groupId);
               question1.setAuditTime(auditTime);
               question1.setEsTime(esTime);
               question1.setAnswerTime(answerTime);
               question1.setDelTime(delTime);
               question1.setAntispamStatus(antispamStatus);
               question1.setAntispamMid(antispamMid);
               question1.setClient(client);
 
            return question1;
    }

}