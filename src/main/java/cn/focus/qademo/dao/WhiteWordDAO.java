package cn.focus.qademo.dao;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import cn.focus.qademo.model.QuestionWhiteWord;
import cn.focus.qademo.model.WhiteWord;

@DAO
public interface WhiteWordDAO {

    @SQL("select name from white_word where id in (SELECT parent_id FROM white_word where id=:1) or (parent_id=0 and id=:1)")	
    String getParentWordName(int wordid);
    
    @SQL("select * from white_word where name= :1")   
    List<WhiteWord> executeQuery(String word);

    //根据问题类型，获得对应的白词分类编号。
    @SQL("SELECT id FROM white_word where name=:1 and parent_id=0")      
    int getWordClass(String type);

    @SQL("SELECT COUNT(1) FROM white_word where name=:1 and  parent_id=:2")
    int belongWhiteClass(String word, int parentid);
	
  //根据名字，获得对应的白词Id。
    @SQL("SELECT * FROM white_word where name=:1")      
    List<WhiteWord> getWordByName(String name);

    @SQL("SELECT  id, parent_id, name, create_time, update_time FROM white_word WHERE id = :1")
    WhiteWord get(Integer id);

    @SQL("SELECT  id, parent_id, name, create_time, update_time FROM white_word WHERE id IN (:1)")
    List<WhiteWord> getList(List<Integer> idList);

    @SQL("SELECT  id, parent_id, name, create_time, update_time FROM white_word WHERE id IN (:1) ORDER BY find_in_set(id , :2)")
    List<WhiteWord> getOrderList(List<Integer> idList, String orderIdsStr);

    
    
    @ReturnGeneratedKeys
    @SQL("INSERT INTO white_word (" + "id" + "#if(:1.parentId!=null){,parent_id}" + "#if(:1.name!=null){,name}"
            + "#if(:1.createTime!=null){,create_time}" + "#if(:1.updateTime!=null){,update_time}" + ") VALUES("
            + ":1.id" + "#if(:1.parentId!=null){,:1.parentId}" + "#if(:1.name!=null){,:1.name}"
            + "#if(:1.createTime!=null){,:1.createTime}" + "#if(:1.updateTime!=null){,:1.updateTime}" + ")")
    int save(WhiteWord whiteword);

    @SQL("UPDATE white_word SET id=:1.id " + "#if(:1.parentId!=null){,parent_id=:1.parentId}"
            + "#if(:1.name!=null){,name=:1.name}" + "#if(:1.createTime!=null){,create_time=:1.createTime}"
            + "#if(:1.updateTime!=null){,update_time=:1.updateTime}" + " WHERE id=:1.id ")
    void update(WhiteWord whiteword);

    @SQL("DELETE FROM white_word WHERE id= :1")
    int delete(Integer id);

    @SQL("SELECT COUNT(1) FROM white_word")
    int count();

    @SQL("SELECT * FROM white_word WHERE parent_id > 0")
    List<WhiteWord> getWords();

    @SQL("INSERT INTO question_white_word (question_id,word_id)" + " VALUES (:1.questionId,:1.wordId)")
    void saveQuestionWhiteWord(List<QuestionWhiteWord> words);

    @SQL("SELECT update_time FROM white_word WHERE update_time > :1 ORDER by update_time DESC LIMIT 1")
    Date hasUpdate(Date time);

}