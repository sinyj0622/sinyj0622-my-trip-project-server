package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;

public class BoardDaoImpl implements BoardDao {

	Connection con;

	public BoardDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public int insert(Board board) throws Exception {
		try (Statement stmt = con.createStatement()){

			int result = stmt.executeUpdate("insert into mytrip_board(conts) values('"
					+ board.getText() + "')");

			return result;
		}
	}

	@Override
	public List<Board> findAll() throws Exception {
		try (Statement stmt = con.createStatement()){
			ResultSet rs = stmt.executeQuery("select * from mytrip_board");

			ArrayList<Board> list = new ArrayList<>();

			while(rs.next()) {
				Board board = new Board();
				board.setNo(rs.getInt("board_id"));
				board.setText(rs.getString("conts"));
				board.setDate(rs.getDate("cdt"));
				board.setViewCount(rs.getInt("vw_cnt"));
				list.add(board);
			}

			return list;
		}
	}

	@Override
	public Board findByNo(int no) throws Exception {
		try (Statement stmt = con.createStatement()){

			ResultSet rs = stmt.executeQuery("select * from mytrip_board where board_id=" + no );

			if (rs.next()) {
				Board board = new Board();
				board.setNo(rs.getInt("board_id"));
				board.setText(rs.getString("conts"));
				board.setDate(rs.getDate("cdt"));
				board.setViewCount(rs.getInt("vw_cnt"));
				return board;
			} else {
				return null;
			}
		}
	}

	@Override
	public int update(Board board) throws Exception {
		try (Statement stmt = con.createStatement()){

			int result = stmt.executeUpdate("update mytrip_board set conts='"
					+ board.getText() +"' where board_id='"
							+ board.getNo() + "'");
			
			return result;
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (Statement stmt = con.createStatement()){

			int result = stmt.executeUpdate("delete from mytrip_board where board_id=" + no);

			return result;
		}
	}

}
