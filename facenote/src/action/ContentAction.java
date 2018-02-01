package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String contentid = request.getParameter("contentid");
		
		System.out.println(contentid);
		
//		String contentid = "ccc_test.com_9";
		ContentsDao ConDao = ContentsDao.getInstance();
		
		
		ContentsDto ConDto  = null;
		ReplyDao ReDao = ReplyDao.getInstance();
		
		UsersDao UDao = UsersDao.getInstance();
		
		UsersDto writer = new UsersDto();
		
		UsersDto hostemail = new UsersDto();
		
		
		
		ConDto  = ConDao.getContentbyNum(contentid);
		
		writer = UDao.getUser(ConDto.getWriter());
		
		hostemail = UDao.getUser(ConDto.getEmail());
		
		List<ReplyDto> ReDtoList = null;
		
		ReDtoList = ReDao.getReplys(contentid);
		
		
		request.setAttribute("Writer", writer);
		request.setAttribute("HostEmail", hostemail);
		request.setAttribute("ConDto", ConDto);
		request.setAttribute("ReDtoList", ReDtoList);
		request.setAttribute("Replycnt", ReDtoList.size());
		request.setAttribute("contentid", ConDto.getContentnum());
		
		System.out.println("ContentAction : imagepath >>> " + ConDto.getImagepath());
		System.out.println("ContemtAction : 컨텐츠 받아오고 아이디" + ConDto.getContentnum());
		System.out.println("ContentAction : 무사히 끝남");
		
		return "/jsp/page/contentForm.jsp";
	}
	

}
