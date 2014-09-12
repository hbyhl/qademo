package cn.focus.qademo.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class QuestionParse {
	/**
	 * 连接数据库，并从中读取问题信息写到文本中
	 */
	public void getQuestion(int type,String lastUpdateTime){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://10.10.90.156:3306/app_xinfang";
		String username = "develop";
		String password = "p3m12d";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			st = conn.createStatement();
		//	List<String> sqlList = new ArrayList<String>();
			
		//	rs = st.executeQuery("select * from question");
//			String sql = "select * from question where status=6 and "
//					+ "is_answered=1 and type="+type+" and create_time>='"+lastUpdateTime+"'";
//			System.out.println(sql);
			rs = st.executeQuery("select * from question where status=6 and "
					+ "is_answered=1 and type="+type+" and create_time>='"+lastUpdateTime+"'");
		     
			File writeFile = new File("QUESTION/question.txt");
			try {
				PrintWriter writer = new PrintWriter(new FileWriter(writeFile));
				//处理结果集
			    while (rs.next())
			    {
			      String question = rs.getString("question");
			      writer.println("#title#"+question);
			     // System.out.println(id+" "+question);
			    }
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection(conn,st,rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @param st
	 * @param rs
	 */
	private void closeConnection(Connection conn, Statement st, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从文件中读取要加入白词库的白词
	 * getNum表示要获取频率最高的getNum个白词
	 */
	public void getWhiteWord(String list[][],int row,int getNum){
		File file = new File("OUTPUT/allWordsMap");
		try {  
		    BufferedReader reader = new BufferedReader(new FileReader(file));  
		    String line = reader.readLine();
		    int num = 0;
		    //获取前十行数据
		    while(line!=null && num<getNum){
		    	String[] vals = line.split("(" + (char)32 + "|" + (char)9 + ")+");
		    	list[row][num] = vals[0];
		    	num++;
		        line = reader.readLine();  
		    }  
		    reader.close();  
		} catch (FileNotFoundException e) {  
		    e.printStackTrace();  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}  
	}
	
	/**
	 * 将得到频率较高的白词存到数据库中
	 */
	public void saveWhiteWord(String list[][],int typeNum,int getNum){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://10.10.90.156:3306/app_xinfang";
		String username = "develop";
		String password = "p3m12d";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			st = conn.createStatement();
			
			String sql = "";
			//question_type中type与white_word中相应类别的对应关系
			int type_key[] = {0,442,391,459,332,460,346,367,434,406,461};
			
			Calendar calendar = Calendar.getInstance();
	        Date date = (Date) calendar.getTime();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String time = format.format(date);
	       // System.out.println(time);
	        
	        for(int i=1;i<=typeNum;i++){
				for(int j=0;j<getNum;j++){
					rs = st.executeQuery("select * from white_word where name='"+list[i][j]+"'");
				    //若之前白词库中不存在此白词，则添加到白词库中
					if(!rs.first()){
				    	sql = "insert into white_word (parent_id,name,create_time) values ("
						           +type_key[i]+",'"+list[i][j]+"','"+time+"')";
				    	System.out.println(sql);
				    	try{
							st.executeUpdate(sql);
						}catch(Exception e){
							System.out.println("Sql = " + sql);
							System.out.println("Exception: " + e.getMessage());
						}
				    }					
				}		
			}	        
			closeConnection(conn,st,rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}