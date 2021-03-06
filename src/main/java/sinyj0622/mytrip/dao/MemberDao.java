package sinyj0622.mytrip.dao;

import java.util.List;
import java.util.Map;
import sinyj0622.mytrip.domain.Member;

public interface MemberDao {

  int insert(Member member) throws Exception;

  List<Member> findAll() throws Exception;

  Member findByNo(int no) throws Exception;

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

  List<Member> findByKeyword(String keyword) throws Exception;

  Member findByEmailAndPassword(Map<String, Object> params) throws Exception;

  Member findByEmail(String email) throws Exception;
}
