package sinyj0622.mytrip.dao;

import java.util.List;
import java.util.Map;
import sinyj0622.mytrip.domain.Plan;

public interface PlanDao {

  int insert(Plan plan) throws Exception;

  List<Plan> findAll() throws Exception;

  Plan findByNo(int no) throws Exception;

  int update(Plan plan) throws Exception;

  int delete(int no) throws Exception;

  default List<Plan> findByKeyword(Map<String, Object> params) {
    return null;
  }
}
