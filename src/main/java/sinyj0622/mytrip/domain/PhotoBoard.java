package sinyj0622.mytrip.domain;

import java.util.Date;

public class PhotoBoard {

	int no;
	String title;
	Date createdDate;
	int viewCount;
	Plan plan;


	@Override
	public String toString() {
		return "PhotoBoard [no=" + no + ", title=" + title + ", createdDate=" + createdDate + ", viewCount=" + viewCount
				+ ", plan=" + plan + "]";
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}


}
