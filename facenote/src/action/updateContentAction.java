package action;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.ContentsDao;
import model.ContentsDto;
import model.UsersDao;
import model.UsersDto;

public class updateContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ContentsDto ConDto = new ContentsDto();
		
		request.setCharacterEncoding("UTF-8");
		ContentsDao ConDao = ContentsDao.getInstance();

		ConDto = ConDao.getContentbyNum(request.getParameter("contentid"));
		
		request.setAttribute("ConDto", ConDto);
		
		return "/jsp/page/modwriteForm.jsp";

	}

}
