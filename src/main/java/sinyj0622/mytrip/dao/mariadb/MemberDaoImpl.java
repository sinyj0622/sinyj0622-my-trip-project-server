package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;

public class MemberDaoImpl implements MemberDao {

	String jdbcUrl;
	String usename;
	String password;
	
	public MemberDaoImpl(String jdbcUrl, String usename, String password) {
		this.jdbcUrl = jdbcUrl;
		this.usename = usename;
		this.password = password;
	}

	@Override
	public int insert(Member member) throws Exception {
		try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
				Statement stmt = con.createStatement()){
			int result = stmt.executeUpdate("insert into mytrip_member(name, nick, email, pwd, "
					+ "photo, tel, cdt) values('" + member.getName()
					+ "', '" + member.getNickname()
					+ "', '" + member.getEmail()
					+ "', '" + member.getPassWord()
					+ "', '" + member.getMyphoto()
					+ "', '" + member.getPhonenumber()
					+ "', '" + member.getRegisteredDate()
					+ "')");

			return result;
		}
	}

	@Override
	public List<Member> findAll() throws Exception {
		try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
				Statement stmt = con.createStatement()){
			ResultSet rs = stmt.executeQuery("select * from mytrip_member");

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
		try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
				Statement stmt = con.createStatement()){
			ResultSet rs = stmt.executeQuery("select * from mytrip_member where member_id=" + no );

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

	@Override
	public int update(Member member) throws Exception {
		try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
				Statement stmt = con.createStatement()){

			int result = stmt.executeUpdate("update mytrip_member set name='" + member.getName()
			+ "',nick='"  + member.getNickname()
			+ "',pwd='" + member.getPassWord()
			+ "',email='" + member.getEmail()
			+ "',photo='" + member.getMyphoto()
			+ "',tel='" + member.getPhonenumber()
			+ "' where member_id=" + member.getNo());

			return result;
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
				Statement stmt = con.createStatement()){
			int result = stmt.executeUpdate("delete from mytrip_member where member_id=" + no);
			return result;
		}
	}
	
	@Override
	public List<Member> findByKeyword(String keyword) throws Exception {
		try (Connection con = DriverManager.getConnection(jdbcUrl,usename,password);
				Statement stmt = con.createStatement()){
			ResultSet rs = stmt.executeQuery("select *"
					+ " from mytrip_member"
					+ " where name like '%" + keyword
					+ "%' or nick like '%" + keyword
					+ "%'");

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
}
