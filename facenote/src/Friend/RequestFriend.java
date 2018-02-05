package Friend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.AlarmDao;
import model.FriendDao;
import model.UsersDao;
import model.UsersDto;

public class RequestFriend implements CommandAction{
//friend테이블에 요청할 친구 이메일, 사용자의 이메일 그반대도 들어간다.
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String uploadFilePath = req.getSession().getServletContext().getRealPath("/img");
		HttpSession session=req.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail, uploadFilePath);
		
		int result=0;
		
		String friendID=req.getParameter("friendID");
	
		FriendDao dao=FriendDao.getInstance();
	   
		if(!loginUserEmail.equals(friendID)) {
		 result=dao.reqFriend(loginUserEmail, friendID, uploadFilePath);
		}
		 else {result=0;}//자기자신과는  친구가 될수없음!
		
		if(result==2) {//요청이 제대로 되었다면 친구에게 알림을 쏘아준다.
			AlarmDao AlDao=AlarmDao.getInstance();
			AlDao.insertFriendAlarm("2", friendID, loginUserEmail, uploadFilePath);
		}
		req.setAttribute("idsearch",friendID);
		req.setAttribute("result", result);
		//result 가 2이면 성공적 , result=0이면  잘못됨 이미 요청한 친구 <result 값은 0과 2만 나오도록 한다.>
		return "/jsp/page/requestfriendpro.jsp";
	}

}
