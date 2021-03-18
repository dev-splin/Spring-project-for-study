package kr.or.connect.selfmvcguestbook.dto;

import java.util.Date;

public class GuestBookLog {
	private int id;
	private String ip;
	private String method;
	private Date regdate;
	
	public GuestBookLog() {}
	public GuestBookLog(String ip, String method) {
		this.ip = ip;
		this.method = method;
		this.regdate = new Date();
	}
	
	public int getId() {
		return id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "GuestBookLog [id=" + id + ", ip=" + ip + ", method=" + method + ", regdate=" + regdate + "]";
	}
	
	
}
