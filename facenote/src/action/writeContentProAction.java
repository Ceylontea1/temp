package action;

import java.io.File;
import java.nio.file.Files;
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

public class writeContentProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session=request.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		UsersDao userDao = UsersDao.getInstance();
		UsersDto loginUser = userDao.getUser(loginUserEmail);	
		
		ContentsDto ConDto = new ContentsDto();
		ContentsDao ConDao = ContentsDao.getInstance();
		
		String pageUserEmail = (String) session.getAttribute("pageemail");
		String[] emailSplit = pageUserEmail.split("@");
	    String newEmail = emailSplit[0] + "_" + emailSplit[1];
		int count = ConDao.getCount(pageUserEmail);

		String contentId = newEmail + "_" + String.valueOf(count + 1);
		request.setCharacterEncoding("UTF-8");
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";
		
		String uploadFilePath = "c:\\img\\contentIMG\\" + newEmail + "\\" + contentId;

		File checkFolder = new File(uploadFilePath);

		if (!checkFolder.exists()) {
			checkFolder.mkdirs();
		}
		System.out.println("���ε� ����" + uploadFilePath);
		
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType,
				new DefaultFileRenamePolicy());
		String ImagePath = "";
		String fileName = multi.getFilesystemName("imagepath");
		if(!fileName.equals("")) {
			Path source = Paths.get(uploadFilePath + fileName);
			
			File ContentImage = new File(uploadFilePath + "\\" + fileName);
			ContentImage.getPath();
			ImagePath = ContentImage.getPath().replaceAll("c:\\\\", "/");

		}
			
		ConDto.setEmail(pageUserEmail);
		ConDto.setWriter(loginUserEmail);
		ConDto.setContent(multi.getParameter("content"));
		ConDto.setImagepath(ImagePath);
		ConDto.setContentnum(contentId);
		ConDto.setScope(multi.getParameter("scope"));

		ConDao.insertContent(ConDto);

		if(pageUserEmail.equals(loginUserEmail)) {
			return "/mypage.do";
		}
		else {
			return "/friendpage.do?friendmail=" + pageUserEmail;
		}
	}

}
