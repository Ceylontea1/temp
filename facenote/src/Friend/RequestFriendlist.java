package Friend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.AlarmDao;
import model.FriendDao;
import model.UsersDao;
import model.UsersDto;

public class RequestFriendlist implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);
		
		FriendDao Fdao=FriendDao.getInstance();
		AlarmDao Aldao=AlarmDao.getInstance();
		
		List<UsersDto> list=Fdao.showFRlist(loginUser.getEmail());
		int count=Fdao.FriendCount(loginUser.getEmail());
		
		req.setAttribute("loginUser", loginUser);
		req.setAttribute("alarmCount", Aldao.getAlarmCount(loginUserEmail));
		req.setAttribute("friendcount", count);
		req.setAttribute("FreindList", list);
		return "/jsp/page/friendlist.jsp";
	}

}
