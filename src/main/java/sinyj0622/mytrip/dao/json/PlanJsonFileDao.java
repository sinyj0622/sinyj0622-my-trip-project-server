package sinyj0622.mytrip.dao.json;

import java.util.List;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanJsonFileDao extends AbstractJsonFileDao<Plan> implements PlanDao {

  public PlanJsonFileDao(String filename) {
    super(filename);
  }

  @Override
  public int insert(Plan plan) throws Exception {

    if (indexOf(plan.getNo()) > -1) { // 같은 번호의 게시물이 있다면,
      return 0;
    }

    list.add(plan); // 새 게시물을 등록한다.
    saveData();
    return 1;
  }

  @Override
  public List<Plan> findAll() throws Exception {
    return list;
  }

  @Override
  public Plan findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  @Override
  public int update(Plan plan) throws Exception {
    int index = indexOf(plan.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, plan);
    saveData();
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();
    return 1;

  }


  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) {
        return i;
      }
    }
    return -1;
  }
}