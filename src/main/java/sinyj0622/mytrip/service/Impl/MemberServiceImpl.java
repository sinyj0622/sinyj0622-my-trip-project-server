package sinyj0622.mytrip.service.Impl;

import java.util.HashMap;
import java.util.List;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;

public class MemberServiceImpl implements MemberService {
	
	MemberDao memberDao;
	
	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	
	@Override
	public int add(Member member) throws Exception {
		return memberDao.insert(member);
	}

	@Override
	public int delete(int no) throws Exception {
		return memberDao.delete(no);
	}

	@Override
	public Member get(int no) throws Exception {
		return memberDao.findByNo(no);
	}

	@Override
	public List<Member> list() throws Exception {
		return memberDao.findAll();
	}
	
	@Override
	public int update(Member newMember) throws Exception {
		return memberDao.update(newMember);
	}

	@Override
	public List<Member> findByKeyword(String response) throws Exception {
		return memberDao.findByKeyword(response);
	}

	@Override
	public Member findByEmailAndPassword(String email, String password) throws Exception {
		HashMap<String,Object> params = new HashMap<>();
		params.put("email", email);
		params.put("password", password);
		return memberDao.findByEmailAndPassword(params);
	}



}

