package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AlarmDao;
import model.UsersDao;
import model.UsersDto;

public class MyPageAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		HttpSession session=req.getSession();
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser =null;
		
		String loginUserEmail=(String) session.getAttribute("loginUserEmail");
		if(session.getAttribute("loginUserEmail")==null) {
			session.setAttribute("loginUserEmail", req.getParameter("email"));	
			loginUserEmail=(String) session.getAttribute("loginUserEmail");
			if(loginUser==null) {
				AlarmDao dao=AlarmDao.getInstance();
				dao.insertBirthdayAlarm("3", loginUserEmail);
			}
		}
		
		 loginUserEmail = (String)session.getAttribute("loginUserEmail");
		 loginUser=userDao.getUser(loginUserEmail);
		 AlarmDao Aldao=AlarmDao.getInstance();

		req.setAttribute("alarmCount", Aldao.getAlarmCount(loginUserEmail));
		req.setAttribute("loginUser", loginUser);
		
		return "/jsp/page/mypage.jsp";
	}
}
