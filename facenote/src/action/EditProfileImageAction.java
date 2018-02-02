package action;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.UsersDao;
import model.UsersDto;

public class EditProfileImageAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse reps) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String loginUserEmail = (String)session.getAttribute("loginUserEmail");
		
		String[] emailSplit = loginUserEmail.split("@");
		String newEmail = emailSplit[0] + "_" + emailSplit[1];
		
		req.setCharacterEncoding("UTF-8");
		int uploadFileSizeLimit = 5*1024*1024;
		String encType = "UTF-8";
		String uploadFilePath = "c:\\img\\profileIMG\\";
		
		MultipartRequest multi = new MultipartRequest(req,
			 uploadFilePath,
			 uploadFileSizeLimit,
			 encType,new DefaultFileRenamePolicy());
		
		String fileName = multi.getFilesystemName("newProfileImage");
		File oldProfileImage = new File(uploadFilePath + fileName);
		
		Path source = Paths.get(uploadFilePath + fileName);
		String fileType = Files.probeContentType(source).split("/")[1];
		
		File newProfileImage = new File(uploadFilePath + newEmail + "." + fileType);
		if(newProfileImage != null) {
			newProfileImage.delete();
		}
		
		newProfileImage = new File(uploadFilePath + newEmail + "." + fileType);
		
		oldProfileImage.renameTo(newProfileImage);
		
		return "/jsp/page/closepage.jsp";
	}
}
