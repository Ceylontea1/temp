package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		
		System.out.println(" Logout Logout");
		HttpSession session=req.getSession();
		String loginUserEmail=(String) session.getAttribute("loginUserEmail");
		if(loginUserEmail!=null) {
			session.invalidate();
		}
		
		return "/jsp/main/testmainpage.jsp";
	}

}
