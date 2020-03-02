package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.sql.DataSource;

public class BoardDaoImpl implements BoardDao {

	DataSource dataSource;

	public BoardDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insert(Board board) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("insert into mytrip_board(conts) values(?)")){
			stmt.setString(1, board.getText());
			return stmt.executeUpdate();
		}
	}

	@Override
	public List<Board> findAll() throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_board")){
			ResultSet rs = stmt.executeQuery();

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
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_board where board_id=?")){
				stmt.setInt(1, no);
			try (ResultSet rs = stmt.executeQuery()){

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
	}

	@Override
	public int update(Board board) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("update mytrip_board set conts=? where board_id=?")){
				stmt.setString(1, board.getText());
				stmt.setInt(2, board.getNo());
				
			return stmt.executeUpdate();
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("delete from mytrip_board where board_id=?")){
				stmt.setInt(1, no);
				
				return stmt.executeUpdate();
		}
	}

}
