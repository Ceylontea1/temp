package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplyDao {
	private static ReplyDao instance = null;

	private ReplyDao() {
	}

	public static ReplyDao getInstance() {
		if (instance == null) {
			synchronized (ReplyDao.class) {
				instance = new ReplyDao();
			}
		}
		return instance;
	}

	public String makeReplyid(ReplyDto ReDto) {

		String Replyid = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReplyDto> comments = null;

		try {
			conn = ConUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from REPLY where CONTENTNUM = ?");
			pstmt.setString(1, ReDto.getContentid());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Replyid = ReDto.getContentid() + "_" + rs.getInt(1);
			} else {
				Replyid = ReDto.getContentid() + "_0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
		return Replyid;
	}

	public void writeReply(ReplyDto ReDto, String uploadFilePath) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		UsersDao UDao = UsersDao.getInstance();
		
		try {
			conn = ConUtil.getConnection();
			pstmt = conn.prepareStatement("insert into REPLY(REPLYID,CONTENTNUM,WRITER,CONTENT,WRITERNAME) values(?,?,?,?,?)");
			pstmt.setString(1, makeReplyid(ReDto));
			pstmt.setString(2, ReDto.getContentid());
			pstmt.setString(3, ReDto.getWriter());
			pstmt.setString(4, ReDto.getContent());
			pstmt.setString(5, UDao.getUser(ReDto.getWriter(), uploadFilePath).getName());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
	}

	public List<ReplyDto> getReplys(String contentid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReplyDto> comments = null;

		try {
			conn = ConUtil.getConnection();
			pstmt = conn.prepareStatement("select * from REPLY where CONTENTNUM = ?  ORDER BY REGDATE DESC ");
			pstmt.setString(1, contentid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (comments == null) {
					comments = new ArrayList<ReplyDto>();
				}
				ReplyDto comment = new ReplyDto();
				comment.setReplyid(rs.getString("replyid"));
				comment.setContentid(rs.getString("contentnum"));
				comment.setWriter(rs.getString("writer"));
				comment.setContent(rs.getString("content"));
				comment.setRegdate(rs.getTimestamp("regdate"));
				comment.setWritername(rs.getString("writername"));
				comments.add(comment);
			}
			if (comments == null) {
				comments = Collections.emptyList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
		return comments;
	}

	public ReplyDto getReply(String contentid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReplyDto Reply = null;

		try {
			conn = ConUtil.getConnection();
			pstmt = conn.prepareStatement("select * from REPLY where CONTENTNUM = ? ORDER BY REGDATE DESC ");
			pstmt.setString(1, contentid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Reply = new ReplyDto();
				Reply.setReplyid(rs.getString("replyid"));
				Reply.setContentid(rs.getString("contentnum"));
				Reply.setWriter(rs.getString("writer"));
				Reply.setContent(rs.getString("content"));
				Reply.setRegdate(rs.getTimestamp("regdate"));
			}
		} catch ( Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
		return Reply;
	}
	
	public void deleteReply(ReplyDto ReDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConUtil.getConnection();
			pstmt = conn.prepareStatement("delete from REPLY where REPLYID = ?");
			pstmt.setString(1, ReDto.getReplyid());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
	}
}
