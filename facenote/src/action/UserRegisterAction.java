package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDao;

public class UserRegisterAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("UserRegister start!");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String idCheck = "null";
		String emailCheck = "null";
		String userEmail = request.getParameter("userEmail");
		String userPassword1 = request.getParameter("userPassword1");
		String userPassword2 = request.getParameter("userPassword2");
		String userName = request.getParameter("userName");
		String birthYear = request.getParameter("birthYear");
		String birthMonth = request.getParameter("birthMonth");
		String birthDay = request.getParameter("birthDay");
		String userBirth = birthYear + "-" + birthMonth +  "-" + birthDay; 
		String userPhone1 = request.getParameter("userPhone1");
		String userPhone2 = request.getParameter("userPhone2");
		String userPhone3 = request.getParameter("userPhone3");
		String userPhone = userPhone1 + "-" + userPhone2 + "-" + userPhone3;
		String userGender = request.getParameter("userGender");
		String userProfile = request.getParameter("userProfile");
		
		idCheck = (String)session.getAttribute("idCheck");
		System.out.println("idCheckValue : "+session.getAttribute("idCheck"));
		if(idCheck == null) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "이메일 중복 체크를 해주세요.");
			return "/jsp/main/join.jsp";
		}
/*		emailCheck = (String)session.getAttribute("emailCheck");
		System.out.println("emailCheckValue : "+session.getAttribute("emailCheck"));
		if(emailCheck == null) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "이메일 인증이 되지 않았습니다.");
			return "join.jsp";
		}*/
		
		if(userEmail == null || userEmail.equals("") || userPassword1 == null || userPassword1.equals("")
				||userPassword2 == null || userPassword2.equals("") || userName == null || userName.equals("")
				||userBirth == null || userBirth.equals("") ||userGender == null || userGender.equals("")) {
			System.out.println("1번오류메세지");
			
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			return "/jsp/main/join.jsp";
		}
		if(!userPassword1.equals(userPassword2)) {
			System.out.println("2번오류메세지");
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
			return "/jsp/main/join.jsp";
		}
		
		int result = UsersDao.getInstance().register(userEmail, userPassword1, userName, userBirth, userPhone, userGender, userProfile);
		System.out.println("result : "+result);
		
		if(result == 1) {
			System.out.println("messageType : 성공메세지");
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "회원 가입에 성공 했습니다.");
			return "/login.do";
		} else {
			System.out.println("massageType : 오류메시지");
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다.");
			return "/jsp/main/join.jsp";
		}
	}
	

}
