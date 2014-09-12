package cn.focus.qademo.service;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    List<Term> parse = BaseAnalysis.parse("让战士们过一个欢乐祥和的新春佳节。");
	    System.out.println(parse);
	}

}
