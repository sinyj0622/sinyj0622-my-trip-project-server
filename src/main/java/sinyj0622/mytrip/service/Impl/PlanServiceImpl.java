package sinyj0622.mytrip.service.Impl;

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
}
