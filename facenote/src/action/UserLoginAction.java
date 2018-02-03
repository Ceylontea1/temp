package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDao;

public class UserLoginAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("UserLoginServlet in!");
		HttpSession session=request.getSession();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword1");
		
		if(userEmail == null || userEmail.equals("") || userPassword == null || userPassword.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력해주세요.");
			return "/jsp/main/login.jsp";
		}
		
		int result = UsersDao.getInstance().login(userEmail, userPassword);
		
		if(result == 1) {
			request.getSession().setAttribute("userEmail", userEmail);
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "로그인에 성공했습니다.");
			session.setAttribute("loginUserEmail", userEmail);
			System.out.println("로그인 성공");
			return "/mypage.do";
		}
		if(result == 2) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "비밀번호를 다시 입력해주세요.");
			return "/jsp/main/login.jsp";
		}
		if(result == 0) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "아이디가 존재하지 않습니다.");
			return "/jsp/main/login.jsp";
		}
		if(result == -1) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "데이터베이스 에러 다시 시도해주세요.");
			return "/jsp/main/login.jsp";
		}
		
	return "/jsp/main/login.jsp";
	}
}
