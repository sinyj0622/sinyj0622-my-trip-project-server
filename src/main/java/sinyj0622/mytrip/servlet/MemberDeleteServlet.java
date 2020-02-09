package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.domain.Member;

public class MemberDeleteServlet implements Servlet {

	List<Member> members;
	
	public MemberDeleteServlet(List<Member> members) {
		this.members = members;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			int no = in.readInt();

			int index = -1;
			for (int i = 0; i < members.size(); i++) {
				if (members.get(i).getNo() == no) {
					index = i;
					break;
				}
			}

			if (index != -1) {
				members.remove(index);
				out.writeUTF("OK");
			} else {
				out.writeUTF("FAIL");
				out.writeUTF("해당 번호의 게시물이 없습니다.");
			}
	}

}
