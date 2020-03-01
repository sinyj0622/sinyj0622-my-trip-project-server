package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {
	
	Connection con;
	
	public PhotoFileDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public int insert(PhotoFile photoFile) throws Exception {
		try (Statement stmt = con.createStatement()){
			int result = stmt.executeUpdate("insert into mytrip_photo_file(plan_id,file_path)"
					+ " values(" + photoFile.getBoardNo()
					+ ",'" + photoFile.getFilepath()
					+ "')" );

			return result;
		}
	}

	@Override
	public PhotoFile findByNo(int no) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(PhotoFile photoFile) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PhotoFile> findAllByPlanNo(int planNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
