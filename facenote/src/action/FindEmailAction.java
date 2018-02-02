package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UsersDao;

public class FindEmailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		String email = "";
		String name = req.getParameter("name");
		String phone1 = req.getParameter("phone1");
		String phone2 = req.getParameter("phone2");
		String phone3 = req.getParameter("phone3");
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		UsersDao userDao = UsersDao.getInstance();
		email = userDao.findUserEmail(name, phone);
		
		req.setAttribute("userEmail", email);
		
		return "/jsp/main/resultemail.jsp";
	}

}