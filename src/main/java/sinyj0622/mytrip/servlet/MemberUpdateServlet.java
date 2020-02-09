package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.dao.MemberObjectDao;
import sinyj0622.mytrip.domain.Member;

public class MemberUpdateServlet implements Servlet {

	MemberObjectDao memberDao;

	public MemberUpdateServlet(MemberObjectDao memberDao) {
		this.memberDao = memberDao;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			Member member = (Member) in.readObject();

			if (memberDao.update(member) > 0) {
			      out.writeUTF("OK");
			} else {
				out.writeUTF("FAIL");
				out.writeUTF("해당 번호의 게시물이 없습니다.");
				out.flush();
			}
	}

}
