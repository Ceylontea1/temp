package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContentsDao;
import model.ContentsDto;

public class AlbumContentAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		req.setCharacterEncoding("UTF-8");

		List<ContentsDto> contents = null;
		ContentsDao contentsDao = ContentsDao.getInstance();
		
		int count = 0;
			
		count = contentsDao.getCount(req.getParameter("email"));		
		contents = contentsDao.getContentbyEmail(req.getParameter("email"));
		
		req.setAttribute("contents", contents);
		req.setAttribute("contentCount", count);
		
		return "/jsp/page/albumcontent.jsp";
	}
}
