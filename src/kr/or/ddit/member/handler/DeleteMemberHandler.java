package kr.or.ddit.member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.handler.CommandHandler;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

public class DeleteMemberHandler implements CommandHandler{

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		//redirect가 필요함.
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		
		//2. 삭제처리
		//1) 서비스 객체 생성
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		int cnt = memberService.deleteMember(memId);
		
		String msg = "";
	      
	      if(cnt > 0) {
	         msg = "성공";
	      }else {
	         msg = "실패";
	      }
	      //4. 목록 조회화면으로 이동 - Redirect
	      String redirectUrl = req.getContextPath() + 
	            "/member/list.do?msg=" + URLEncoder.encode(msg, "UTF-8"); 

	      return redirectUrl;
	}

}
