package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDao {
	private static LikeDao instance = null;

	private LikeDao() {
	}

	public static LikeDao getInstance() {
		if (instance == null) {
			synchronized (LikeDao.class) {
				instance = new LikeDao();
			}
		}
		return instance;
	}

	public boolean checkLiked(LikeDto LDto) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConUtil.getConnection();
			pstmt = conn.prepareStatement("select * from T_LIKE where CONTENTID = ? and LIKEUSER = ?");
			pstmt.setString(1, LDto.getContentid());
			pstmt.setString(2, LDto.getLikeuser());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
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
		return result;
	}

	public boolean insertLike(LikeDto LDto) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		if (!checkLiked(LDto)) {
			try {
				conn = ConUtil.getConnection();
				pstmt = conn.prepareStatement("insert into T_LIKE(CONTENTID,LIKEUSER) values(?,?)");
				pstmt.setString(1, LDto.getContentid());
				pstmt.setString(2, LDto.getLikeuser());
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
			result = true;
		}
		return result;
	}
	
}
