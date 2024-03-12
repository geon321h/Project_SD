package DB_SD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendSD_DAO {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "sqlid";
	private String pw = "sqlpw";
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	FriendSD_DTO Dto = null;
	ArrayList<FriendSD_DTO> lists = null;
	
	public FriendSD_DAO() {

		try {

			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("jar 파일 누락");
			e.printStackTrace();
		}
	
	}
	
	public void connect () {
		
		try {	
			
			conn = DriverManager.getConnection(url,id,pw);
			System.out.println("접속 성공");
			
		} catch (SQLException e) {
				System.out.println("접속 실패");
				e.printStackTrace();
		}
	
	}
	
	public int deleteFriend(int userNo,int friendNo) {
		connect();
		String sql = "delete friendSD "
						+ "where ((friend_no = ? and user_no = ?)"
						+ "      OR (friend_no = ? and user_no = ?))"
						+ "      AND FRIEND_CHECK = 'Y' ";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,friendNo);
			ps.setInt(2,userNo);
			ps.setInt(3,userNo);
			ps.setInt(4,friendNo);

			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 5.사용한 자원 반납
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
				System.out.println("접속 종료");
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return cnt;
		
	}

	public int updateFriend(int userNo, int friendNo) {
		connect();
		String sql = "update friendSD "
						+ "set FRIEND_CHECK = 'Y' "
						+ "where user_no = ? and friend_no = ? "
						+ "      AND FRIEND_CHECK = 'N'";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,friendNo);
			ps.setInt(2,userNo);

			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 5.사용한 자원 반납
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
				System.out.println("접속 종료");
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return cnt;
	}

	public int deleteBeforeFriend(int userNo, int friendNo) {
		connect();
		String sql = "delete friendSD "
						+ "where (friend_no = ? and user_no = ? )"
						+ "  AND FRIEND_CHECK = 'N'";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,userNo);
			ps.setInt(2,friendNo);

			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 5.사용한 자원 반납
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
				System.out.println("접속 종료");
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return cnt;		
	}

	public int insertFriend(int userNo, int friendNo) {
		connect();
		String sql = "insert into friendSD values(?,?,?)";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			ps.setInt(2, friendNo);
			ps.setString(3, "N");

			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 5.사용한 자원 반납
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
				System.out.println("접속 종료");
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return cnt;
	}
	
}
