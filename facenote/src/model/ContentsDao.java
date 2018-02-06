package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	public List<ContentsDto> getContentbyEmail(String loginEmail) {
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

	public int getContentsCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0 ;
		try {
				conn = ConUtil.getConnection();
				pstmt = conn.prepareStatement("select count(*) from CONTENTS");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { 
				try {
					rs.close();
				}catch(SQLException e) {
					
				}
			}
			if(pstmt != null) { 
				try {
					pstmt.close();
				}catch(SQLException e) {
					
				}
			}
			if(conn != null) { 
				try {
					conn.close();
				}catch(SQLException e) {
					
				}
			}
		}
		return count;
	}
	
	public ContentsDto getContentbyNum(String contentid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentsDto article = null;
		
		try {
				conn = ConUtil.getConnection();
				pstmt = conn.prepareStatement("select * from CONTENTS where CONTENTNUM = ?");
				pstmt.setString(1, contentid);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					article = new ContentsDto();
					article.setEmail(rs.getString("email"));
					article.setWriter(rs.getString("writer"));
					article.setContent(rs.getString("content"));
					article.setImagepath(rs.getString("imagepath"));
					article.setRegdate(rs.getString("regdate"));
					article.setScope(rs.getString("scope"));
					article.setGood(rs.getString("good"));
					article.setContentnum(rs.getString("contentnum"));
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { 
				try {
					rs.close();
				}catch(SQLException e) {
					
				}
			}
			if(pstmt != null) { 
				try {
					pstmt.close();
				}catch(SQLException e) {
					
				}
			}
			if(conn != null) { 
				try {
					conn.close();
				}catch(SQLException e) {
					
				}
			}
		}
			return article;
	}
	
	public void updateContent(ContentsDto ConDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentsDto article = null;
		
		try {
				conn = ConUtil.getConnection();
				pstmt = conn.prepareStatement("update CONTENTS set EMAIL = ?, WRITER = ?, CONTENT = ?, IMAGEPATH = ?, SCOPE = ?, GOOD = ? where CONTENTNUM = ?");
				pstmt.setString(1, ConDto.getEmail());
				pstmt.setString(2, ConDto.getWriter());
				pstmt.setString(3, ConDto.getContent());
				pstmt.setString(4, ConDto.getImagepath());
				pstmt.setString(5, ConDto.getScope());
				pstmt.setString(6, ConDto.getGood());
				pstmt.setString(7, ConDto.getContentnum());
				
				pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { 
				try {
					rs.close();
				}catch(SQLException e) {
					
				}
			}
			if(pstmt != null) { 
				try {
					pstmt.close();
				}catch(SQLException e) {
					
				}
			}
			if(conn != null) { 
				try {
					conn.close();
				}catch(SQLException e) {
					
				}
			}
		}
	}
	
	public void insertContent(ContentsDto ConDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentsDto article = null;
		
		try {
				conn = ConUtil.getConnection();
				
				pstmt = conn.prepareStatement("insert into CONTENTS(EMAIL, WRITER, CONTENTNUM, CONTENT, IMAGEPATH, SCOPE, REGDATE, GOOD) values(?,?,?,?,?,?,?,?)");
				
				pstmt.setString(1, ConDto.getEmail());
				pstmt.setString(2, ConDto.getWriter());
				pstmt.setString(3, ConDto.getContentnum());
				pstmt.setString(4, ConDto.getContent());
				System.out.println("Contentid>>>>>>>>>>>>" + ConDto.getContentnum());
				System.out.println("imagepath>>>>" + ConDto.getImagepath());
				pstmt.setString(5, ConDto.getImagepath());
				pstmt.setString(6, ConDto.getScope());
				pstmt.setString(7, this.getDate());
				pstmt.setInt(8, 0);
				
				pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) { 
				try {
					rs.close();
				}catch(SQLException e) {
					
				}
			}
			if(pstmt != null) { 
				try {
					pstmt.close();
				}catch(SQLException e) {
					
				}
			}
			if(conn != null) { 
				try {
					conn.close();
				}catch(SQLException e) {
					
				}
			}
		}
	}
	
	public String getDate() {
		String time="";
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm",Locale.KOREA);
		Date currentTime=new Date();
		time=formatter.format(currentTime);
		
		return time;
	}
	
	public void deleteContent(String contentnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from CONTENTS where CONTENTNUM = ?";
		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, contentnum);
			pstmt.executeQuery();
		}
		catch(Exception e) {
			
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
	}
	
}
