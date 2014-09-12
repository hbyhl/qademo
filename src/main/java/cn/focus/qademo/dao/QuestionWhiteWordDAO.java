package cn.focus.qademo.dao;

import java.util.List;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import cn.focus.qademo.model.QuestionWhiteWord;
@DAO
public interface QuestionWhiteWordDAO {

	@SQL("SELECT  id, question_id, word_id, status, create_time, city_id, city_name FROM question_white_word WHERE id = :1")
	QuestionWhiteWord get(Integer id );

	
	@SQL("SELECT  id, question_id, word_id, status, create_time, city_id, city_name FROM question_white_word WHERE id IN (:1)")
	List<QuestionWhiteWord> getList(List<Integer> idList );
	
	
	@SQL("SELECT  id, question_id, word_id, status, create_time, city_id, city_name FROM question_white_word WHERE id IN (:1) ORDER BY find_in_set(id , :2)")
	List<QuestionWhiteWord> getOrderList(List<Integer> idList, String orderIdsStr );
    
	    @ReturnGeneratedKeys
	@SQL("INSERT INTO question_white_word ("+
				       "id"+
	    	    				    	       "#if(:1.questionId!=null){,question_id}"+
	    				    	       "#if(:1.wordId!=null){,word_id}"+
	    				    	       "#if(:1.status!=null){,status}"+
	    				    	       "#if(:1.createTime!=null){,create_time}"+
	    				    	       "#if(:1.cityId!=null){,city_id}"+
	    				    	       "#if(:1.cityName!=null){,city_name}"+
	    		") VALUES("+
		    	       ":1.id"+
	    	    		    	    	       "#if(:1.questionId!=null){,:1.questionId}"+
	    		    	    	       "#if(:1.wordId!=null){,:1.wordId}"+
	    		    	    	       "#if(:1.status!=null){,:1.status}"+
	    		    	    	       "#if(:1.createTime!=null){,:1.createTime}"+
	    		    	    	       "#if(:1.cityId!=null){,:1.cityId}"+
	    		    	    	       "#if(:1.cityName!=null){,:1.cityName}"+
	    		")") 
	Long save(QuestionWhiteWord questionwhiteword );
	
		@SQL("UPDATE question_white_word SET id=:1.id " +
		    		    	       "#if(:1.questionId!=null){,question_id=:1.questionId}"+
	    		    	       "#if(:1.wordId!=null){,word_id=:1.wordId}"+
	    		    	       "#if(:1.status!=null){,status=:1.status}"+
	    		    	       "#if(:1.createTime!=null){,create_time=:1.createTime}"+
	    		    	       "#if(:1.cityId!=null){,city_id=:1.cityId}"+
	    		    	       "#if(:1.cityName!=null){,city_name=:1.cityName}"+
	    	    " WHERE id=:1.id ")
	void update(QuestionWhiteWord questionwhiteword );
	
	@SQL("DELETE FROM question_white_word WHERE id= :1")
	int delete(Integer id);
	
	@SQL("SELECT COUNT(1) FROM question_white_word")
	int count();
	
}