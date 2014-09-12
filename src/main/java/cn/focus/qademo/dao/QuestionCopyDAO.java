package cn.focus.qademo.dao;

import java.util.List;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import cn.focus.qademo.model.QuestionCopy;
@DAO
public interface QuestionCopyDAO {

	@SQL("SELECT  id, uid, question, answer, city_id, build_id, type, status, editor_id, audit_id, is_answered, is_anonymous, collections_count, useful_count, is_read, create_time, update_time, group_id, audit_time, es_time, answer_time, del_time, antispam_status, antispam_mid, client FROM question_copy WHERE id = :1")
	QuestionCopy get(Long id );

	
	@SQL("SELECT  id, uid, question, answer, city_id, build_id, type, status, editor_id, audit_id, is_answered, is_anonymous, collections_count, useful_count, is_read, create_time, update_time, group_id, audit_time, es_time, answer_time, del_time, antispam_status, antispam_mid, client FROM question_copy WHERE id IN (:1)")
	List<QuestionCopy> getList(List<Long> idList );
	
	
	@SQL("SELECT  id, uid, question, answer, city_id, build_id, type, status, editor_id, audit_id, is_answered, is_anonymous, collections_count, useful_count, is_read, create_time, update_time, group_id, audit_time, es_time, answer_time, del_time, antispam_status, antispam_mid, client FROM question_copy WHERE id IN (:1) ORDER BY find_in_set(id , :2)")
	List<QuestionCopy> getOrderList(List<Long> idList, String orderIdsStr );
    
	    @ReturnGeneratedKeys
	@SQL("INSERT INTO question_copy ("+
				       "id"+
	    	    				    	       "#if(:1.uid!=null){,uid}"+
	    				    	       "#if(:1.question!=null){,question}"+
	    				    	       "#if(:1.answer!=null){,answer}"+
	    				    	       "#if(:1.cityId!=null){,city_id}"+
	    				    	       "#if(:1.buildId!=null){,build_id}"+
	    				    	       "#if(:1.type!=null){,type}"+
	    				    	       "#if(:1.status!=null){,status}"+
	    				    	       "#if(:1.editorId!=null){,editor_id}"+
	    				    	       "#if(:1.auditId!=null){,audit_id}"+
	    				    	       "#if(:1.isAnswered!=null){,is_answered}"+
	    				    	       "#if(:1.isAnonymous!=null){,is_anonymous}"+
	    				    	       "#if(:1.collectionsCount!=null){,collections_count}"+
	    				    	       "#if(:1.usefulCount!=null){,useful_count}"+
	    				    	       "#if(:1.isRead!=null){,is_read}"+
	    				    	       "#if(:1.createTime!=null){,create_time}"+
	    				    	       "#if(:1.updateTime!=null){,update_time}"+
	    				    	       "#if(:1.groupId!=null){,group_id}"+
	    				    	       "#if(:1.auditTime!=null){,audit_time}"+
	    				    	       "#if(:1.esTime!=null){,es_time}"+
	    				    	       "#if(:1.answerTime!=null){,answer_time}"+
	    				    	       "#if(:1.delTime!=null){,del_time}"+
	    				    	       "#if(:1.antispamStatus!=null){,antispam_status}"+
	    				    	       "#if(:1.antispamMid!=null){,antispam_mid}"+
	    				    	       "#if(:1.client!=null){,client}"+
	    		") VALUES("+
		    	       ":1.id"+
	    	    		    	    	       "#if(:1.uid!=null){,:1.uid}"+
	    		    	    	       "#if(:1.question!=null){,:1.question}"+
	    		    	    	       "#if(:1.answer!=null){,:1.answer}"+
	    		    	    	       "#if(:1.cityId!=null){,:1.cityId}"+
	    		    	    	       "#if(:1.buildId!=null){,:1.buildId}"+
	    		    	    	       "#if(:1.type!=null){,:1.type}"+
	    		    	    	       "#if(:1.status!=null){,:1.status}"+
	    		    	    	       "#if(:1.editorId!=null){,:1.editorId}"+
	    		    	    	       "#if(:1.auditId!=null){,:1.auditId}"+
	    		    	    	       "#if(:1.isAnswered!=null){,:1.isAnswered}"+
	    		    	    	       "#if(:1.isAnonymous!=null){,:1.isAnonymous}"+
	    		    	    	       "#if(:1.collectionsCount!=null){,:1.collectionsCount}"+
	    		    	    	       "#if(:1.usefulCount!=null){,:1.usefulCount}"+
	    		    	    	       "#if(:1.isRead!=null){,:1.isRead}"+
	    		    	    	       "#if(:1.createTime!=null){,:1.createTime}"+
	    		    	    	       "#if(:1.updateTime!=null){,:1.updateTime}"+
	    		    	    	       "#if(:1.groupId!=null){,:1.groupId}"+
	    		    	    	       "#if(:1.auditTime!=null){,:1.auditTime}"+
	    		    	    	       "#if(:1.esTime!=null){,:1.esTime}"+
	    		    	    	       "#if(:1.answerTime!=null){,:1.answerTime}"+
	    		    	    	       "#if(:1.delTime!=null){,:1.delTime}"+
	    		    	    	       "#if(:1.antispamStatus!=null){,:1.antispamStatus}"+
	    		    	    	       "#if(:1.antispamMid!=null){,:1.antispamMid}"+
	    		    	    	       "#if(:1.client!=null){,:1.client}"+
	    		")") 
	Long save(QuestionCopy questioncopy );
	
