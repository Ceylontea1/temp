package Friend;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.AlarmDao;
import model.FriendDao;
import model.UsersDao;
import model.UsersDto;

public class SearchFriend implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String uploadFilePath = req.getSession().getServletContext().getRealPath("/img");

		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail, uploadFilePath);

		List<UsersDto> resultList=new ArrayList<UsersDto>(10);
		AlarmDao Aldao=AlarmDao.getInstance();

		String a=req.getParameter("namesearch");
		String b=req.getParameter("phonesearch");
		String c=req.getParameter("idsearch");

		String method="";
		FriendDao dao=FriendDao.getInstance();
		
		if(!a.equals("")) {
			method="name";
			resultList=dao.searchFriend(method, a, loginUserEmail);
		}
		else if(!b.equals("")) {
			method="phone";
			resultList=dao.searchFriend(method, b, loginUserEmail);
		}
		else if(!c.equals("")) {
			method="id";
			resultList=dao.searchFriend(method, c,loginUserEmail);
		}
		
		req.setAttribute("loginUser", loginUser);
		req.setAttribute("alarmCount", Aldao.getAlarmCount(loginUserEmail));
		req.setAttribute("list", resultList);
		req.setAttribute("listSize", resultList.size());

		return "/jsp/page/searchfriend.jsp";
	}

}
