package sinyj0622.mytrip.service;

import java.util.List;
import sinyj0622.mytrip.domain.Member;

public interface MemberService {

  Member get(String email, String password) throws Exception;

  int add(Member member) throws Exception;

  int delete(int no) throws Exception;

  Member get(int no) throws Exception;

  List<Member> list() throws Exception;

  List<Member> findByKeyword(String response) throws Exception;

  int update(Member newMember) throws Exception;


}
