package sinyj0622.mytrip.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.domain.Board;

public class BoardObjectDao {

	String filename;
	List<Board> list;

	public BoardObjectDao(String filename) {
		this.filename = filename;
		this.list = new ArrayList<>();
		loadData();
	}

	@SuppressWarnings("unchecked")
	public void loadData() {

		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {

			list = (List<Board>) in.readObject();

			System.out.printf("총 %d개의 게시글 데이터를 로딩했습니다.\n", list.size());

		} catch (Exception e) {
			System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
		}

	}

	public void saveData() {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(filename)))) {

			out.writeObject(list);

			System.out.printf("총 %d개의 게시글을 저장하였습니다.\n", list.size());

		} catch (IOException e) {
			System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

		}
	}

	public int insert(Board board) throws Exception {

		if (indexOf(board.getNo()) > -1) { // 같은 번호의 게시물이 있다면,
			return 0;
		}

		list.add(board); // 새 게시물을 등록한다.
		saveData();
		return 1;
	}

	public List<Board> findAll() throws Exception {
		return list;
	}

	public Board findByNo(int no) throws Exception {
		int index = indexOf(no);
		if (index == -1) {
			return null;
		}
		return list.get(index);
	}

	public int update(Board board) throws Exception {
		int index = indexOf(board.getNo());

		if (index == -1) {
			return 0;
		}

		list.set(index, board); 
		saveData();
		return 1;
	}

	public int delete(int no) throws Exception {
		int index = indexOf(no);
		if (index == -1) {
			return 0;
		}

		list.remove(index); 
		saveData(); 
		return 1;

	}

	private int indexOf(int no) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNo() == no) {
				return i;
			}
		}
		return -1;
	}

}
