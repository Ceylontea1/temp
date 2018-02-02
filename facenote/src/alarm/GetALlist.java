package alarm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.AlarmDao;
import model.AlarmDto;
import model.UsersDao;
import model.UsersDto;

public class GetALlist implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		System.out.println("getAllist");
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);
		
		AlarmDao Aldao = AlarmDao.getInstance();
		List<AlarmDto> list = Aldao.getAlarmList(loginUserEmail);
		
		int alarmCount = Aldao.getAlarmCount(loginUserEmail);
		
		req.setAttribute("loginUser", loginUser);
		req.setAttribute("alarmCount", alarmCount);
		req.setAttribute("alarmlist", list);
		return "/jsp/alarm/alarmlist.jsp";
	}

}
