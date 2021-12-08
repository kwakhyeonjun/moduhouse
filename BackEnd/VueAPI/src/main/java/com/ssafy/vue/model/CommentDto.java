package com.ssafy.vue.model;

public class CommentDto {
	
	private int commentno;
	private int articleno;
	private String content;
	private String userid;
	private String regtime;
	
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}	
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	@Override
	public String toString() {
		return "CommentDto [commentno=" + commentno + ", articleno=" + articleno + ", content=" + content + ", userid="
				+ userid + "]";
	}
}
