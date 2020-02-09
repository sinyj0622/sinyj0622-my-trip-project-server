package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.dao.MemberObjectDao;
import sinyj0622.mytrip.domain.Member;

public class MemberAddServlet implements Servlet {

	MemberObjectDao memberDao;

	public MemberAddServlet(MemberObjectDao memberDao) {
		this.memberDao = memberDao;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			Member member = (Member) in.readObject();


			if (memberDao.insert(member) > 0) {
				out.writeUTF("OK");
			} else {
				out.writeUTF("FAIL");
				out.writeUTF("같은 번호의 게시물이 있습니다.");

			}

	}

}
