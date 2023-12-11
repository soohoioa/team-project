package bbangshuttle.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() throws Exception{
		memberDao=new MemberDao();
	}
	/*
	 * 회원가입
	 */
	public int addMember(Member member) throws Exception{
			//1.아이디중복체크
			if(memberDao.CountUserId(member.getMemberId())>=1) {
				//중복
				return -1;
			}else {
				//가입
				int rowCount=memberDao.insert(member);
				return rowCount;
			}
		} 

	
	/*
	 * 회원로그인
	 */
	public int login(String memberId,String password)throws Exception{
		// 0:실패 1:성공
		int result=0;
		if(memberDao.CountUserId(memberId)==1) {
			//아이디존재하는경우
			String memberid = memberDao.findById(memberId);
			if(memberid.equals(password)) {
				//패스워드일치
				result=1;
			}else {
				//패스워드불일치
				result=0;
			}
		}else {
			//회원이 아닌경우
			result=0;
		}
		return result;
	}
	
	public Member loginMember(String memberId, String password) throws Exception {
	    // 회원 정보를 데이터베이스 등에서 가져오는 DAO의 메서드를 사용하여 조회
	    Member member = memberDao.getMemberById(memberId);

	    // 회원 정보가 존재하고 비밀번호가 일치하면 로그인 성공
	    if (member != null && member.getMemberPassword().equals(password)) {
	        return member;
	    } else {
	        // 로그인 실패 시 null 반환
	        return null;
	    }
	}
	
	
	/*
	 * 회원아이디중복체크
	 */
	public boolean isDuplicatedId(String memberId) throws Exception{
		if(memberDao.CountUserId(memberId)>=1) {
			return true;
		}else {
			return false;
		}
			
	}
	public void updateMemberPoint(Member member, int pointToAdd) throws Exception {
        // 현재 회원의 memberPoint를 조회
        int currentMemberPoint = member.getMemberPoint();

        // 새로운 memberPoint를 계산하여 업데이트
        int newMemberPoint = currentMemberPoint + pointToAdd;
        member.setMemberPoint(newMemberPoint);

        // 데이터베이스에 업데이트된 memberPoint를 반영
        memberDao.updateMemberPoint(member);
    }
	
	/*
	 * 회원상세보기
	 */
	public Member memberDetail(String memberPassword) throws Exception{
		return memberDao.showMyInfo(memberPassword);
	}
	/*
	 * 회원수정
	 */
	public int memberUpdate(Member member)throws Exception{
		return memberDao.update(member);
	}
	/*
	 * 회원탈퇴
	 */
	public int memberDelete(String memberId) throws Exception{
		return memberDao.delete(memberId);
	}
	
}