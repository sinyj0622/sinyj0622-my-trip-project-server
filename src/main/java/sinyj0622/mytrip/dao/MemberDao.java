package sinyj0622.mytrip.dao;

import java.util.List;
import sinyj0622.mytrip.domain.Member;

public interface MemberDao {

   int insert(Member member) throws Exception;

   List<Member> findAll() throws Exception;

   Member findByNo(int no) throws Exception;

   int update(Member member) throws Exception;

   int delete(int no) throws Exception;
 
   default List<Member> findByKeyword(String keyword) throws Exception{
	   return null;
   }
   
   default Member findByEmailAndPassword(String email, String password) throws Exception {
	   return null;
   }
  
}
