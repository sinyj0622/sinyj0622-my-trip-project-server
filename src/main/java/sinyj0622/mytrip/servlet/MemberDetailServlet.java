package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.dao.MemberObjectDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.Member;

public class MemberDetailServlet implements Servlet {

	MemberObjectDao memberDao;

	public MemberDetailServlet(MemberObjectDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			int no = in.readInt();

			Member member = memberDao.findByNo(no);

		    if (member != null) {
		      out.writeUTF("OK");
		      out.writeObject(member);


			} else {
				out.writeUTF("FAIL");
				out.writeUTF("해당 번호의 게시물이 없습니다.");
			}
	}

}
