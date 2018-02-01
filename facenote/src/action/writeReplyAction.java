package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReplyDao;
import model.ReplyDto;
import model.UsersDao;
import model.UsersDto;

public class writeReplyAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session=request.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);		
		
		request.setCharacterEncoding("UTF-8");
		ReplyDto ReDto = new ReplyDto();
		ReplyDao ReDao = ReplyDao.getInstance();
		
		ReDto.setWriter(loginUserEmail);
		ReDto.setContent(request.getParameter("reply"));
		ReDto.setContentid(request.getParameter("contentid"));
		
		ReDao.writeReply(ReDto);
		
		return "/FaceNote/content.do";
	}

}
