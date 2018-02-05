package Friend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.AlarmDao;
import model.UsersDao;
import model.UsersDto;

public class SearchFriendinit implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String uploadFilePath = req.getSession().getServletContext().getRealPath("/img");

		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail, uploadFilePath);
		
		AlarmDao Aldao=AlarmDao.getInstance();

		req.setAttribute("loginUser", loginUser);
		req.setAttribute("user", loginUserEmail);
		req.setAttribute("alarmCount", Aldao.getAlarmCount(loginUserEmail));

		return "/jsp/page/searchfriend.jsp";
	}

}
