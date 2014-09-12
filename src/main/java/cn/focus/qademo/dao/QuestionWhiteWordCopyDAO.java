package cn.focus.qademo.dao;

import java.util.List;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import cn.focus.qademo.model.QuestionWhiteWordCopy;
@DAO
public interface QuestionWhiteWordCopyDAO {

	@SQL("SELECT  id, question_id, word_id, create_time FROM question_white_word_copy WHERE id = :1")
	QuestionWhiteWordCopy get(Integer id );

	
	@SQL("SELECT  id, question_id, word_id, create_time FROM question_white_word_copy WHERE id IN (:1)")
	List<QuestionWhiteWordCopy> getList(List<Integer> idList );
	
	
	@SQL("SELECT  id, question_id, word_id, create_time FROM question_white_word_copy WHERE id IN (:1) ORDER BY find_in_set(id , :2)")
	List<QuestionWhiteWordCopy> getOrderList(List<Integer> idList, String orderIdsStr );
    
	    @ReturnGeneratedKeys
	@SQL("INSERT INTO question_white_word_copy ("+
				       "id"+
	    	    				    	       "#if(:1.questionId!=null){,question_id}"+
	    				    	       "#if(:1.wordId!=null){,word_id}"+
	    				    	       "#if(:1.createTime!=null){,create_time}"+
	    		") VALUES("+
		    	       ":1.id"+
	    	    		    	    	       "#if(:1.questionId!=null){,:1.questionId}"+
	    		    	    	       "#if(:1.wordId!=null){,:1.wordId}"+
	    		    	    	       "#if(:1.createTime!=null){,:1.createTime}"+
	    		")") 
	Long save(QuestionWhiteWordCopy questionwhitewordcopy );
	
		@SQL("UPDATE question_white_word_copy SET id=:1.id " +
		    		    	       "#if(:1.questionId!=null){,question_id=:1.questionId}"+
	    		    	       "#if(:1.wordId!=null){,word_id=:1.wordId}"+
	    		    	       "#if(:1.createTime!=null){,create_time=:1.createTime}"+
	    	    " WHERE id=:1.id ")
	void update(QuestionWhiteWordCopy questionwhitewordcopy );
	
	@SQL("DELETE FROM question_white_word_copy WHERE id= :1")
	int delete(Integer id);
	
	@SQL("SELECT COUNT(1) FROM question_white_word_copy")
	int count();
	
}