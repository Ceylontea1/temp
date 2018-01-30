package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDao {
	
	private static FriendDao instance = null;
	
	private FriendDao() {}
	
	public static FriendDao getInstance() {
		if(instance == null) {
			synchronized(FriendDao.class) {
				instance = new FriendDao();
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
	
	public String checkState(String loginEmail, String friendEmail) {
		String state = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select STATE from FRIEND where EMAIL = ? and FRIENDEMAIL = ?";
		
		try {
			con = ConUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginEmail);
			pstmt.setString(2, friendEmail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				state = rs.getString("STATE");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.closeConnection(con, pstmt, rs);
		}
		
		return state;
	}

	public int reqFriend(String loginEmail,String friendEmail) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		UsersDao dao=UsersDao.getInstance();
		String sql=null;
		UsersDto dto=new UsersDto();
		int s1=0,s2=0,sum=0;
			try {
				conn=ConUtil.getConnection();
			dto=dao.getUserInfo(loginEmail);	
			sql="insert into FRIEND(EMAIL,FRIENDEMAIL,STATE)values(?,?,?)";
			pstmt=conn.prepareStatement(sql);//친구의 친구목록에 사용자 가 친구요청으로 등록
			pstmt.setString(1, friendEmail);
			pstmt.setString(2,loginEmail);
			pstmt.setString(3, "요청");
			s1=pstmt.executeUpdate();
			System.out.println("s1="+s1);
		
			dto=dao.getUserInfo(friendEmail);
			sql="insert into FRIEND(EMAIL,FRIENDEMAIL,STATE)values(?,?,?)";
			pstmt=conn.prepareStatement(sql);//사용자의 친구목록에 친구 가 친구요청으로 등록
			pstmt.setString(1, loginEmail);
			pstmt.setString(2,friendEmail);
			pstmt.setString(3, "요청");
			s2=pstmt.executeUpdate();
		
			System.out.println("s2="+s2);
			
			sum=s1+s2;
			if(sum==1) {
				if(s1==1) {
					sql="delete * from FRIEND where EMAIL=? AND FRIENDEMAIL=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, friendEmail);
					pstmt.setString(2,loginEmail);
					pstmt.executeQuery();
				}
				if(s2==1) {
					sql="delete * from FRIEND where EMAIL=? AND FRIENDEMAIL=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, loginEmail);
					pstmt.setString(2,friendEmail);
					pstmt.executeQuery();
				}
			}
			
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
 	
		return sum;
	}

	
	public UsersDto dtoMaker(ResultSet rs) throws SQLException {
		UsersDto dto=new UsersDto();
		
		 dto.setEmail(rs.getString("EMAIL"));
		 dto.setName(rs.getString("NAME"));
		 dto.setAge(rs.getInt("AGE"));
		 dto.setGender(rs.getString("GENDER"));
		 dto.setBirth(rs.getString("BIRTH"));
		 dto.setPhone(rs.getString("PHONE"));
		 dto.setimagepath(rs.getString("IMAGEPATH"));
		 dto.setRegdate(rs.getString("REGDATE"));
		return dto;
	}
	//친구인 사람제외하고 유저에서 사람 출력 <list>로 리턴
	public List<UsersDto> searchFriend(String method,String value,String loginEmail){
		
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		UsersDao dao=UsersDao.getInstance();
		String sql=null;
		UsersDto dto=new UsersDto();
		ResultSet rs=null;
		List<UsersDto> list=new ArrayList<UsersDto>(10);
		

		try {
			if(method.equals("name")) {
			sql ="select * from users where name=?";
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement(sql);	
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto=dtoMaker(rs);
				String state=getFRstate(dto,loginEmail);
				if(state.equals("")&&(!dto.getEmail().equals(loginEmail))) {//이미친구인 애랑 ,자기자신은 리스트에 넣지않음
				list.add(dto);}
				else continue;
			}
			}
			else if(method.equals("phone")) {
			sql ="select * from Users where PHONE=?";
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement(sql);	
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto=dtoMaker(rs);
				String state=getFRstate(dto,loginEmail);
				if(state.equals("")&&(!dto.getEmail().equals(loginEmail))) {//이미친구인 애랑 ,자기자신은 리스트에 넣지않음
				list.add(dto);}
				else continue;
			}
			}
			
			else if(method.equals("id")) {
				sql="select * from Users where EMAIL=?";
				conn=ConUtil.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, value);
				rs=pstmt.executeQuery();
				while(rs.next()){
					dto=dtoMaker(rs);
					String state=getFRstate(dto,loginEmail);
					if(state.equals("")&&(!dto.getEmail().equals(loginEmail))) {//이미친구인 애랑 ,자기자신은 리스트에 넣지않음
					list.add(dto);
					}
					else continue;
					
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	   
	   
			return list;
		
		
	}
	public String getFRstate(UsersDto dto,String loginEmail) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		String State="";
		sql="select STATE from FRIEND where EMAIL=? and FRIENDEMAIL=?";
		try {
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginEmail);
			pstmt.setString(2, dto.getEmail());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				State=rs.getString("STATE");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return State;
	}
	// 친구 수락 함수 <친구테이블 의 state를 요청을 -> 친구로 업데이트 시킨다 .> <됬으면,-> 알람의 데이터를 삭제한다.>
	public int agreeFriend(String loginEmail,String friendEmail) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int r=0;
		String sql="";
		
		try {
			conn=ConUtil.getConnection();
			sql="update Friend set STATE=? where (EMAIL=? and FRIENDEMAIL=?) or (EMAIL=?and FRIENDEMAIL=?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "친구");
			pstmt.setString(2, loginEmail);
			pstmt.setString(3, friendEmail);
			pstmt.setString(4, friendEmail);
			pstmt.setString(5, loginEmail);
			r=pstmt.executeUpdate();//r값은 2가 되어야지 성공적
			
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
		if(pstmt!=null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return r;			
	}
	
	
	public int FriendCount(String loginEmail) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from FRIEND where EMAIL=? ";
		int result=0;
		try {
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginEmail);
		
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt(1);
			}
			
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
		if(pstmt!=null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		
	}
	
	public List<UsersDto> showFRlist(String loginEmail){
		List<UsersDto> friendlist;
		int count=FriendCount(loginEmail);
		if(count>0) {
			friendlist=new ArrayList<UsersDto>(count);
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from FRIEND where EMAIL=? and STATE=? ";
		UsersDto dto=new UsersDto();
		try {
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginEmail);
			pstmt.setString(2, "친구");
			rs=pstmt.executeQuery();
			UsersDao dao=UsersDao.getInstance();
			while(rs.next()) {
		
				String friendEmail=rs.getString(2);
				dto=dao.getUserInfo(friendEmail);
				friendlist.add(dto);
		}
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
		if(rs!=null)
			try {
				rs.close();
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
		
		}else {
			friendlist=null;
		}
		
		return friendlist;
	}

	public int deleteFriend(String loginEmail,String friendEmail) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result1=0; int result2=0; int sum=0;
		String sql="delete from FRIEND where EMAIL=? and FRIENDEMAIL=? and STATE=?";
		try {
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,loginEmail);
			pstmt.setString(2,friendEmail);
			pstmt.setString(3,"친구");
			result1=pstmt.executeUpdate();
			  System.out.println("result1"+result1);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,friendEmail);
			pstmt.setString(2, loginEmail);
			pstmt.setString(3, "친구");
			result2=pstmt.executeUpdate();
			  System.out.println("result2"+result2);
			sum=result1+result2;
				System.out.println("sum"+sum);
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
		 if(pstmt!=null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return sum;
	}
	
}