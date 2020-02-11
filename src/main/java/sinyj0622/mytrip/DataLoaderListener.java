package sinyj0622.mytrip;

import java.util.Map;
import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.dao.json.BoardJsonFileDao;
import sinyj0622.mytrip.dao.json.MemberJsonFileDao;
import sinyj0622.mytrip.dao.json.PlanJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {


  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩하였습니다.");


    BoardDao boardDao = new BoardJsonFileDao("./board.json");
    MemberDao memberDao = new MemberJsonFileDao("./member.json");
    PlanDao planDao = new PlanJsonFileDao("./plan.json");

    context.put("boardDao", boardDao);
    context.put("memberDao", memberDao);
    context.put("planDao", planDao);


  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장하였습니다.");

  }

}
