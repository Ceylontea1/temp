package model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
					if(!rs.getString("IMAGEPATH").equals("null")) {
						user.setimagepath(rs.getString("IMAGEPATH"));
					}
					else {
						user.setimagepath("/img/noneProfileIMG/noneIMG.png");
					}
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
		 
	public UsersDto getUserInfo(String USERID) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		 UsersDto ConnectUser=new UsersDto(); 
		try {
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement("select * from USERS where EMAIL=(?)");
			//사용자 아이디가 cap911218일경우를 생각함
			pstmt.setString(1, USERID);
			rs=pstmt.executeQuery();	
			
			if(rs.next()) {
				
				 ConnectUser.setEmail(rs.getString("EMAIL"));
				 ConnectUser.setName(rs.getString("NAME"));
				 ConnectUser.setAge(rs.getInt("AGE"));
				 ConnectUser.setGender(rs.getString("GENDER"));
				 ConnectUser.setBirth(rs.getString("BIRTH"));
				 ConnectUser.setPhone(rs.getString("PHONE"));
				 ConnectUser.setimagepath(rs.getString("IMAGEPATH"));
				 ConnectUser.setRegdate(rs.getString("REGDATE"));
				
			 }
		}catch(Exception e) {e.printStackTrace();}
		
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(pstmt!=null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(
				"getUserInfo()함수 에서 Userdto user EMAIL"+ConnectUser.getEmail());
		System.out.println(
				"getUserInfo()함수 에서 Userdto user NAME"+ConnectUser.getName());
		 return  ConnectUser;
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
	
}
