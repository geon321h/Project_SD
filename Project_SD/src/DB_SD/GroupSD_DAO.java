package DB_SD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupSD_DAO {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "sqlid";
	private String pw = "sqlpw";
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	GroupSD_DTO Dto = null;
	ArrayList<GroupSD_DTO> lists = null;
	
	public GroupSD_DAO() {

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
	
	public int insertGroup(int userNo, String groupName) {
		connect();
		String sql = "insert all "
						+ "  INTO GROUPSD(GROUP_NO,GROUP_NAME,GROUP_MANAGER_NO) "
						+ "  VALUES (GROUPSD_SEQ.NEXTVAL,?,?) "
						+ "  INTO GROUPSD_USER_LIST "
						+ "  VALUES (GROUPSD_SEQ.NEXTVAL,?,'Y') "
						+ "select * from dual";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupName);
			ps.setInt(2, userNo);
			ps.setInt(3, userNo);

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
	
	public GroupSD_DTO getGroupByNo(int groupNo) {
		connect();
		String sql = "SELECT * FROM GROUPSD WHERE GROUP_NO =?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, groupNo);
			rs = ps.executeQuery();
			if(rs.next()) { 
				int group_no = rs.getInt("group_no");
				String group_name  = rs.getString("group_name");
				int group_manager_no  = rs.getInt("group_manager_no");
				String group_create_day = String.valueOf(rs.getDate("group_create_day"));

				Dto = new GroupSD_DTO();
				Dto.setGroup_no(group_no);
				Dto.setGroup_name(group_name);
				Dto.setGroup_manager_no(group_manager_no);
				Dto.setGroup_create_day(group_create_day);
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
		return Dto;
	}
	
	public int checkManager(int no,int groupNo) {
		connect();
		String sql = "SELECT * FROM GROUPSD WHERE GROUP_MANAGER_NO = ? AND GROUP_NO = ?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setInt(2, groupNo);
			rs = ps.executeQuery();
			if(rs.next()) { 
				cnt=1;
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
		return cnt;
	}
	
	public int updateGroupName(String groupName, int groupNo) {
		connect();
		String sql = "UPDATE GROUPSD "
						+ "SET GROUP_NAME = ? "
						+"WHERE GROUP_NO = ?";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,groupName);
			ps.setInt(2,groupNo);

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
	
	public int deleteGroup(int groupNo) {
		connect();
		String sql = "DELETE GROUPSD "
						+ "WHERE GROUP_NO = ?";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,groupNo);

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
