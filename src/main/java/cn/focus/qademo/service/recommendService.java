package cn.focus.qademo.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Iterator;
import java.util.List;


import org.ansj.domain.Term;
import org.ansj.recognition.NatureRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import cn.focus.qademo.dao.QuestionDAO;
import cn.focus.qademo.model.Question;
import cn.focus.qademo.util.questionCompare;

@Service
public class recommendService {
    @Autowired
    private QuestionDAO questionDAO;

	/**
	 * @param args
	 */
	public List<Question> demo(String newstr,long buildid,int type) {
	
		initStopWordsAndStopNatures();
		List<Question> sort =analysisStr(newstr,buildid,type);
		return sort;
	}
	public void initStopWordsAndStopNatures() {

		List<String> stopWord = new ArrayList();

		System.out.println("init stopwords and natures");
		// 停词表
		String stopWordFile = "stopword.txt";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(stopWordFile), "UTF-8"));
			String line = reader.readLine();

			while ((line = reader.readLine()) != null) {
				stopWord.add(line);
			}
		} catch (Exception e) {
			System.out.println("创建停词表失败");
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FilterModifWord.insertStopWords(stopWord);
		stopWord.clear();
//		FilterModifWord.insertStopNatures(" ");
//		FilterModifWord.insertStopNatures("t");
//		FilterModifWord.insertStopNatures("tg");
//		FilterModifWord.insertStopNatures("s");
//		FilterModifWord.insertStopNatures("f");
//		FilterModifWord.insertStopNatures("vshi");
//		FilterModifWord.insertStopNatures("vyou");
//		FilterModifWord.insertStopNatures("a");
//		FilterModifWord.insertStopNatures("ad");
//		FilterModifWord.insertStopNatures("an");
//		FilterModifWord.insertStopNatures("ag");
//		FilterModifWord.insertStopNatures("al");
//		FilterModifWord.insertStopNatures("b");
//		FilterModifWord.insertStopNatures("bl");
//		FilterModifWord.insertStopNatures("z");
//		FilterModifWord.insertStopNatures("r");
//		FilterModifWord.insertStopNatures("rr");
//		FilterModifWord.insertStopNatures("rz");
//		FilterModifWord.insertStopNatures("rzt");
//		FilterModifWord.insertStopNatures("rzs");
//		FilterModifWord.insertStopNatures("rzv");
//		FilterModifWord.insertStopNatures("ry");
//		FilterModifWord.insertStopNatures("ryt");
//		FilterModifWord.insertStopNatures("rys");
//		FilterModifWord.insertStopNatures("ryv");
//		FilterModifWord.insertStopNatures("rg");
//		FilterModifWord.insertStopNatures("m");
//		FilterModifWord.insertStopNatures("mq");
//		FilterModifWord.insertStopNatures("q");
//		FilterModifWord.insertStopNatures("qv");
//		FilterModifWord.insertStopNatures("qt");
//		FilterModifWord.insertStopNatures("d");
//		FilterModifWord.insertStopNatures("p");
//		FilterModifWord.insertStopNatures("pba");
//		FilterModifWord.insertStopNatures("pbei");
//		FilterModifWord.insertStopNatures("c");
//		FilterModifWord.insertStopNatures("cc");
//		FilterModifWord.insertStopNatures("u");
//		FilterModifWord.insertStopNatures("uzhe");
//		FilterModifWord.insertStopNatures("ule");
//		FilterModifWord.insertStopNatures("uguo");
//		FilterModifWord.insertStopNatures("ude1");
//		FilterModifWord.insertStopNatures("ude2");
//		FilterModifWord.insertStopNatures("ude3");
//		FilterModifWord.insertStopNatures("usuo");
//		FilterModifWord.insertStopNatures("udeng");
//		FilterModifWord.insertStopNatures("uyy");
//		FilterModifWord.insertStopNatures("udh");
//		FilterModifWord.insertStopNatures("uls");
//		FilterModifWord.insertStopNatures("uzhi");
//		FilterModifWord.insertStopNatures("ulian");
//		FilterModifWord.insertStopNatures("e");
//		FilterModifWord.insertStopNatures("y");
//		FilterModifWord.insertStopNatures("o");
//		FilterModifWord.insertStopNatures("h");
//		FilterModifWord.insertStopNatures("k");
//		FilterModifWord.insertStopNatures("x");
//		FilterModifWord.insertStopNatures("xx");
//		FilterModifWord.insertStopNatures("xu");
//		FilterModifWord.insertStopNatures("w");
//		FilterModifWord.insertStopNatures("wkz");
//		FilterModifWord.insertStopNatures("wky");
//		FilterModifWord.insertStopNatures("wyz");
//		FilterModifWord.insertStopNatures("wyy");
//		FilterModifWord.insertStopNatures("wj");
//		FilterModifWord.insertStopNatures("ww");
//		FilterModifWord.insertStopNatures("wt");
//		FilterModifWord.insertStopNatures("wd");
//		FilterModifWord.insertStopNatures("wf");
//		FilterModifWord.insertStopNatures("wn");
//		FilterModifWord.insertStopNatures("wm");
//		FilterModifWord.insertStopNatures("ws");
//		FilterModifWord.insertStopNatures("wp");
//		FilterModifWord.insertStopNatures("wb");
//		FilterModifWord.insertStopNatures("wh");
//		FilterModifWord.insertStopNatures("en");
//		FilterModifWord.insertStopNatures("nr1");
//		FilterModifWord.insertStopNatures("vd");
//		FilterModifWord.insertStopNatures("vf");
//		FilterModifWord.insertStopNatures("vx");
//		FilterModifWord.insertStopNatures("vi");
		System.out.println("init stopwords and natures success!!");
	}
	
	
	public List<Question> analysisStr(String newstr,long buildid,int type) {
		List<Term> n1 = ToAnalysis.parse(newstr) ;
		new NatureRecognition(n1).recognition() ;
		List<Term> n2=FilterModifWord.modifResult(n1) ;
		System.out.println(n1);
		System.out.println("OK\n");
		System.out.println(n2);
		System.out.println("OK\n");
		List<Question> questions=questionDAO.getBuildInfo(buildid,type);
		Iterator<Question> it=questions.iterator();
		while(it.hasNext()){
			int price=0;
			Question tmp=it.next();
			String q=tmp.getQuestion();
			List<Term> parse = ToAnalysis.parse(q) ;
			System.out.println(parse);
			List<Term> parse2=FilterModifWord.modifResult(parse) ;
			System.out.println(parse2);
			for(int j=0;j<n2.size();j++){
				for(int i=0;i<parse2.size();i++){
					if(n2.get(j).getName().equals(parse2.get(i).getName())){						
						price++;
						break;
					}											
				}

			}
			tmp.setPrice(price);
		}
		  //创建比较器对象
		  questionCompare comp=new questionCompare();
		  //调用排序方法
		  Collections.sort(questions,comp);
		  return questions;		
	}



}
