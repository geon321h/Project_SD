package DB_SD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Groupsd_user_list_DAO {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "sqlid";
	private String pw = "sqlpw";
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Groupsd_user_list_DTO Dto = null;
	ArrayList<Groupsd_user_list_DTO> lists = null;
	
	public Groupsd_user_list_DAO() {

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
	
	public int getMyGroupCount(int no) {
		connect();
		String sql = "SELECT COUNT(GROUP_NO) COUNT "
						+ "FROM GROUPSD_USER_LIST "
						+ "WHERE USER_NO = ? "
						+ "and GROUP_CHECK = 'Y'";
			
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) { 
				
				count = rs.getInt("COUNT");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 5.사용한 자원 반납
				if(ps != null) {
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
				System.out.println("접속 종료");
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return count;
	}

	public int updateGroup(int no, int groupNo) {
		connect();
		String sql = "UPDATE GROUPSD_USER_LIST "
						+ "SET GROUP_CHECK = 'Y' "
						+ "WHERE GROUP_NO = ? "
						+ "AND USER_NO = ? ";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,groupNo);
			ps.setInt(2,no);

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

	public int deleteGroup(int no, int groupNo) {
		connect();
		String sql = "DELETE GROUPSD_USER_LIST "
						+ "WHERE  GROUP_NO = ? "
						+ "AND USER_NO = ? "
						+ "AND GROUP_CHECK = 'N'";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,groupNo);
			ps.setInt(2,no);

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
	
	public int deleteGroupUser(int no, int groupNo) {
		connect();
		String sql = "DELETE GROUPSD_USER_LIST "
						+ "WHERE  GROUP_NO = ? "
						+ "AND USER_NO = ? "
						+ "AND GROUP_CHECK = 'Y'";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,groupNo);
			ps.setInt(2,no);

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
	
	public int insertGroup(int userNo,int groupNo) {
		connect();
		String sql = "INSERT INTO GROUPSD_USER_LIST VALUES (?,?,'N')";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, groupNo);
			ps.setInt(2, userNo);

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
