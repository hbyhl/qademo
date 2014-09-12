package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class WhiteWord extends BaseObject {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer parentId;

    private String name;

    private java.util.Date createTime;

    private java.util.Date updateTime;

    public void copy(WhiteWord whiteword){
               this.id = whiteword.id;
               this.parentId = whiteword.parentId;
               this.name = whiteword.name;
               this.createTime = whiteword.createTime;
               this.updateTime = whiteword.updateTime;
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
    
    public static WhiteWord getInstantce(Integer id,Integer parentId,String name,java.util.Date createTime,java.util.Date updateTime){
      WhiteWord whiteword = new WhiteWord();
               whiteword.setId(id);
               whiteword.setParentId(parentId);
               whiteword.setName(name);
               whiteword.setCreateTime(createTime);
               whiteword.setUpdateTime(updateTime);
            return whiteword;
    }

}