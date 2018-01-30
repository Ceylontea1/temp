package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AlarmDao;
import model.FriendDao;
import model.UsersDao;
import model.UsersDto;

public class FriendPageAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {

		HttpSession session=req.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);
		
		FriendDao friendDao = FriendDao.getInstance();		
		UsersDto friendDto = userDao.getUser(req.getParameter("friendmail"));
		String friendemail=friendDto.getEmail();
		String state = "";
		
		int alarmcount = 0;
		
		AlarmDao Aldao=AlarmDao.getInstance();
		if(req.getParameter("count") != null) {
			alarmcount = Integer.parseInt(req.getParameter("count"));
			Aldao.alarmDelete(alarmcount, loginUserEmail, friendemail);
		}
		
		state = friendDao.checkState(loginUserEmail, req.getParameter("friendmail"));

		req.setAttribute("loginUser", loginUser);
		req.setAttribute("alarmCount", Aldao.getAlarmCount(loginUserEmail));
		req.setAttribute("friendState", state);
		req.setAttribute("friend", friendDto);
		
		return "/jsp/page/friendpage.jsp";
	}
}
