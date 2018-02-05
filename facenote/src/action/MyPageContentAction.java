package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ContentsDao;
import model.ContentsDto;
import model.UsersDao;
import model.UsersDto;

public class MyPageContentAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {		
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String uploadFilePath = req.getSession().getServletContext().getRealPath("/img");

		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail, uploadFilePath);
		
		ContentsDao contentsDao = ContentsDao.getInstance();
		List<ContentsDto> contents = null;
		List<String> writers = null;
		List<String> contentLocations = null;

		contents = contentsDao.getContentbyEmail(loginUserEmail, uploadFilePath);
		loginUser = userDao.getUser(loginUserEmail, uploadFilePath);
		writers = userDao.getWriters(contents);
		contentLocations = userDao.getUsers(contents);
		
		req.setAttribute("loginUser", loginUser);
		req.setAttribute("contents", contents);
		req.setAttribute("writer", writers);
		req.setAttribute("contentLocation", contentLocations);
		
		return "/jsp/page/mypagecontent.jsp";
	}
}
