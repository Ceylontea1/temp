package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ContentsDao;
import model.ContentsDto;
import model.LikeDao;
import model.LikeDto;
import model.UsersDao;
import model.UsersDto;

public class UpLikeAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);	
		
		ContentsDao ConDao = ContentsDao.getInstance();
		ContentsDto ConDto = null; 
		
		LikeDao LDao = LikeDao.getInstance();
		LikeDto LDto = new LikeDto();
		
		String contentid = request.getParameter("contentid");
		String liker = loginUserEmail;
		LDto.setContentid(contentid);
		LDto.setLikeuser(liker);
		ConDto = ConDao.getContentbyNum(contentid);		
		if(LDao.insertLike(LDto) && !(ConDto.getWriter().equals(liker))) {
			
			ConDto.setGood(String.valueOf(Integer.parseInt(ConDto.getGood())+1));
			
			ConDao.updateContent(ConDto);
		}
		return "/FaceNote/content.do";
	}
}
