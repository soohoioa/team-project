package bbangshuttle.member;

import java.sql.Date;

public class MemberServiceTest {
	
	public static void main(String[] args) throws Exception {
		MemberService memberService = new MemberService();
		
		//1.회원가입
		System.out.println("1.회원가입");
		int rowCount = memberService.addMember(new Member("sdfsadf", "asdfasdf", "ahffk", "adsfsf@nasdfas", "adsfasf", "1999/09/09", "010-0101-0101", null, 0));
		System.out.println(">>>>"+rowCount);
		
		//2.회원로그인
		rowCount=0;
		rowCount = memberService.login("sdfsadf", "asdfasdf");
		System.out.println(">>>>"+rowCount);

		//3.회원아이디중복체크
		System.out.println("3.회원아이디중복체크");
        String testId1 = "sdfsadf";
        // String testId2 = "iwantgohome";
        boolean isDuplicated = memberService.isDuplicatedId(testId1);
        // boolean isDuplicated = memberService.isDuplicatedId(testId2);
        System.out.println(">>>> ID '" + testId1 + "' is " + (isDuplicated ? "중복" : "가입가능"));
        // System.out.println(">>>> ID '" + testId2 + "' is " + (isDuplicated ? "중복" : "가입가능"));
		
        
        //4.회원상세보기
		System.out.println("4.회원상세보기");
		String memberPassword = "asdfasdf";
		Member member = memberService.memberDetail(memberPassword);
		System.out.println(">>>회원정보:" + member.toString());
		
		//5.회원수정
		Member updateMember = memberService.memberDetail("5555");
		updateMember.setMemberAddress("아이");
        System.out.println("회원수정 : " + memberService.memberUpdate(updateMember));
		
		
		//6.회원탈퇴
        
		System.out.println("6. 회원탈퇴");
		String memberId = "choishuttle44";
		int deleteCount = memberService.memberDelete(memberId);
        System.out.println(">>>> 삭제된 회원 수: " + deleteCount);
	}
}	