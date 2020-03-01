package sinyj0622.mytrip.domain;

import java.io.Serializable;

public class PhotoFile implements Serializable{

	private static final long serialVersionUID = 1L;

	int no;
	String filepath;
	int boardNo;
	
	@Override
	public String toString() {
		return "PhotoFile [no=" + no + ", filepath=" + filepath + ", boardNo=" + boardNo + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	
}
