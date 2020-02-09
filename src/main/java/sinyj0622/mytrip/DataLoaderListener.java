package sinyj0622.mytrip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.BoardObjectDao;
import sinyj0622.mytrip.dao.MemberObjectDao;
import sinyj0622.mytrip.dao.PlanObjectDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.domain.Plan;

public class DataLoaderListener implements ApplicationContextListener {

	
	@Override
	public void contextInitialized(Map<String, Object> context) {
		System.out.println("데이터를 로딩하였습니다.");
		
		
		BoardObjectDao boardDao = new BoardObjectDao("./board.ser2");
		MemberObjectDao memberDao = new MemberObjectDao("./member.ser2");
		PlanObjectDao planDao = new PlanObjectDao("./plan.ser2");
		
		context.put("boardDao", boardDao);
		context.put("memberDao", memberDao);
		context.put("planDao", planDao);
		

	}

	@Override
	public void contextDestroyed(Map<String, Object> context) {
		System.out.println("데이터를 저장하였습니다.");
		
	}
	
}
