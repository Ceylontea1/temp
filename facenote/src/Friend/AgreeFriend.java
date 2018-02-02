package Friend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.AlarmDao;
import model.FriendDao;
import model.UsersDao;
import model.UsersDto;

public class AgreeFriend implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		
		FriendDao FRdao=FriendDao.getInstance();
		AlarmDao aldao=AlarmDao.getInstance();
		String FRid=req.getParameter("friendID");
		String count=req.getParameter("count");
		int c=Integer.parseInt(count);
		int agreeRS=FRdao.agreeFriend(loginUserEmail, FRid);//값이 2일떄 성공적 
		int ALRS=aldao.alarmDelete(c, loginUserEmail, FRid);//값이 1일떄 성공적
		req.setAttribute("agreeRS", agreeRS);
		req.setAttribute("ALRS", ALRS);
		return "/jsp/alarm/alarmlistproc.jsp";
	}

}
