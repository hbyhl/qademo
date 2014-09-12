package cn.focus.qademo.dao;

import java.util.List;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import cn.focus.qademo.model.QuestionStat;
@DAO
public interface QuestionStatDAO {

	@SQL("SELECT  id, report_date, city_id, today_submit, today_answer, today_del, total_submit, total_undeal, total_answer, create_time FROM question_stat WHERE id = :1")
	QuestionStat get(Integer id );

	
	@SQL("SELECT  id, report_date, city_id, today_submit, today_answer, today_del, total_submit, total_undeal, total_answer, create_time FROM question_stat WHERE id IN (:1)")
	List<QuestionStat> getList(List<Integer> idList );
	
	
	@SQL("SELECT  id, report_date, city_id, today_submit, today_answer, today_del, total_submit, total_undeal, total_answer, create_time FROM question_stat WHERE id IN (:1) ORDER BY find_in_set(id , :2)")
	List<QuestionStat> getOrderList(List<Integer> idList, String orderIdsStr );
    
	    @ReturnGeneratedKeys
	@SQL("INSERT INTO question_stat ("+
				       "id"+
	    	    				    	       "#if(:1.reportDate!=null){,report_date}"+
	    				    	       "#if(:1.cityId!=null){,city_id}"+
	    				    	       "#if(:1.todaySubmit!=null){,today_submit}"+
	    				    	       "#if(:1.todayAnswer!=null){,today_answer}"+
	    				    	       "#if(:1.todayDel!=null){,today_del}"+
	    				    	       "#if(:1.totalSubmit!=null){,total_submit}"+
	    				    	       "#if(:1.totalUndeal!=null){,total_undeal}"+
	    				    	       "#if(:1.totalAnswer!=null){,total_answer}"+
	    				    	       "#if(:1.createTime!=null){,create_time}"+
	    		") VALUES("+
		    	       ":1.id"+
	    	    		    	    	       "#if(:1.reportDate!=null){,:1.reportDate}"+
	    		    	    	       "#if(:1.cityId!=null){,:1.cityId}"+
	    		    	    	       "#if(:1.todaySubmit!=null){,:1.todaySubmit}"+
	    		    	    	       "#if(:1.todayAnswer!=null){,:1.todayAnswer}"+
	    		    	    	       "#if(:1.todayDel!=null){,:1.todayDel}"+
	    		    	    	       "#if(:1.totalSubmit!=null){,:1.totalSubmit}"+
	    		    	    	       "#if(:1.totalUndeal!=null){,:1.totalUndeal}"+
	    		    	    	       "#if(:1.totalAnswer!=null){,:1.totalAnswer}"+
	    		    	    	       "#if(:1.createTime!=null){,:1.createTime}"+
	    		")") 
	Long save(QuestionStat questionstat );
	
		@SQL("UPDATE question_stat SET id=:1.id " +
		    		    	       "#if(:1.reportDate!=null){,report_date=:1.reportDate}"+
	    		    	       "#if(:1.cityId!=null){,city_id=:1.cityId}"+
	    		    	       "#if(:1.todaySubmit!=null){,today_submit=:1.todaySubmit}"+
	    		    	       "#if(:1.todayAnswer!=null){,today_answer=:1.todayAnswer}"+
	    		    	       "#if(:1.todayDel!=null){,today_del=:1.todayDel}"+
	    		    	       "#if(:1.totalSubmit!=null){,total_submit=:1.totalSubmit}"+
	    		    	       "#if(:1.totalUndeal!=null){,total_undeal=:1.totalUndeal}"+
	    		    	       "#if(:1.totalAnswer!=null){,total_answer=:1.totalAnswer}"+
	    		    	       "#if(:1.createTime!=null){,create_time=:1.createTime}"+
	    	    " WHERE id=:1.id ")
	void update(QuestionStat questionstat );
	
	@SQL("DELETE FROM question_stat WHERE id= :1")
	int delete(Integer id);
	
	@SQL("SELECT COUNT(1) FROM question_stat")
	int count();
	
}