package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class QuestionCopy extends BaseObject {

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

    public void copy(QuestionCopy questioncopy){
               this.id = questioncopy.id;
               this.uid = questioncopy.uid;
               this.question = questioncopy.question;
               this.answer = questioncopy.answer;
               this.cityId = questioncopy.cityId;
               this.buildId = questioncopy.buildId;
               this.type = questioncopy.type;
               this.status = questioncopy.status;
               this.editorId = questioncopy.editorId;
               this.auditId = questioncopy.auditId;
               this.isAnswered = questioncopy.isAnswered;
               this.isAnonymous = questioncopy.isAnonymous;
               this.collectionsCount = questioncopy.collectionsCount;
               this.usefulCount = questioncopy.usefulCount;
               this.isRead = questioncopy.isRead;
               this.createTime = questioncopy.createTime;
               this.updateTime = questioncopy.updateTime;
               this.groupId = questioncopy.groupId;
               this.auditTime = questioncopy.auditTime;
               this.esTime = questioncopy.esTime;
               this.answerTime = questioncopy.answerTime;
               this.delTime = questioncopy.delTime;
               this.antispamStatus = questioncopy.antispamStatus;
               this.antispamMid = questioncopy.antispamMid;
               this.client = questioncopy.client;
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
    
    public static QuestionCopy getInstantce(Long id,Integer uid,String question,String answer,Integer cityId,Integer buildId,Integer type,Integer status,Integer editorId,Integer auditId,Boolean isAnswered,Boolean isAnonymous,Integer collectionsCount,Integer usefulCount,Integer isRead,java.util.Date createTime,java.util.Date updateTime,Integer groupId,java.util.Date auditTime,java.util.Date esTime,java.util.Date answerTime,java.util.Date delTime,Integer antispamStatus,String antispamMid,Integer client){
      QuestionCopy questioncopy = new QuestionCopy();
               questioncopy.setId(id);
               questioncopy.setUid(uid);
               questioncopy.setQuestion(question);
               questioncopy.setAnswer(answer);
               questioncopy.setCityId(cityId);
               questioncopy.setBuildId(buildId);
               questioncopy.setType(type);
               questioncopy.setStatus(status);
               questioncopy.setEditorId(editorId);
               questioncopy.setAuditId(auditId);
               questioncopy.setIsAnswered(isAnswered);
               questioncopy.setIsAnonymous(isAnonymous);
               questioncopy.setCollectionsCount(collectionsCount);
               questioncopy.setUsefulCount(usefulCount);
               questioncopy.setIsRead(isRead);
               questioncopy.setCreateTime(createTime);
               questioncopy.setUpdateTime(updateTime);
               questioncopy.setGroupId(groupId);
               questioncopy.setAuditTime(auditTime);
               questioncopy.setEsTime(esTime);
               questioncopy.setAnswerTime(answerTime);
               questioncopy.setDelTime(delTime);
               questioncopy.setAntispamStatus(antispamStatus);
               questioncopy.setAntispamMid(antispamMid);
               questioncopy.setClient(client);
            return questioncopy;
    }

}