package sinyj0622.mytrip.service;

import java.util.HashMap;
import java.util.List;

import sinyj0622.mytrip.domain.Plan;

public interface PlanService {

	Plan get(int planNo) throws Exception;

	int add(Plan plan) throws Exception;

	int delete(int no) throws Exception;

	List<Plan> list() throws Exception;

	List<Plan> findByKeyword(HashMap<String, Object> params) throws Exception;

	int update(Plan newPlan)  throws Exception;



}
