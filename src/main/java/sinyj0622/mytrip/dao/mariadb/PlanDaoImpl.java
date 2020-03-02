package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.sql.DataSource;

public class PlanDaoImpl implements PlanDao {

	DataSource dataSource;

	public PlanDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insert(Plan plan) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("insert into mytrip_plan(spot, titl, person, sdt, edt, money) values(?,?,?,?,?,?)")){
			stmt.setString(1, plan.getDestnation());
			stmt.setString(2, plan.getTravelTitle());
			stmt.setString(3, plan.getPerson());
			stmt.setString(4, plan.getStartDate());
			stmt.setString(5, plan.getEndDate());
			stmt.setString(6, plan.getTravelMoney());

			return stmt.executeUpdate();
		}
	}

	@Override
	public List<Plan> findAll() throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_plan")){
			ResultSet rs = stmt.executeQuery();

			ArrayList<Plan> list = new ArrayList<>();

			while(rs.next()) {
				Plan plan = new Plan();
				plan.setNo(rs.getInt("plan_id"));
				plan.setDestnation(rs.getString("spot"));
				plan.setTravelTitle(rs.getString("titl"));
				plan.setPerson(rs.getString("person"));
				plan.setStartDate(rs.getString("sdt"));
				plan.setEndDate(rs.getString("edt"));
				plan.setTravelMoney(rs.getString("money"));
				list.add(plan);
			}
			return list;
		}
	}

	@Override
	public Plan findByNo(int no) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from mytrip_plan where plan_id=?")){
			stmt.setInt(1, no);
			try (ResultSet rs = stmt.executeQuery()){

				if (rs.next()) {
					Plan plan = new Plan();
					plan.setNo(rs.getInt("plan_id"));
					plan.setTravelTitle(rs.getString("titl"));
					plan.setDestnation(rs.getString("spot"));
					plan.setPerson(rs.getString("person"));
					plan.setStartDate(rs.getString("sdt"));
					plan.setEndDate(rs.getString("edt"));
					plan.setTravelMoney(rs.getString("money"));
					return plan;
				} else {
					return null;
				}
			}
		}
	}

	@Override
	public int update(Plan plan) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("update mytrip_plan"
						+ " set titl=?,spot=?,person=?,sdt=?,edt=?,money=? where plan_id=?")){
			stmt.setString(1, plan.getTravelTitle());
			stmt.setString(2, plan.getDestnation());
			stmt.setString(3, plan.getPerson());
			stmt.setString(4, plan.getStartDate());
			stmt.setString(5, plan.getEndDate());
			stmt.setString(6, plan.getTravelMoney());
			stmt.setInt(7, plan.getNo());
			
			return stmt.executeUpdate();
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (Connection con = dataSource.getConnection();
				PreparedStatement stmt = con.prepareStatement("delete from mytrip_plan where plan_id=?")){
			stmt.setInt(1, no);
			return stmt.executeUpdate();
		}
	}


}
