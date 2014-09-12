package cn.focus.qademo.dao;

import java.util.List;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import cn.focus.qademo.model.QuestionType;
@DAO
public interface QuestionTypeDAO {

	@SQL("SELECT  id, type FROM question_type WHERE id = :1")
	QuestionType get(Integer id );

	
	@SQL("SELECT  id, type FROM question_type WHERE id IN (:1)")
	List<QuestionType> getList(List<Integer> idList );
	
	
	@SQL("SELECT  id, type FROM question_type WHERE id IN (:1) ORDER BY find_in_set(id , :2)")
	List<QuestionType> getOrderList(List<Integer> idList, String orderIdsStr );
    
	    @ReturnGeneratedKeys
	@SQL("INSERT INTO question_type ("+
				       "id"+
	    	    				    	       "#if(:1.type!=null){,type}"+
	    		") VALUES("+
		    	       ":1.id"+
	    	    		    	    	       "#if(:1.type!=null){,:1.type}"+
	    		")") 
	Long save(QuestionType questiontype );
	
		@SQL("UPDATE question_type SET id=:1.id " +
		    		    	       "#if(:1.type!=null){,type=:1.type}"+
	    	    " WHERE id=:1.id ")
	void update(QuestionType questiontype );
	
	@SQL("DELETE FROM question_type WHERE id= :1")
	int delete(Integer id);
	
	@SQL("SELECT COUNT(1) FROM question_type")
	int count();

    @SQL("SELECT id FROM question_type where type=:1")
    int getID(String word);

}