package model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class UsersDao {
	
	private static UsersDao instance = null;
	
	private UsersDao() {}
	
	public static UsersDao getInstance() {
		if(instance == null) {
			synchronized(UsersDao.class) {
				instance = new UsersDao();
			}
		}
		
		return instance;
	}
	
	public void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public UsersDto getUser(ResultSet rs) {
		UsersDto user = null;
		try {
			while(rs.next()) {
					user = new UsersDto();
					user.setEmail(rs.getString("EMAIL"));
					user.setPassword(rs.getString("PASSWORD"));
					user.setName(rs.getString("NAME"));
					user.setAge(rs.getInt("AGE"));
					user.setGender(rs.getString("GENDER"));
					user.setBirth(rs.getString("BIRTH"));
					user.setPhone(rs.getString("PHONE"));
//					if(this.isExists(rs.getString("IMAGEPATH"))) {
						user.setimagepath(rs.getString("IMAGEPATH"));
//					}
//					else {
//						user.setimagepath("/img/profileIMG/noneProfile.png");
//					}
					user.setRegdate(rs.getString("REGDATE"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	public UsersDto getUser(String loginEmail) {
		UsersDto user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from USERS where EMAIL = ?";
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginEmail);
			rs = pstmt.executeQuery();
			
			user = this.getUser(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
		
		return user;
	}
	
	public boolean isExists(String imagePath) {
		boolean isExists = true;
		
		File file = new File(imagePath);
		
		isExists = file.exists();
		
		return isExists;
	}
	
	public List<String> getUsers(List<ContentsDto> content) {
		List<String> users = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select NAME from USERS where EMAIL = ?";
		try {
			users = new ArrayList<String>();
			con = ConUtil.getConnection();
			for(int index = 0; index < content.size(); index++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, content.get(index).getEmail());
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					users.add(rs.getString("NAME"));
				}
				pstmt.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
		
		return users;
	}

	public List<String> getWriters(List<ContentsDto> content) {
		List<String> writers = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select NAME from USERS where EMAIL = ?";
		try {
			writers = new ArrayList<String>();
			con = ConUtil.getConnection();
			for(int index = 0; index < content.size(); index++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, content.get(index).getWriter());
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					writers.add(rs.getString("NAME"));
				}
				pstmt.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
		
		return writers;
	}

	public void editUserInformation(HttpServletRequest req, String loginEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update USERS set PHONE = ? where EMAIL = ?";
		String phone = "";
		String phoneNumber = "";
		
		if(!req.getParameter("newPassword").equals("")) {
			this.changePassword(req, loginEmail);
		}
		
		for(int i = 0; i < 3; i++) {
			phone = "phone" + i;
			if(i == 2) {
				phoneNumber += req.getParameter(phone);
			}
			else {
				phoneNumber += req.getParameter(phone) + "-";
			}
			
		}
		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phoneNumber);
			pstmt.setString(2, loginEmail);
			
			pstmt.executeQuery();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
	}
	
	public void changePassword(HttpServletRequest req, String loginEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update USERS set PASSWORD = ? where EMAIL = ?";
		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, req.getParameter("newPassword"));
			pstmt.setString(2, loginEmail);
			
			pstmt.executeQuery();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
	}
	
	public int login(String userEmail, String userPassword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from USERS where EMAIL = ?";
		try {
			conn = ConUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("PASSWORD").equals(userPassword)) {
					return 1;	// 로그인 성공
				}
					return 2;	// 비밀번호 틀림
			} else {
					return 0;	// 해당 사용자가 존재하지 않음
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn, pstmt, rs);
		}
		return -1;	// 데이터 베이스 오류
	}
	
	public int registerCheck(String userEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from USERS where EMAIL = ?";
		try {
			conn = ConUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			rs = pstmt.executeQuery();
			if(rs.next() || userEmail.equals("")) {
				System.out.println("dao registerCheck 가입 불가능한 이메일");
				return 0;	// 이미 존재하는 회원
			} else {
				System.out.println("dao registerCheck 가입 가능한 회원 이메일");
				return 1;	// 가입 가능한 회원 이메일
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn, pstmt, rs);
		}
		return -1;	// 데이터 베이스 오류
	}
	
	public int register(String userEmail, String userPassword, String userName, String userBirth, String userPhone, String userGender, String userProfile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(registerCheck(userEmail) == 1) {
			String sql = "insert into USERS(EMAIL, PASSWORD, NAME, AGE, GENDER, BIRTH, PHONE, IMAGEPATH, REGDATE) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				conn = ConUtil.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userEmail);
				pstmt.setString(2, userPassword);
				pstmt.setString(3, userName);
				pstmt.setInt(4, this.getAge(userBirth));
				pstmt.setString(5, userGender);
				pstmt.setString(6, userBirth);
				pstmt.setString(7, userPhone);
				pstmt.setString(8, this.getImagePath(userEmail));
				pstmt.setString(9, this.getDate());
				return pstmt.executeUpdate();
			
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				this.closeConnection(conn, pstmt, rs);
			}
		}
		return -1;	// 데이터베이스 오류
	}
	
	public String getImagePath(String email) {
		System.out.println(email);
		String[] emailSplit = email.split("@");
		String dirPath = "/img/profileIMG/";
		String imagePath = dirPath + emailSplit[0] + "_" + emailSplit[1];
		
		return imagePath;
	}
	
	public int getAge(String birth) {
		int age = 0;
		
		String time="";
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA);
		Date currentTime=new Date();
		time=formatter.format(currentTime);
		
		String[] nowYear = time.split("-");
		String[] birthYear = birth.split("-");
		
		age = Integer.parseInt(nowYear[0]) - Integer.parseInt(birthYear[0]);
		
		return age;
	}

	public String getDate() {
		String time="";
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
		Date currentTime=new Date();
		time=formatter.format(currentTime);
		
		return time;
	}

	public String findUserEmail(String findName, String findPhone) {	// num = 1 : email / 2 : password 
		String userEmail = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select EMAIL from USERS where NAME = ? and PHONE = ?";		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, findName);
			pstmt.setString(2, findPhone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userEmail = rs.getString("EMAIL");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
		return userEmail;
	}
	
	public String findUserPassword(String findEmail) {	// num = 1 : email / 2 : password 
		String userPassword = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select PASSWORD from USERS where EMAIL = ?";		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, findEmail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userPassword = rs.getString("PASSWORD");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
		return userPassword;
	}
}
