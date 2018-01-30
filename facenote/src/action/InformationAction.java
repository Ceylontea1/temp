package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AlarmDao;
import model.UsersDao;
import model.UsersDto;

public class InformationAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		HttpSession session=req.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);
		
		AlarmDao Aldao=AlarmDao.getInstance();
		
		String[] password = new String[1];
		password[0] = loginUser.getPassword();
		
		req.setAttribute("alarmCount", Aldao.getAlarmCount("cap911218@hanmail.net"));

		req.setAttribute("loginUser", loginUser);
		req.setAttribute("phoneSplit", (loginUser.getPhone()).split("-"));
		
		return "/jsp/page/information.jsp";
	}
}