		@SQL("UPDATE question_copy SET id=:1.id " +
		    		    	       "#if(:1.uid!=null){,uid=:1.uid}"+
	    		    	       "#if(:1.question!=null){,question=:1.question}"+
	    		    	       "#if(:1.answer!=null){,answer=:1.answer}"+
	    		    	       "#if(:1.cityId!=null){,city_id=:1.cityId}"+
	    		    	       "#if(:1.buildId!=null){,build_id=:1.buildId}"+
	    		    	       "#if(:1.type!=null){,type=:1.type}"+
	    		    	       "#if(:1.status!=null){,status=:1.status}"+
	    		    	       "#if(:1.editorId!=null){,editor_id=:1.editorId}"+
	    		    	       "#if(:1.auditId!=null){,audit_id=:1.auditId}"+
	    		    	       "#if(:1.isAnswered!=null){,is_answered=:1.isAnswered}"+
	    		    	       "#if(:1.isAnonymous!=null){,is_anonymous=:1.isAnonymous}"+
	    		    	       "#if(:1.collectionsCount!=null){,collections_count=:1.collectionsCount}"+
	    		    	       "#if(:1.usefulCount!=null){,useful_count=:1.usefulCount}"+
	    		    	       "#if(:1.isRead!=null){,is_read=:1.isRead}"+
	    		    	       "#if(:1.createTime!=null){,create_time=:1.createTime}"+
	    		    	       "#if(:1.updateTime!=null){,update_time=:1.updateTime}"+
	    		    	       "#if(:1.groupId!=null){,group_id=:1.groupId}"+
	    		    	       "#if(:1.auditTime!=null){,audit_time=:1.auditTime}"+
	    		    	       "#if(:1.esTime!=null){,es_time=:1.esTime}"+
	    		    	       "#if(:1.answerTime!=null){,answer_time=:1.answerTime}"+
	    		    	       "#if(:1.delTime!=null){,del_time=:1.delTime}"+
	    		    	       "#if(:1.antispamStatus!=null){,antispam_status=:1.antispamStatus}"+
	    		    	       "#if(:1.antispamMid!=null){,antispam_mid=:1.antispamMid}"+
	    		    	       "#if(:1.client!=null){,client=:1.client}"+
	    	    " WHERE id=:1.id ")
	void update(QuestionCopy questioncopy );
	
	@SQL("DELETE FROM question_copy WHERE id= :1")
	int delete(Long id);
	
	@SQL("SELECT COUNT(1) FROM question_copy")
	int count();
	
}