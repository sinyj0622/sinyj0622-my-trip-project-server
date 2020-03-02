package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.sql.DataSource;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

	DataSource dataSource;

	public PhotoBoardDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<PhotoBoard> findAllByPlanNo(int planNo) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_photo where plan_id=?")){
			stmt.setInt(1, planNo);
			try (ResultSet rs = stmt.executeQuery()){

				ArrayList<PhotoBoard> list = new ArrayList<>();

				while(rs.next()) {
					PhotoBoard photoBoard = new PhotoBoard();
					photoBoard.setNo(rs.getInt("photo_id"));
					photoBoard.setTitle(rs.getString("titl"));
					photoBoard.setCreatedDate(rs.getDate("cdt"));
					photoBoard.setViewCount(rs.getInt("vw_cnt"));
					list.add(photoBoard);
				}

				return list;
			}
		}
	}

	@Override
	public int insert(PhotoBoard photoBoard) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("insert into mytrip_photo(titl,plan_id) values(?,?)", PreparedStatement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, photoBoard.getTitle());
			stmt.setInt(2, photoBoard.getPlan().getNo());

			int result = stmt.executeUpdate();
			// PK의 값을 생성
			try (ResultSet generatedKey = stmt.getGeneratedKeys()){
				// PK을 가져온다
				generatedKey.next();

				photoBoard.setNo(generatedKey.getInt(1));
			}

			return result;
		}
	}

	@Override
	public PhotoBoard findByNo(int no) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_photo where photo_id=?")){
			stmt.setInt(1, no);

			try (ResultSet rs = stmt.executeQuery()){

				if (rs.next()) {
					PhotoBoard photoBoard = new PhotoBoard();
					photoBoard.setNo(rs.getInt("photo_id"));
					photoBoard.setTitle(rs.getString("titl"));
					photoBoard.setCreatedDate(rs.getDate("cdt"));
					photoBoard.setViewCount(rs.getInt("vw_cnt"));

					Plan plan = new Plan();
					plan.setNo(rs.getInt("plan_id"));
					photoBoard.setPlan(plan);
					return photoBoard;
				} else {
					return null;
				}
			}
		}
	}

	@Override
	public int update(PhotoBoard photoBoard) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("update mytrip_photo set titl=? where photo_id=?")){
			stmt.setString(1, photoBoard.getTitle());
			stmt.setInt(2, photoBoard.getNo());

			return stmt.executeUpdate();
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("delete from mytrip_photo where photo_id=?")){
			stmt.setInt(1, no);
			return stmt.executeUpdate();
		}
	}


}
