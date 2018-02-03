package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDao;
import model.UsersDto;

public class UserRemove implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String loginUserEmail = (String) session.getAttribute("loginUserEmail");
		String loginUserPassword = req.getParameter("password");
		UsersDao userDao = UsersDao.getInstance();
		
		if(userDao.userRemove(loginUserEmail, loginUserPassword) != 1) {
			return "/jsp/page/removeuser.jsp";
		}
		
		return "/jsp/main/testmainpage.jsp";
	}

}
