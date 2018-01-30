package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContentsDao {
	
	private static ContentsDao instance = null;
	
	private ContentsDao() {}
	
	public static ContentsDao getInstance() {
		if(instance == null) {
			synchronized(ContentsDao.class) {
				instance = new ContentsDao();
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
	
	//Content 가져옴
	public List<ContentsDto> getContentsDto(ResultSet rs) {
		ContentsDto dto = null;
		List<ContentsDto> contents = new ArrayList<ContentsDto>();
		
		try {
			while(rs.next()) {
				dto = new ContentsDto();
				dto.setEmail(rs.getString("EMAIL"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setContentnum(rs.getString("CONTENTNUM"));
				dto.setContent(rs.getString("CONTENT"));
				if(rs.getString("IMAGEPATH") == null) {
					dto.setImagepath(null);
				}
				else {
					dto.setImagepath(rs.getString("IMAGEPATH"));
				}
				dto.setScope(rs.getString("SCOPE"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setGood(rs.getString("GOOD"));
								
				contents.add(dto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return contents;
	}
	
	//Content에 있는 Writer로 친구 정보 파악
	public UsersDto getWriter(ResultSet rs) {
		UsersDao userDao = UsersDao.getInstance();
		UsersDto writer = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs2 = null;
		String sql = "select * from USERS where EMAIL = ?";
		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rs.getString("WRITER"));
			rs2 = pstmt.executeQuery();
			writer = userDao.getUser(rs2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs2);
		}
		
		return writer;
	}
	
	//loginEmail에 써진 글 갯수 파악
	public int getCount(String loginEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement("select count(*) from CONTENTS where EMAIL = ?");
			pstmt.setString(1, loginEmail);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeConnection(con, pstmt, rs);
		}
		
		return count;
	}
	
	//그냥 모든 글 가져오는 것
	public List<ContentsDto> getContents() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ContentsDto> contents = null;
		
		String sql = "select * from CONTENTS order by REGDATE desc";
		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			contents = getContentsDto(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
		
		return contents;
	}
	
	//loginEmail에 써진 글 가져오는 것
	public List<ContentsDto> getContent(String loginEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ContentsDto> contents = null;
		
		String sql = "select * from CONTENTS where EMAIL = ? order by REGDATE desc";
		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginEmail);
			rs = pstmt.executeQuery();
			
			contents = getContentsDto(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
		
		return contents;
	}
	//글을 가져오는 것은 모두 최신 글이 위로 오도록 함

	
	
}
