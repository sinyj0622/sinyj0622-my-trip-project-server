package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {
	
	String jdbcUrl;
	String usename;
	String password;
	
	public PhotoFileDaoImpl(String jdbcUrl, String usename, String password) {
		this.jdbcUrl = jdbcUrl;
		this.usename = usename;
		this.password = password;
	}

	@Override
	public int insert(PhotoFile photoFile) throws Exception {
		try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
				Statement stmt = con.createStatement()){
			int result = stmt.executeUpdate("insert into mytrip_photo_file(photo_id,file_path)"
					+ " values(" + photoFile.getBoardNo()
					+ ",'" + photoFile.getFilepath()
					+ "')" );

			return result;
		}
	}

	@Override
	public List<PhotoFile> findAll(int boardNo) throws Exception {
		try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
				Statement stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery("select photo_file_id, photo_id, file_path" //
		            + " from mytrip_photo_file" //
		            + " where photo_id=" + boardNo //
		            + " order by photo_file_id asc")) {

		      ArrayList<PhotoFile> list = new ArrayList<>();

		      while (rs.next()) {

		        list.add(new PhotoFile().setNo(rs.getInt("photo_file_id")) //
		            .setFilepath(rs.getString("file_path")) //
		            .setBoardNo(rs.getInt("photo_id"))); //
		      }

		      return list;

		    }
	}

	@Override
	public int deleteAll(int boardNo) throws Exception {
	    try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
	    		Statement stmt = con.createStatement()) {
	        int result = stmt.executeUpdate( //
	            "delete from mytrip_photo_file" //
	                + " where photo_id=" + boardNo);
	        return result;
	      }
	}

}
