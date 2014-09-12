package cn.focus.qademo.dao;

import java.util.List;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import cn.focus.qademo.model.WhiteWordCopy;
@DAO
public interface WhiteWordCopyDAO {

	@SQL("SELECT  id, parent_id, name, create_time, update_time FROM white_word_copy WHERE id = :1")
	WhiteWordCopy get(Integer id );

	
	@SQL("SELECT  id, parent_id, name, create_time, update_time FROM white_word_copy WHERE id IN (:1)")
	List<WhiteWordCopy> getList(List<Integer> idList );
	
	
	@SQL("SELECT  id, parent_id, name, create_time, update_time FROM white_word_copy WHERE id IN (:1) ORDER BY find_in_set(id , :2)")
	List<WhiteWordCopy> getOrderList(List<Integer> idList, String orderIdsStr );
    
	    @ReturnGeneratedKeys
	@SQL("INSERT INTO white_word_copy ("+
				       "id"+
	    	    				    	       "#if(:1.parentId!=null){,parent_id}"+
	    				    	       "#if(:1.name!=null){,name}"+
	    				    	       "#if(:1.createTime!=null){,create_time}"+
	    				    	       "#if(:1.updateTime!=null){,update_time}"+
	    		") VALUES("+
		    	       ":1.id"+
	    	    		    	    	       "#if(:1.parentId!=null){,:1.parentId}"+
	    		    	    	       "#if(:1.name!=null){,:1.name}"+
	    		    	    	       "#if(:1.createTime!=null){,:1.createTime}"+
	    		    	    	       "#if(:1.updateTime!=null){,:1.updateTime}"+
	    		")") 
	Long save(WhiteWordCopy whitewordcopy );
	
		@SQL("UPDATE white_word_copy SET id=:1.id " +
		    		    	       "#if(:1.parentId!=null){,parent_id=:1.parentId}"+
	    		    	       "#if(:1.name!=null){,name=:1.name}"+
	    		    	       "#if(:1.createTime!=null){,create_time=:1.createTime}"+
	    		    	       "#if(:1.updateTime!=null){,update_time=:1.updateTime}"+
	    	    " WHERE id=:1.id ")
	void update(WhiteWordCopy whitewordcopy );
	
	@SQL("DELETE FROM white_word_copy WHERE id= :1")
	int delete(Integer id);
	
	@SQL("SELECT COUNT(1) FROM white_word_copy")
	int count();
	
}