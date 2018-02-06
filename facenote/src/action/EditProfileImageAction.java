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
		String uploadFilePath = req.getSession().getServletContext().getRealPath("/img");
		File uploadDir = new File(uploadFilePath + "\\profileIMG\\");
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		File originalProfileImage = new File(uploadDir + "\\" + newEmail);
		if(originalProfileImage.exists()) {
			originalProfileImage.delete();
		}
		
		MultipartRequest multi = new MultipartRequest(req,
			 uploadFilePath + "\\profileIMG\\",
			 uploadFileSizeLimit,
			 encType,new DefaultFileRenamePolicy());
		
		String fileName = multi.getFilesystemName("newProfileImage");
		File oldProfileImage = new File(uploadDir + "\\" + fileName);	// 새 업로드
	
		Path source = Paths.get(uploadDir + fileName);
//		Path source = Paths.get(uploadDir +  newEmail + ".png");
		String fileType = Files.probeContentType(source).split("/")[1];
//		File oldProfileImage = new File(uploadDir + "\\" + newEmail + fileType);	// 새 업로드

		File newProfileImage = new File(uploadDir + "\\" + newEmail + ".png"); // 메일 주소로 업로드
		if(newProfileImage != null) {
			newProfileImage.delete();
		}
		
		newProfileImage = new File(uploadDir + "\\" + newEmail + ".png");
		System.out.println("new file path : " + newProfileImage.getPath());
		System.out.println("uploadDir : " + uploadDir);
//		System.out.println("oldPI : " + uploadDir + "\\" + fileName);
		System.out.println("new File path : " + uploadDir + "\\" + newEmail + ".png");
		oldProfileImage.renameTo(newProfileImage);
		
		return "/jsp/page/closepage.jsp";
	}
}
