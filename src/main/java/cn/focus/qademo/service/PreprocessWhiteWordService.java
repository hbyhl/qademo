package cn.focus.qademo.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.util.FilterModifWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.focus.qademo.dao.WhiteWordDAO;
import cn.focus.qademo.model.WhiteWord;
import cn.focus.qademo.model.vo.Word;
import cn.focus.qademo.util.HtmlFilter;
import cn.focus.qademo.util.QuestionParse;

@Service
public class PreprocessWhiteWordService {
	private List<String> docs = new ArrayList<String>(); // 存储所有的文本内容
	private List<String> titles = new ArrayList<String>();// 存储所有的标题内容
	private Map<String, Word> allWordsMap = new HashMap<String, Word>();
	//private List<StringBuffer> wordsList = new ArrayList<StringBuffer>();
	private int titleWeight = 1;
	private String inputData = "QUESTION";
	private String output = "OUTPUT";
	@Autowired
	private WhiteWordDAO whiteWordDAO;
	public static final int getNum = 10;
	
	public static String separator = File.separator;
	

	public PreprocessWhiteWordService() {
	}

	public PreprocessWhiteWordService(int titleWeight) {
		this.titleWeight = titleWeight;
	}

	public void demo() {
		readFolder(inputData);
		initStopWordsAndStopNatures();
		analysisStr();
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
		FilterModifWord.insertStopNatures(" ");
		FilterModifWord.insertStopNatures("t");
		FilterModifWord.insertStopNatures("tg");
		FilterModifWord.insertStopNatures("s");
		FilterModifWord.insertStopNatures("f");
		FilterModifWord.insertStopNatures("vshi");
		FilterModifWord.insertStopNatures("vyou");
		FilterModifWord.insertStopNatures("a");
		FilterModifWord.insertStopNatures("ad");
		FilterModifWord.insertStopNatures("an");
		FilterModifWord.insertStopNatures("ag");
		FilterModifWord.insertStopNatures("al");
		FilterModifWord.insertStopNatures("b");
		FilterModifWord.insertStopNatures("bl");
		FilterModifWord.insertStopNatures("z");
		FilterModifWord.insertStopNatures("r");
		FilterModifWord.insertStopNatures("rr");
		FilterModifWord.insertStopNatures("rz");
		FilterModifWord.insertStopNatures("rzt");
		FilterModifWord.insertStopNatures("rzs");
		FilterModifWord.insertStopNatures("rzv");
		FilterModifWord.insertStopNatures("ry");
		FilterModifWord.insertStopNatures("ryt");
		FilterModifWord.insertStopNatures("rys");
		FilterModifWord.insertStopNatures("ryv");
		FilterModifWord.insertStopNatures("rg");
		FilterModifWord.insertStopNatures("m");
		FilterModifWord.insertStopNatures("mq");
		FilterModifWord.insertStopNatures("q");
		FilterModifWord.insertStopNatures("qv");
		FilterModifWord.insertStopNatures("qt");
		FilterModifWord.insertStopNatures("d");
		FilterModifWord.insertStopNatures("p");
		FilterModifWord.insertStopNatures("pba");
		FilterModifWord.insertStopNatures("pbei");
		FilterModifWord.insertStopNatures("c");
		FilterModifWord.insertStopNatures("cc");
		FilterModifWord.insertStopNatures("u");
		FilterModifWord.insertStopNatures("uzhe");
		FilterModifWord.insertStopNatures("ule");
		FilterModifWord.insertStopNatures("uguo");
		FilterModifWord.insertStopNatures("ude1");
		FilterModifWord.insertStopNatures("ude2");
		FilterModifWord.insertStopNatures("ude3");
		FilterModifWord.insertStopNatures("usuo");
		FilterModifWord.insertStopNatures("udeng");
		FilterModifWord.insertStopNatures("uyy");
		FilterModifWord.insertStopNatures("udh");
		FilterModifWord.insertStopNatures("uls");
		FilterModifWord.insertStopNatures("uzhi");
		FilterModifWord.insertStopNatures("ulian");
		FilterModifWord.insertStopNatures("e");
		FilterModifWord.insertStopNatures("y");
		FilterModifWord.insertStopNatures("o");
		FilterModifWord.insertStopNatures("h");
		FilterModifWord.insertStopNatures("k");
		FilterModifWord.insertStopNatures("x");
		FilterModifWord.insertStopNatures("xx");
		FilterModifWord.insertStopNatures("xu");
		FilterModifWord.insertStopNatures("w");
		FilterModifWord.insertStopNatures("wkz");
		FilterModifWord.insertStopNatures("wky");
		FilterModifWord.insertStopNatures("wyz");
		FilterModifWord.insertStopNatures("wyy");
		FilterModifWord.insertStopNatures("wj");
		FilterModifWord.insertStopNatures("ww");
		FilterModifWord.insertStopNatures("wt");
		FilterModifWord.insertStopNatures("wd");
		FilterModifWord.insertStopNatures("wf");
		FilterModifWord.insertStopNatures("wn");
		FilterModifWord.insertStopNatures("wm");
		FilterModifWord.insertStopNatures("ws");
		FilterModifWord.insertStopNatures("wp");
		FilterModifWord.insertStopNatures("wb");
		FilterModifWord.insertStopNatures("wh");
		FilterModifWord.insertStopNatures("en");
		FilterModifWord.insertStopNatures("nr1");
		FilterModifWord.insertStopNatures("vd");
		FilterModifWord.insertStopNatures("vf");
		FilterModifWord.insertStopNatures("vx");
		FilterModifWord.insertStopNatures("vi");
		System.out.println("init stopwords and natures success!!");
	}

