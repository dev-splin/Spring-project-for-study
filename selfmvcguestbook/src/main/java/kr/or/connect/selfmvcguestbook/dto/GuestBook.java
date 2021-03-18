package kr.or.connect.selfmvcguestbook.dto;

import java.util.Date;

public class GuestBook {
	private int id;
	private String name;
	private String content;
	private Date regdate;
	
	
	
	public GuestBook() {}
	public GuestBook(String name, String content) {
		this.name = name;
		this.content = content;
		this.regdate = new Date();
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "GuestBook [id=" + id + ", name=" + name + ", content=" + content + ", regdate=" + regdate + "]";
	}
	
	
}
