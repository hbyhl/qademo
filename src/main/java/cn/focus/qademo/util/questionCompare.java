package cn.focus.qademo.util;

import java.util.Comparator;

import cn.focus.qademo.model.Question;

public class questionCompare  implements Comparator<Question>{

	/**
	 * @param args
	 */
	 @Override
	 public int compare(Question o1, Question o2) {
	  if(o1.getPrice()>o2.getPrice())
	   return -1;
	  else if(o1.getPrice()==o2.getPrice()){
	   if(o1.getId()>o2.getId())
	    return -1;
	   else
	    return 1;
	  }
	  else
	   return 1;
	 }

}
