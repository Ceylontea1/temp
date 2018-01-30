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

/*
 * 수정내용
 * 
 * DB 연결 후 Connection 제거를 안 한 것 수정
 * 그 후로 알림을 여러번 눌러도 멈추는 것이 사라졌는데 아마 저거때문이 아니었나 싶음
 * 
 */

public class AlarmDao {
	private static AlarmDao instance=null;
	private AlarmDao() {}
	public static AlarmDao getInstance() {
		if(instance==null) {
			synchronized (AlarmDao.class) {
				instance= new AlarmDao();
			}
		}
		return instance;
	}
	public String curtuntTime() {
		String time="";
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA);
		Date currentTime=new Date();
		time=formatter.format(currentTime);
		return time;
	}
	public String date(){
		String date="";
		SimpleDateFormat formatter= new SimpleDateFormat("MM-dd",Locale.KOREA);
		Date currentDate=new Date();
		date=formatter.format(currentDate);
		return date;
	}
	public int time() {
		int time=0;
		SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
		Date currentTime=new Date();
		String Timetmp=formatter.format(currentTime);
		Timetmp.split(":");

		return  time;
	}

	public int getAlarmCount(String loginEamil) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int count=0;

		try {
			conn=ConUtil.getConnection();
			sql="select count(*) from ALARM where EMAIL=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginEamil);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	public int maxCount(String loginEmail) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select MAX(COUNT) from ALARM where EMAIL=?";
		int count=0;
		try {
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginEmail);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public String[] friendBirthdayCheck(String loginEamil) {//데이가 같을때 인설트한다.

	
		String currentDate=date();
		String Cmonth=currentDate.split("-")[0];
		String Cday=currentDate.split("-")[1];
		FriendDao Fdao=FriendDao.getInstance();
		List<UsersDto> list=Fdao.showFRlist(loginEamil);
		System.out.println("FriendBirthdayCheck  list"+list);
		if(list==null)return null;
		String[] BirthdayFriend=new String[list.size()];	
		System.out.println("FriendBirthdayCheck  listsize"+list.size());

		int count=0;
		for(UsersDto tmp: list) {

			String[] birthday=tmp.getBirth().split("-");
			if(Cmonth.equals(birthday[1])&&Cday.equals(birthday[2])) {
				BirthdayFriend[count]=tmp.getEmail();
				count++;
			}
		}
		

		return BirthdayFriend;
	}
	public void insertBirthdayAlarm(String num,String loginEamil){
		String date="";
		int count=0;
		int counttmp=getAlarmCount(loginEamil);
		if(counttmp==0) {
			count=0;
		}
		else if(counttmp!=0) {
			count=maxCount(loginEamil);
			count=count+1;
		}
		Connection conn=null;
		PreparedStatement pstmt=null;
		String[] FRBDid;
		String sql="";
		ResultSet rs=null;
		UsersDao Udao=UsersDao.getInstance();
		UsersDto Udto=null;
		if(num.equals("3")) {
			System.out.println("BirthdatAlarm  loginEamil"+ loginEamil);
			FRBDid=friendBirthdayCheck(loginEamil);
			if(FRBDid[0]==null)return;
			if(FRBDid[0]!=null) {
				try {
					conn=ConUtil.getConnection();

					for(int i=0; i<FRBDid.length;i++) {
						Udto=Udao.getUserInfo(FRBDid[i]); 
						sql="select REGDATE from ALARM where EMAIL=? and CONTENT=? and FRIENDEMAIL=?";
						pstmt=conn.prepareStatement(sql);
						pstmt.setString(1, loginEamil);
						pstmt.setString(2, "3");
						pstmt.setString(3, Udto.getEmail());
						rs=pstmt.executeQuery();
						while (rs.next()) {
							date=rs.getString(1);
							System.out.println("BirthdatAlarm while 문안에 있는것"+date);
						}
						String currentDate=curtuntTime().split(" ")[0];
						System.out.println("BirthdatAlarm currentDate"+currentDate);
						String datetmp=date.split(" ")[0];
						System.out.println("BirthdatAlarm datetmp"+datetmp);
					
						if(rs != null) {
							rs.close();
						}
						if(pstmt != null) {
							pstmt.close();
						}
						if(!currentDate.equals(datetmp)) {
					
							sql="insert into ALARM(EMAIL,FRIENDEMAIL,NAME,CONTENT,REGDATE,COUNT)values(?,?,?,?,?,?) ";
							pstmt=conn.prepareStatement(sql);
							pstmt.setString(1,loginEamil);
							pstmt.setString(2, Udto.getEmail());
							pstmt.setString(3, Udto.getName());
							pstmt.setString(4, num);
							pstmt.setString(5, curtuntTime());
							pstmt.setInt(6, count);
							pstmt.executeUpdate();
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				finally {
					try {
						if(rs != null) {
							rs.close();
						}
						if(pstmt != null) {
							pstmt.close();
						}
						if(conn != null) {
							conn.close();
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}	
		}
	}
	//친구요청알람
	public void insertFriendAlarm(String num,String AlarmId,String loginEamil) {
		int count=0;
		int counttmp=getAlarmCount(AlarmId);
		if(counttmp==0) {
			count=0;
		}
		else if(counttmp!=0) {
			count=maxCount(AlarmId);
			count=count+1;
		}
		Connection conn=null;
		PreparedStatement pstmt=null;
		String[] FRBDid;
		String sql="";
		UsersDao Udao=UsersDao.getInstance();
		UsersDto Udto=null;
		//num=1 <댓글> num=2<친구요청> num=3 <생일>

		if(num.equals("2")) {

			try {
				conn=ConUtil.getConnection();
				sql="insert into ALARM(EMAIL,FRIENDEMAIL,NAME,CONTENT,REGDATE,COUNT)values(?,?,?,?,?,?) ";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,AlarmId);
				pstmt.setString(2,loginEamil);
				UsersDto dto=Udao.getUserInfo(loginEamil);
				pstmt.setString(3,dto.getName());
				pstmt.setString(4, num);
				pstmt.setString(5,curtuntTime());
				pstmt.setInt(6, count);//count넣기
				int a=pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

	}



	public List<AlarmDto> getAlarmList(String loginEamil){
		List<AlarmDto> list=new ArrayList<AlarmDto>(20);
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		AlarmDto Aldto=null;
		try {
			conn=ConUtil.getConnection();
			sql="SELECT A.EMAIL, A.FRIENDEMAIL, A.NAME, A.CONTENT, A.REGDATE,A.COUNT, U.IMAGEPATH FROM USERS U INNER JOIN ALARM A ON A.FRIENDEMAIL = U.EMAIL \r\n" + 
					"where A.EMAIL=? ORDER BY A.REGDATE ASC";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginEamil);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Aldto=new AlarmDto();
				Aldto.setEmail(rs.getString(1));
				Aldto.setFriendemail(rs.getString(2));
				Aldto.setName(rs.getString(3));
				Aldto.setContent(rs.getString(4));
				Aldto.setRegdate(rs.getString(5));
				Aldto.setCount(rs.getInt(6));
				Aldto.setFrimg(rs.getString(7));
				list.add(Aldto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public int alarmDelete(int count,String loginEamil,String friendEmail) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int r=0;
		try {
			System.out.println("알림 삭제에서 카운트"+count);
			conn=ConUtil.getConnection();
			sql="delete from ALARM where COUNT=? AND EMAIL=? AND FRIENDEMAIL=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, loginEamil);
			pstmt.setString(3, friendEmail);
			r=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return r;//r=1일떄 성공,r=0일떄 실패

	}
	//새글을 썻을때 친구들에게 알림을 넣어주는 기능!
	public void insertNewTextAlarm(String loginEamil, String num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		FriendDao dao=FriendDao.getInstance();
		List<UsersDto> frlist=dao.showFRlist(loginEamil);
		UsersDao Udao=UsersDao.getInstance();
		UsersDto dto=Udao.getUserInfo(loginEamil);//접속사 유저
	
		for(UsersDto tmpdtp: frlist) {
			
		String sql="insert into ALARM(EMAIL,FRIENDEMAIL,NAME,CONTENT,REGDATE,COUNT)values(?,?,?,?,?,?)";
		int count=0;
		int counttmp=getAlarmCount(tmpdtp.getEmail());
		if(counttmp==0) {
			count=0;
		}
		else if(counttmp!=0) {
			count=maxCount(tmpdtp.getEmail());
			count=count+1;
		}
		try {
			conn=ConUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tmpdtp.getEmail());
			pstmt.setString(2, loginEamil);
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, num);
			pstmt.setString(5, curtuntTime());
			pstmt.setInt(6,count);
			rs=pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}}
		}

	}

}
