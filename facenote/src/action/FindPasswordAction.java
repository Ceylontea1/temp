package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UsersDao;

public class FindPasswordAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String password = "";
				
		UsersDao userDao = UsersDao.getInstance();
		
		password = userDao.findUserPassword(email);
		
		
		req.setAttribute("userPassword", password);
		
		return "/jsp/main/resultpassword.jsp";
	}

}