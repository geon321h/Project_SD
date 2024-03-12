package DB_SD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupJoin_DAO {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "sqlid";
	private String pw = "sqlpw";
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	GroupJoin_DTO Dto = null;
	ArrayList<GroupJoin_DTO> lists = null;
	
	public GroupJoin_DAO() {

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
	
	public ArrayList<GroupJoin_DTO> getMyGroup(int no) {
		connect();
		String sql = "SELECT T10.GROUP_NO,GROUP_NAME,GROUP_CREATE_DAY,USER_COUNT "
						+ "FROM GROUPSD T10 "
						+ "     ,(SELECT T22.GROUP_NO,COUNT(T21.USER_NO)USER_COUNT "
						+ "       FROM GROUPSD_USER_LIST T21 "
						+ "            ,(SELECT GROUP_NO "
						+ "              FROM GROUPSD_USER_LIST "
						+ "              WHERE USER_NO = ? "
						+ "			   AND GROUP_CHECK = 'Y') T22 "
						+ "       WHERE T21.GROUP_NO = T22.GROUP_NO "
						+ "       GROUP BY T22.GROUP_NO) T20 "
						+ "WHERE T10.GROUP_NO = T20.GROUP_NO "
						+ "ORDER BY T10.GROUP_NO";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while(rs.next()) { 
				int group_no = rs.getInt("GROUP_NO");
				String group_name = rs.getString("GROUP_NAME");
				int user_count = rs.getInt("USER_COUNT");
				String group_create_day = String.valueOf(rs.getDate("GROUP_CREATE_DAY"));

				Dto = new GroupJoin_DTO();
				Dto.setGroup_no(group_no);
				Dto.setGroup_name(group_name);
				Dto.setGroup_manager_no(user_count);
				Dto.setGroup_create_day(group_create_day);
				
				lists.add(Dto);
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
		return lists;
	}

	public ArrayList<GroupJoin_DTO> getMyManagerGroup(int no) {
		connect();
		String sql = "SELECT T10.GROUP_NO,GROUP_NAME,GROUP_CREATE_DAY,USER_COUNT "
						+ "FROM GROUPSD T10 "
						+ "     ,(SELECT T22.GROUP_NO,COUNT(T21.USER_NO)USER_COUNT "
						+ "       FROM GROUPSD_USER_LIST T21 "
						+ "            ,(SELECT GROUP_NO "
						+ "              FROM GROUPSD_USER_LIST "
						+ "              WHERE USER_NO = ? "
						+ "			   AND GROUP_CHECK = 'Y') T22 "
						+ "       WHERE T21.GROUP_NO = T22.GROUP_NO "
						+ "       GROUP BY T22.GROUP_NO) T20 "
						+ "WHERE T10.GROUP_NO = T20.GROUP_NO and T10.GROUP_MANAGER_NO = ? "
						+ "ORDER BY T10.GROUP_NO";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setInt(2, no);
			rs = ps.executeQuery();
			while(rs.next()) { 
				int group_no = rs.getInt("GROUP_NO");
				String group_name = rs.getString("GROUP_NAME");
				int user_count = rs.getInt("USER_COUNT");
				String group_create_day = String.valueOf(rs.getDate("GROUP_CREATE_DAY"));

				Dto = new GroupJoin_DTO();
				Dto.setGroup_no(group_no);
				Dto.setGroup_name(group_name);
				Dto.setGroup_manager_no(user_count);
				Dto.setGroup_create_day(group_create_day);
				
				lists.add(Dto);
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
		return lists;
	}
	
	public ArrayList<GroupJoin_DTO> getInviteGroup(int no) {
		connect();
		String sql = "SELECT GROUP_NAME, T10.GROUP_NO "
						+ "FROM (SELECT GROUP_NO "
						+ "      FROM GROUPSD_USER_LIST "
						+ "      WHERE USER_NO = ? "
						+ "      and GROUP_CHECK = 'N')T10 "
						+ "      , GROUPSD T20 "
						+ "WHERE T10.GROUP_NO = T20.GROUP_NO";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while(rs.next()) { 
				String group_name = rs.getString("GROUP_NAME");
				int group_no = rs.getInt("GROUP_NO");

				Dto = new GroupJoin_DTO();
				Dto.setGroup_name(group_name);
				Dto.setGroup_no(group_no);
				
				lists.add(Dto);
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
		return lists;
	}
	
}
