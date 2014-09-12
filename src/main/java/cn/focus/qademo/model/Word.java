package cn.focus.qademo.model;

public class Word {

	private String name;
	private String nature;
	private Integer count;
	
	
	public Word(String name, String nature, Integer count) {
		super();
		this.name = name;
		this.nature = nature;
		this.count = count;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
