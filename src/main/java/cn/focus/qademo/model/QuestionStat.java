package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class QuestionStat extends BaseObject {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private Integer id;

    private java.util.Date reportDate;

    private Integer cityId;

    private Integer todaySubmit;

    private Integer todayAnswer;

    private Integer todayDel;

    private Integer totalSubmit;

    private Integer totalUndeal;

    private Integer totalAnswer;

    private java.util.Date createTime;

    public void copy(QuestionStat questionstat){
               this.id = questionstat.id;
               this.reportDate = questionstat.reportDate;
               this.cityId = questionstat.cityId;
               this.todaySubmit = questionstat.todaySubmit;
               this.todayAnswer = questionstat.todayAnswer;
               this.todayDel = questionstat.todayDel;
               this.totalSubmit = questionstat.totalSubmit;
               this.totalUndeal = questionstat.totalUndeal;
               this.totalAnswer = questionstat.totalAnswer;
               this.createTime = questionstat.createTime;
          }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public java.util.Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(java.util.Date reportDate) {
        this.reportDate = reportDate;
    }
    
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    
    public Integer getTodaySubmit() {
        return todaySubmit;
    }

    public void setTodaySubmit(Integer todaySubmit) {
        this.todaySubmit = todaySubmit;
    }
    
    public Integer getTodayAnswer() {
        return todayAnswer;
    }

    public void setTodayAnswer(Integer todayAnswer) {
        this.todayAnswer = todayAnswer;
    }
    
    public Integer getTodayDel() {
        return todayDel;
    }

    public void setTodayDel(Integer todayDel) {
        this.todayDel = todayDel;
    }
    
    public Integer getTotalSubmit() {
        return totalSubmit;
    }

    public void setTotalSubmit(Integer totalSubmit) {
        this.totalSubmit = totalSubmit;
    }
    
    public Integer getTotalUndeal() {
        return totalUndeal;
    }

    public void setTotalUndeal(Integer totalUndeal) {
        this.totalUndeal = totalUndeal;
    }
    
    public Integer getTotalAnswer() {
        return totalAnswer;
    }

    public void setTotalAnswer(Integer totalAnswer) {
        this.totalAnswer = totalAnswer;
    }
    
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    
    public static QuestionStat getInstantce(Integer id,java.util.Date reportDate,Integer cityId,Integer todaySubmit,Integer todayAnswer,Integer todayDel,Integer totalSubmit,Integer totalUndeal,Integer totalAnswer,java.util.Date createTime){
      QuestionStat questionstat = new QuestionStat();
               questionstat.setId(id);
               questionstat.setReportDate(reportDate);
               questionstat.setCityId(cityId);
               questionstat.setTodaySubmit(todaySubmit);
               questionstat.setTodayAnswer(todayAnswer);
               questionstat.setTodayDel(todayDel);
               questionstat.setTotalSubmit(totalSubmit);
               questionstat.setTotalUndeal(totalUndeal);
               questionstat.setTotalAnswer(totalAnswer);
               questionstat.setCreateTime(createTime);
            return questionstat;
    }

}