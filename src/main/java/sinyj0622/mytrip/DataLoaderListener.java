package sinyj0622.mytrip;

import java.util.Map;
import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.json.BoardJsonFileDao;
import sinyj0622.mytrip.dao.json.MemberJsonFileDao;
import sinyj0622.mytrip.dao.json.PlanJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {


  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩하였습니다.");


    BoardJsonFileDao boardDao = new BoardJsonFileDao("./board.json");
    MemberJsonFileDao memberDao = new MemberJsonFileDao("./member.json");
    PlanJsonFileDao planDao = new PlanJsonFileDao("./plan.json");

    context.put("boardDao", boardDao);
    context.put("memberDao", memberDao);
    context.put("planDao", planDao);


  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장하였습니다.");

  }

}