	public void analysisStr() {

		System.out.println("analysising　strs start");

		//List<List<Term>> tmpTermList = new ArrayList<List<Term>>();

		for (String str : titles) {
		   
			List<Term> parse = IndexAnalysis.parse(str);
			List<Term> terms = FilterModifWord.modifResult(parse);
			//StringBuffer wordstr = new StringBuffer();
			//tmpTermList.add(terms);
			//Map<String, Word> m = new HashMap<String, Word>();
			/*for(Term p: parse){
				String name = p.getRealName();
				 
				wordstr.append(name+"\\");
			}
			*/
			for (Term t : terms) {
				String name = t.getRealName();
				String nature = t.getNatureStr();
				 
				
				if (allWordsMap.containsKey(name)) {
					Word w = new Word(name, nature, allWordsMap.get(name).getCount()
							+ titleWeight);
					allWordsMap.put(name, w);
				} else {
					Word w = new Word(name, nature, titleWeight);
					allWordsMap.put(name, w);
				}
				
				
			}
			//wordsList.add(wordstr);
			
		}
		titles.clear();

		for (int i = 0; i < docs.size(); i++) {
			String str = docs.get(i);
			List<Term> parse = IndexAnalysis.parse(str);
			List<Term> terms = FilterModifWord.modifResult(parse);
			//tmpTermList.add(terms);

			//Map<String, Word> m = wordsMaps.get(i);
			//StringBuffer wordstr = wordsList.get(i);
			/*
			for(Term p:parse){
				String name = p.getRealName();
				 
				wordstr.append(name+"\\");
			}
			*/
			for (Term t : terms) {
				String name = t.getRealName();
				String nature = t.getNatureStr();
				 
				if (allWordsMap.containsKey(name)) {
					Word w = new Word(name, nature, allWordsMap.get(name).getCount()
							+ 1);
					allWordsMap.put(name, w);
				} else {
					Word w = new Word(name, nature, 1);
					allWordsMap.put(name, w);
				}
			}
		}
		docs.clear();

		/*
		String wordsMapsResult = "";
		StringBuffer sb = new StringBuffer();
		for (StringBuffer str : wordsList) {
			sb.append(str.toString() + "\n");

		}
		wordsMapsResult = sb.toString();
		*/
		
		List<Map.Entry<String, Word>> mappingList = null;

		// 通过ArrayList构造函数把map.entrySet()转换成list
		mappingList = new ArrayList<Map.Entry<String, Word>>(
				allWordsMap.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList,
				new Comparator<Map.Entry<String, Word>>() {
					public int compare(Map.Entry<String, Word> mapping1,
							Map.Entry<String, Word> mapping2) {
						return mapping2.getValue().getCount()
								.compareTo(mapping1.getValue().getCount());
					}
				});

		String allWordsMapResult = "";
		StringBuffer sb = new StringBuffer();

		for (Map.Entry<String, Word> mapping : mappingList) {
			Word w = mapping.getValue();
			if (w.getNature().equals("userDefine")) {
				continue;
			}
			sb.append((w.getName() + "\t" + w.getCount() + "\t" + w.getNature() + "\n"));
		}

		allWordsMapResult = sb.toString();

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(output + "/allWordsMap"));
			writer.write(allWordsMapResult);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		 

		System.out.println("analysising　strs end");

	}

	/**
	 * 读取文件夹
	 * 
	 * @return
	 */
	public void readFolder(String filePath) {
		try {
			// 读取指定文件夹下的所有文件
			File file = new File(filePath);
			if (!file.isDirectory()) {
				System.out.println("---------- 该文件不是一个目录文件 ----------");
			} else if (file.isDirectory()) {
				System.out.println("---------- 这是一个目录文件夹 ----------");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filePath + separator + filelist[i]);
					String absolutepath = readfile.getAbsolutePath();// 文件的绝对路径
					String filename = readfile.getName();// 读到的文件名
					readFile(absolutepath, filename, i);// 调用readFile方法读取文件夹下所有文件
					 
				}
				System.out.println("---------- 所有文件操作完毕 ----------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文件夹下的文件
	 * 
	 * @return
	 */
	public void readFile(String absolutepath, String filename, int index) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(absolutepath)));

			String s = null;
			//int i=0;
			while ((s = br.readLine()) != null) {
				//i++;
				//if(i>4){break;}
				if (s.startsWith("#title#")) {
					titles.add(HtmlFilter.htmlFilter(s.substring(7)));
				}
//				if (s.startsWith("#context#")) {
//					docs.add(HtmlFilter.htmlFilter(s.substring(9)));
//				}

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  String[][] getWhiteWordList() {
//		ArrayList<String> list_notype = new ArrayList<String>();
    	
    	String lastUpdateTime = "2014-01-01 00:00:00";
		String whiteWordList[][] = new String[11][getNum];		
		QuestionParse questionParse = new QuestionParse();
		
		for(int type=1;type<=10;type++){			
			questionParse.getQuestion(type,lastUpdateTime);    //从数据库中读取相应type的问题
			new PreprocessWhiteWordService().demo();
			
			questionParse.getWhiteWord(whiteWordList,type,getNum);   //获取要添加到白词库中的该type白词
			
		}
		
		//二维数组存储频率较高的白词，地一维度表示问题类别,与question_type表一致
		//例如，其中whiteWordList[1][]表示法律政策......
		for(int i=1;i<11;i++){
			for(int j=0;j<getNum;j++){
			    List<WhiteWord> temp = whiteWordDAO.executeQuery(whiteWordList[i][j]);
			    if(temp!=null && temp.size()>0)
			    {
			        whiteWordList[i][j]= null;
			    }
			}
			System.out.println();
		}
		
		return whiteWordList;
		
	//	questionParse.saveWhiteWord(whiteWordList, 10, getNum);
				
	}
}
