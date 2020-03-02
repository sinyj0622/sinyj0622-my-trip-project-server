package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.sql.DataSource;

public class MemberDaoImpl implements MemberDao {

	DataSource dataSource;

	public MemberDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insert(Member member) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("insert into mytrip_member(name, nick, email, pwd, "
						+ "photo, tel, cdt) values(?,?,?,?,?,?,?)")){
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getNickname());
			stmt.setString(3, member.getEmail());
			stmt.setString(4, member.getPassWord());
			stmt.setString(5, member.getMyphoto());
			stmt.setString(6, member.getPhonenumber());
			stmt.setDate(7, member.getRegisteredDate());
			return stmt.executeUpdate();
		}
	}

	@Override
	public List<Member> findAll() throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_member")){
			ResultSet rs = stmt.executeQuery();

			ArrayList<Member> list = new ArrayList<>();

			while(rs.next()) {
				Member member = new Member();
				member.setNo(rs.getInt("member_id"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhonenumber(rs.getString("tel"));
				member.setRegisteredDate(rs.getDate("cdt"));
				list.add(member);
			}
			return list;
		}
	}

	@Override
	public Member findByNo(int no) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_member where member_id=?")){
			stmt.setInt(1, no);
			try(ResultSet rs = stmt.executeQuery()){

				if (rs.next()) {
					Member member = new Member();
					member.setNo(rs.getInt("member_id"));
					member.setName(rs.getString("name"));
					member.setNickname(rs.getString("nick"));
					member.setEmail(rs.getString("email"));
					member.setMyphoto(rs.getString("photo"));
					member.setPhonenumber(rs.getString("tel"));
					member.setRegisteredDate(rs.getDate("cdt"));
					return member;
				} else {
					return null;
				}
			}
		}
	}

	@Override
	public int update(Member member) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("update mytrip_member"
						+ " set name=?,nick=?,pwd=?,email=?,photo=?,tel=? where member_id=?")){

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getNickname());
			stmt.setString(3, member.getPassWord());
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getMyphoto());
			stmt.setString(6, member.getPhonenumber());
			stmt.setInt(7, member.getNo());
			return stmt.executeUpdate();
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("delete from mytrip_member where member_id=?")){
			stmt.setInt(1, no);
			return stmt.executeUpdate();
		}
	}

	@Override
	public List<Member> findByKeyword(String keyword) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select *"
						+ " from mytrip_member"
						+ " where name like ? or nick like ?")){
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			ArrayList<Member> list = new ArrayList<>();

			while(rs.next()) {
				Member member = new Member();
				member.setNo(rs.getInt("member_id"));
				member.setName(rs.getString("name"));
				member.setNickname(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setMyphoto(rs.getString("photo"));
				member.setRegisteredDate(rs.getDate("cdt"));
				list.add(member);
			}
			return list;
		}
	}

	@Override
	public Member findByEmailAndPassword(String email, String password) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_member"
						+ " where email=? and pwd=?")){
			stmt.setString(1, email);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()){

				if (rs.next()) {
					Member member = new Member();
					member.setNo(rs.getInt("member_id"));
					member.setName(rs.getString("name"));
					member.setNickname(rs.getString("nick"));
					member.setEmail(rs.getString("email"));
					member.setMyphoto(rs.getString("photo"));
					member.setPhonenumber(rs.getString("tel"));
					member.setRegisteredDate(rs.getDate("cdt"));
					return member;
				} else {
					return null;
				}
			}
		}
	}
}
