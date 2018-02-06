package action;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


import model.ContentsDao;
import model.ContentsDto;
import model.UsersDao;
import model.UsersDto;

public class updateContentProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session=request.getSession();

		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);	
		
		ContentsDto ConDto = new ContentsDto();
		ContentsDao ConDao = ContentsDao.getInstance();
		
		String pageUserEmail = request.getParameter("pageemail");
		System.out.println("writeContentProAction - pageUserEmail : " + pageUserEmail);
		String[] emailSplit = pageUserEmail.split("@");
	    String newEmail = emailSplit[0] + "_" + emailSplit[1];

		String contentId = request.getParameter("contentid");
		request.setCharacterEncoding("UTF-8");
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";
		
		String uploadFilePath = request.getSession().getServletContext().getRealPath("/img") + "\\contentIMG\\" + newEmail + "\\" + contentId;

		File checkFolder = new File(uploadFilePath);

		if (!checkFolder.exists()) {
			checkFolder.mkdirs();
		}
		
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType,
				new DefaultFileRenamePolicy());
		String ImagePath = "";
		String fileName = multi.getFilesystemName("imagepath");
		if(fileName != null) {
			Path source = Paths.get(uploadFilePath + fileName);
			
			File ContentImage = new File(uploadFilePath + "\\" + fileName);
			ContentImage.getPath();
			ImagePath = ContentImage.getPath().replaceAll("c:\\\\", "/img");
			ImagePath = "img" + ImagePath.split("img")[1];
		}
			
		ConDto.setEmail(pageUserEmail);
		ConDto.setWriter(loginUserEmail);
		ConDto.setContentnum(contentId);
		ConDto.setContent(multi.getParameter("content"));
		if(fileName != null) {
			ConDto.setImagepath(ImagePath);
		}
		
		System.out.println("updateContentProAction : contentid >>>> " + contentId);
		ConDto.setContentnum(contentId);
		ConDto.setScope(multi.getParameter("scope"));
		ConDto.setGood(multi.getParameter("good"));

		ConDao.updateContent(ConDto);

		if(pageUserEmail.equals(loginUserEmail)) {
			System.out.println("writeContentProAction - pageUserEmail.equals(loginUserEmail) : " + pageUserEmail.equals(loginUserEmail));
			return "/mypage.do";
		}
		else {
			System.out.println("writeContentProAction - pageUserEmail.equals(loginUserEmail) : " + pageUserEmail.equals(loginUserEmail));
			return "/friendpage.do?friendmail=" + pageUserEmail;
		}
	}
			

}
