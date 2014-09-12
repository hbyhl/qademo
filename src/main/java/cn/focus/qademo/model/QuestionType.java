package cn.focus.qademo.model; 

import cn.focus.dc.commons.model.BaseObject;
import cn.focus.dc.commons.annotation.PrimaryKey;

public class QuestionType extends BaseObject {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private Integer id;

    private String type;

    public void copy(QuestionType questiontype){
               this.id = questiontype.id;
               this.type = questiontype.type;
          }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public static QuestionType getInstantce(Integer id,String type){
      QuestionType questiontype = new QuestionType();
               questiontype.setId(id);
               questiontype.setType(type);
            return questiontype;
    }

}