package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class WhiteWordCopy extends BaseObject {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer parentId;

    private String name;

    private java.util.Date createTime;

    private java.util.Date updateTime;

    public void copy(WhiteWordCopy whitewordcopy){
               this.id = whitewordcopy.id;
               this.parentId = whitewordcopy.parentId;
               this.name = whitewordcopy.name;
               this.createTime = whitewordcopy.createTime;
               this.updateTime = whitewordcopy.updateTime;
          }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    public static WhiteWordCopy getInstantce(Integer id,Integer parentId,String name,java.util.Date createTime,java.util.Date updateTime){
      WhiteWordCopy whitewordcopy = new WhiteWordCopy();
               whitewordcopy.setId(id);
               whitewordcopy.setParentId(parentId);
               whitewordcopy.setName(name);
               whitewordcopy.setCreateTime(createTime);
               whitewordcopy.setUpdateTime(updateTime);
            return whitewordcopy;
    }

}