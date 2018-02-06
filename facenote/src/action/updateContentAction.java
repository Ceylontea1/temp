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
		HttpSession session = request.getSession();
/*		String loginUserEmail = (String) session.getAttribute("loginUserEmail");
//		String pageUserEmail = (String) session.getAttribute("pageemail");
		String pageUserEmail = "";
		
		UsersDao dao = UsersDao.getInstance(); 
		UsersDto pageUser = dao.getUser(ConDao.getContentbyNum(request.getParameter("contentid")).getEmail());
		pageUserEmail = pageUser.getEmail();
		
		String[] emailSplit = pageUserEmail.split("@");
	    String newEmail = emailSplit[0] + "_" + emailSplit[1];
		int count = ConDao.getCount(pageUserEmail);

		String contentId = newEmail + "_" + String.valueOf(count + 1);
//		String uploadFilePath = "c:\\img\\contentIMG\\" + newEmail + "\\" + contentId;
		String uploadFilePath = request.getSession().getServletContext().getRealPath("/img") + "\\contentIMG\\" + newEmail + "\\" + contentId;

		File checkFolder = new File(uploadFilePath);

		if (!checkFolder.exists()) {
			checkFolder.mkdirs();
		}
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";

		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType,
				new DefaultFileRenamePolicy());
		
		ConDto = ConDao.getContentbyNum(contentId);

		String ImagePath = "";
		String fileName = multi.getFilesystemName("imagepath");
		
		if(fileName != null) {
			Path source = Paths.get(uploadFilePath + fileName);
			
			File ContentImage = new File(uploadFilePath + "\\" + fileName);
			ContentImage.getPath();
			ImagePath = ContentImage.getPath().replaceAll("c:\\\\", "/");
			ImagePath = "img" + ImagePath.split("img")[1];

		}else {
			ImagePath="null";
		}

//		ConDto.setContent(ConDao.getContentbyNum("contentid"));
//		ConDto.setImagepath(ImagePath);
//		ConDto.setScope(multi.getParameter("scope"));

//		ConDao.updateContent(ConDto);
*/
		ConDto = ConDao.getContentbyNum(request.getParameter("contentid"));
		
		request.setAttribute("ConDto", ConDto);
		
		return "/jsp/page/modwriteForm.jsp";

	}

}
