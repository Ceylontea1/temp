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

public class updateContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ContentsDto ConDto = new ContentsDto();
		
		request.setCharacterEncoding("UTF-8");
		ContentsDao ConDao = ContentsDao.getInstance();
		HttpSession session = request.getSession();
		String loginUserEmail = (String) session.getAttribute("loginemail");
		String pageUserEmail = (String) session.getAttribute("pageemail");
		String[] emailSplit = pageUserEmail.split("@");
	    String newEmail = emailSplit[0] + "_" + emailSplit[1];
		int count = ConDao.getCount(pageUserEmail);

		String contentId = newEmail + "_" + String.valueOf(count + 1);
		String uploadFilePath = "c:\\img\\contentIMG\\" + newEmail + "\\" + contentId;
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
		
		if(!fileName.equals("") || fileName != null) {
			Path source = Paths.get(uploadFilePath + fileName);
			
			File ContentImage = new File(uploadFilePath + "\\" + fileName);
			ContentImage.getPath();
			ImagePath = ContentImage.getPath().replaceAll("c:\\\\", "/");

		}else {
			ImagePath="null";
		}

		ConDto.setContent(multi.getParameter("content"));
		ConDto.setImagepath(ImagePath);
		ConDto.setScope(multi.getParameter("scope"));

		ConDao.updateContent(ConDto);

		if(pageUserEmail.equals(loginUserEmail)) {
			return "/mypage.do";
		}
		else {
			return "/friendpage.do?friendmail=" + pageUserEmail;
		}
	}

}
