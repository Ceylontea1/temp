package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AlarmDao;
import model.FriendDao;
import model.UsersDao;
import model.UsersDto;

public class AlbumAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();

		if(session.getAttribute("loginUserEmail") == null) {
			return "/jsp/main/join.jsp";
		}

		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);
		UsersDto userDto = userDao.getUser(req.getParameter("email"));
			
		AlarmDao Aldao=AlarmDao.getInstance();

		FriendDao friendDao = FriendDao.getInstance();
		String state = "";
		state = friendDao.checkState(loginUserEmail, req.getParameter("email"));
		
		req.setAttribute("alarmCount", Aldao.getAlarmCount(loginUserEmail));
		req.setAttribute("friendState", state);
		req.setAttribute("albumAccount", userDto);
		req.setAttribute("loginUser", loginUser);
		
		return "/jsp/page/album.jsp";
	}
}
