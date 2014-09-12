package cn.focus.qademo.dao;

import java.util.List;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import cn.focus.qademo.model.QuestionUseful;
@DAO
public interface QuestionUsefulDAO {

	@SQL("SELECT  uid, question_id, create_time FROM question_useful WHERE uid = :1")
	QuestionUseful get(Integer id );

	
	@SQL("SELECT  uid, question_id, create_time FROM question_useful WHERE uid IN (:1)")
	List<QuestionUseful> getList(List<Integer> idList );
	
	
	@SQL("SELECT  uid, question_id, create_time FROM question_useful WHERE uid IN (:1) ORDER BY find_in_set(uid , :2)")
	List<QuestionUseful> getOrderList(List<Integer> idList, String orderIdsStr );
    
	    @ReturnGeneratedKeys
	@SQL("INSERT INTO question_useful ("+
				       "uid"+
	    	    				    	       "#if(:1.questionId!=null){,question_id}"+
	    				    	       "#if(:1.createTime!=null){,create_time}"+
	    		") VALUES("+
		    	       ":1.uid"+
	    	    		    	    	       "#if(:1.questionId!=null){,:1.questionId}"+
	    		    	    	       "#if(:1.createTime!=null){,:1.createTime}"+
	    		")") 
	Long save(QuestionUseful questionuseful );
	
		@SQL("UPDATE question_useful SET uid=:1.uid " +
		    		    	       "#if(:1.questionId!=null){,question_id=:1.questionId}"+
	    		    	       "#if(:1.createTime!=null){,create_time=:1.createTime}"+
	    	    " WHERE uid=:1.uid ")
	void update(QuestionUseful questionuseful );
	
	@SQL("DELETE FROM question_useful WHERE uid= :1")
	int delete(Integer id);
	
	@SQL("SELECT COUNT(1) FROM question_useful")
	int count();
	
}