package bbangshuttle.member;

public class MemberSQL {
	
	public static final String  MEMBER_INSERT=
			"insert into userinfo(member_id,member_password,member_name,member_email,member_address,member_birth,member_number,member_regdate,member_point) values(?,?,?,?,?,?,?,sysdate,0)";
			// 회원가입 (멤버 집어넣기)
	
	public static final String MEMBER_DELETE=
			"delete from userinfo where member_id=?";
			// 회원탈퇴 (멤버 삭제하기)
	
	public static final String MEMBER_UPDATE=
			"update userinfo set member_password=?, member_email=?, member_address=?, member_number=? where member_id=?";
			// 회원정보수정 (멤버 정보 업데이트)
	
	public static final String MEMBER_SELECT_BY_ALL=
			"select member_id,member_password,member_name,member_email,member_address,member_birth,member_number,member_regdate,member_point from userinfo";
			// 회원목록 (멤버 정보 모두 나열)
	
	public static final String MEMBER_FIND_ID=
			"select member_id from userinfo where member_email=?";
			// 아이디 찾기 (멤버 이메일 입력 후, 아이디 출력)
	
	public static final String MEMBER_FIND_PW=
			"select member_password from userinfo where member_id=?";
			// 비밀번호 찾기 (멤버 아이디 입력 후, 비밀번호 출력) 

	public static final String MEMBER_LOGIN=
			"select member_Id,member_password from userinfo where member_Id=?,member_password=?";
			// 로그인 (멤버 아이디, 비밀번호 입력 후 로그인 성공)
	
	public static final String MEMBER_FIND_MYINFO = 
			"select member_id, member_password, member_name,member_email, member_address, member_birth, member_number, member_regdate, member_point from userinfo where member_password=?";
			// 내정보 (멤버 비밀번호 입력 후, 내 정보 출력)
	
	public static final String MEMBER_SELETE_BY_ID_COUNT =
			"select count(*) as cnt  from userinfo where member_id=?";
			// 아이디 중복 체크 (멤버 아이디 입력 후, 사용자 존재여부 확인)

	public static final String MEMBER_UPDATE_POINT = "UPDATE member SET member_point = ? WHERE member_id = ?";
	

}


