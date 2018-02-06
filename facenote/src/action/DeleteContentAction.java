package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ContentsDao;
import model.ContentsDto;
import model.UsersDao;
import model.UsersDto;

public class DeleteContentAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {		
		request.setCharacterEncoding("UTF-8");
		
		ContentsDao conDao = ContentsDao.getInstance();
		ContentsDto conDto = conDao.getContentbyNum(request.getParameter("contentid"));
		
		HttpSession session=request.getSession();

		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		String pageUserEmail = conDto.getWriter();
		
		conDao.deleteContent(request.getParameter("contentid"));
		
		String url = "";
		if(pageUserEmail.equals(loginUserEmail)) {
			System.out.println("writeContentProAction - pageUserEmail.equals(loginUserEmail) : " + pageUserEmail.equals(loginUserEmail));
			url = "/mypage.do";
		}
		else {
			System.out.println("writeContentProAction - pageUserEmail.equals(loginUserEmail) : " + pageUserEmail.equals(loginUserEmail));
			url = "/jsp/page/friendpage.jsp?friendmail=" + pageUserEmail;
		}
		
		request.setAttribute("url", url);
		
		return "/jsp/page/closepagewithurl.jsp";

	}
}
