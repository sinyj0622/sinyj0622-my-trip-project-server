package sinyj0622.mytrip.service.Impl;

import java.util.HashMap;
import java.util.List;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;

public class PlanServiceImpl implements PlanService{

	PlanDao planDao;

	public PlanServiceImpl(PlanDao planDao) {
		this.planDao = planDao;
	}

	@Override
	public Plan get(int planNo) throws Exception {
		return planDao.findByNo(planNo);
	}

	@Override
	public int add(Plan plan) throws Exception {
		return planDao.insert(plan);
	}

	@Override
	public int delete(int no) throws Exception {
		return planDao.delete(no);
	}

	@Override
	public List<Plan> list() throws Exception {
		return planDao.findAll();
	}

	@Override
	public List<Plan> findByKeyword(HashMap<String, Object> params) throws Exception {
		return planDao.findByKeyword(params);
	}

	@Override
	public int update(Plan newPlan) throws Exception {
		return planDao.update(newPlan);
	}
}
