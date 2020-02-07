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
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.domain.Plan;

public class DataLoaderListener implements ApplicationContextListener {

	
	List<Board> boardList = new ArrayList<>();
	List<Member> memberList = new LinkedList<>();
	List<Plan> planList = new ArrayList<>();
	
	
	@Override
	public void ContextInitialized(Map<String, Object> context) {
		System.out.println("데이터를 로딩하였습니다.");
		
		loadBoardData();
		loadMemberData();
		loadPlanData();
		
		context.put("boardList", boardList);
		context.put("memberList", memberList);
		context.put("planList", planList);
		

	}

	@Override
	public void ContextDestroyed(Map<String, Object> context) {
		System.out.println("데이터를 저장하였습니다.");
		
		saveBoardData();
		saveMemberData();
		savePlanData();
		
	}
	
	

	@SuppressWarnings("unchecked")
	public void loadBoardData() {

		try(ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("./board.ser2")))) {

			boardList =  (List<Board>) in.readObject();

			System.out.printf("총 %d개의 게시글 데이터를 로딩했습니다.\n", boardList.size());

		} catch (Exception e) {
			System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
		}  

	}


	public void saveBoardData() {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("./board.ser2")))) {

			out.writeObject(boardList);


			System.out.printf("총 %d개의 게시글을 저장하였습니다.\n", boardList.size());

		} catch (IOException e) {
			System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

		}
	}

	@SuppressWarnings("unchecked")
	public void loadMemberData() {

		try(ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("./member.ser2")))) {

			memberList = (List<Member>) in.readObject();

			System.out.printf("총 %d개의 회원 데이터를 로딩했습니다.\n", memberList.size() );

		} catch (Exception e) {
			System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
		} 

	}

	public void saveMemberData() {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("./member.ser2")))){

			out.writeObject(memberList);

			System.out.printf("총 %d개의 회원 데이터를 저장하였습니다.\n", memberList.size() );


		} catch (IOException e) {
			System.out.println("파일 저장 중 오류 발생! - " + e.getMessage());
		} 
	}

	@SuppressWarnings("unchecked")
	public void loadPlanData() {

		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("./plan.ser2")))) {

			planList = (List<Plan>) in.readObject();

			System.out.printf("총 %d개의 여행계획 데이터를 로딩했습니다.\n", planList.size());


		} catch (Exception e) {
			System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
		}  

	}

	public void savePlanData() {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("./plan.ser2")))) {

			out.writeObject(planList);

			System.out.printf("총 %d개의 여행계획 데이터를 저장하였습니다.\n", planList.size());


		} catch (IOException e) {
			System.out.println("파일 저장 중 오류 발생! - " + e.getMessage());
		} 
	}
	
}
