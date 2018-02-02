package Friend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.FriendDao;
import model.FriendDto;
import model.UsersDao;
import model.UsersDto;

public class RequestFRDelete implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		
		String frid= req.getParameter("friendID");

		FriendDao FRdao=FriendDao.getInstance();
		int result=FRdao.deleteFriend(loginUserEmail, frid);
		if(result!=2) {
			result=0;
		}
		req.setAttribute("result", result);
		return "/jsp/page/frienddeletepro.jsp";
	}

}
