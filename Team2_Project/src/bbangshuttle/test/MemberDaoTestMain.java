package bbangshuttle.test;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberDao;

public class MemberDaoTestMain {

	public static void main(String[] args) throws Exception{
		
		MemberDao memberDao = new MemberDao();
		
		
		//System.out.println("1. 회원가입 : " 
				//+ memberDao.insert(new Member("Yoonshuttle166","6666", "윤셔틀", "Yoonshuttle166@gmail.com", "제주시", "1992/05/06", "010-6666-6666", null, 0)));
		
		
		
		System.out.println("2. 회원정보수정 : " 
				+ memberDao.update(new Member("leeshuttle22","99999", "이셔틀", "leeshuttle22@gmail.com", "런던", "1997/01/01", "010-9999-9999", null, 0)));
		
		

		
		//System.out.println("3. 회원탈퇴 : " + memberDao.delete("kimshuttle11"));
		
		
		
		//System.out.println("4. 아이디 찾기 : " + memberDao.findByEmail("Yoonshuttle166@gmail.com"));
		
		
		
		System.out.println("5. 비밀번호 찾기 : " + memberDao.findById("kimshuttle11"));
		
		
		
		System.out.println("6. 내 정보 출력 : " + memberDao.showMyInfo("1111"));
		
		
		
		System.out.println("7. 회원 목록 전체 출력 : " + memberDao.findAll());
		
	}

}
