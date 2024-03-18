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
						+ "		 AND T21.GROUP_CHECK = 'Y' "
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
						+ "		 AND T21.GROUP_CHECK = 'Y' "
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
	
	public ArrayList<GroupJoin_DTO> getInviteFriend(int GroupNo, int no) {
		connect();
		String sql = "SELECT T20.FRIEND_NO "
						+ "FROM (SELECT USER_NO "
						+ "      FROM GROUPSD_USER_LIST "
						+ "      WHERE GROUP_NO = ? )T10 "
						+ "      RIGHT JOIN (SELECT (CASE WHEN ? = USER_NO THEN FRIEND_NO ELSE USER_NO END) AS FRIEND_NO "
						+ "      FROM FRIENDSD  "
						+ "      WHERE (USER_NO = ? OR FRIEND_NO = ?) "
						+ "      AND FRIEND_CHECK = 'Y') T20 "
						+ "      ON T10.USER_NO = T20.FRIEND_NO "
						+ "WHERE T10.USER_NO IS NULL";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, GroupNo);
			ps.setInt(2, no);
			ps.setInt(3, no);
			ps.setInt(4, no);
			rs = ps.executeQuery();
			while(rs.next()) { 
				int friend_no = rs.getInt("FRIEND_NO");

				Dto = new GroupJoin_DTO();
				Dto.setGroup_no(friend_no);
				
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
	
	public ArrayList<GroupJoin_DTO> getGroupUserList(int GroupNo, int no) {
		connect();
		String sql = "SELECT NAME,USER_NO "
						+ "FROM  (SELECT USER_NO,GROUP_CHECK "
						+ "       FROM GROUPSD_USER_LIST "
						+ "       WHERE GROUP_NO = ?) T10 "
						+ "       , USERSD T20 "
						+ "WHERE T20.NO = T10.USER_NO "
						+ "      AND NOT USER_NO = ? "
						+ "      AND T10.GROUP_CHECK = 'Y'";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, GroupNo);
			ps.setInt(2, no);
			rs = ps.executeQuery();
			while(rs.next()) { 
				String name = rs.getString("NAME");
				int user_no = rs.getInt("USER_NO");

				Dto = new GroupJoin_DTO();
				Dto.setGroup_no(user_no); // 임시로 사용
				Dto.setGroup_name(name); // 임시로 사용
				
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
	
	public ArrayList<GroupJoin_DTO> getWritingList(int GroupNo) {
		connect();
		String sql = "SELECT GROUP_NO,GROUP_WRITING_NO,GROUP_WRITING_TITLE,GROUP_WRITING_CONTENT,GROUP_WRITING_DAY,GROUP_WRITING_USER_NO,T20.NAME "
						+ "FROM (SELECT GROUP_NO,GROUP_WRITING_NO,GROUP_WRITING_TITLE,GROUP_WRITING_CONTENT,GROUP_WRITING_DAY,GROUP_WRITING_USER_NO "
						+ "      FROM GROUPSD_WRITING_LIST "
						+ "      WHERE GROUP_NO = ? "
						+ "      ORDER BY GROUP_WRITING_NO) T10 "
						+ "      , USERSD T20 "
						+ "WHERE T10.GROUP_WRITING_USER_NO = T20.NO";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, GroupNo);
			rs = ps.executeQuery();
			while(rs.next()) { 
				int group_no = rs.getInt("GROUP_NO");
				int group_writing_no = rs.getInt("GROUP_WRITING_NO");
				String group_writing_title = rs.getString("GROUP_WRITING_TITLE");
				String group_writing_content = rs.getString("GROUP_WRITING_CONTENT");
				String group_writing_day = String.valueOf(rs.getDate("GROUP_WRITING_DAY"));
				int group_writing_user_no = rs.getInt("GROUP_WRITING_USER_NO");
				String name = rs.getString("NAME");

				Dto = new GroupJoin_DTO();
				Dto.setGroup_no(group_no);
				Dto.setGroup_writing_no(group_writing_no); 
				Dto.setGroup_writing_title(group_writing_title); 
				Dto.setGroup_writing_content(group_writing_content); 
				Dto.setGroup_writing_day(group_writing_day); 
				Dto.setGroup_writing_user_no(group_writing_user_no); 
				Dto.setGroup_name(name); // 임시로 유저네임 대신 사용
				
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

	public ArrayList<GroupJoin_DTO> getWritingInfo(int groupNo, int writingNo) {

		connect();
		String sql = "SELECT GROUP_NO,GROUP_WRITING_NO,GROUP_WRITING_TITLE,GROUP_WRITING_CONTENT,GROUP_WRITING_DAY,GROUP_WRITING_USER_NO,T20.NAME "
						+ "FROM (SELECT GROUP_NO,GROUP_WRITING_NO,GROUP_WRITING_TITLE,GROUP_WRITING_CONTENT,GROUP_WRITING_DAY,GROUP_WRITING_USER_NO "
						+ "      FROM GROUPSD_WRITING_LIST "
						+ "      WHERE GROUP_NO = ? "
						+ "      AND GROUP_WRITING_NO = ? "
						+ "      ORDER BY GROUP_WRITING_NO) T10 "
						+ "      , USERSD T20 "
						+ "WHERE T10.GROUP_WRITING_USER_NO = T20.NO";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, groupNo);
			ps.setInt(2, writingNo);
			rs = ps.executeQuery();
			if(rs.next()) { 
				int group_no = rs.getInt("GROUP_NO");
				int group_writing_no = rs.getInt("GROUP_WRITING_NO");
				String group_writing_title = rs.getString("GROUP_WRITING_TITLE");
				String group_writing_content = rs.getString("GROUP_WRITING_CONTENT");
				String group_writing_day = String.valueOf(rs.getDate("GROUP_WRITING_DAY"));
				int group_writing_user_no = rs.getInt("GROUP_WRITING_USER_NO");
				String name = rs.getString("NAME");

				Dto = new GroupJoin_DTO();
				Dto.setGroup_no(group_no);
				Dto.setGroup_writing_no(group_writing_no); 
				Dto.setGroup_writing_title(group_writing_title); 
				Dto.setGroup_writing_content(group_writing_content); 
				Dto.setGroup_writing_day(group_writing_day); 
				Dto.setGroup_writing_user_no(group_writing_user_no); 
				Dto.setGroup_name(name); // 임시로 유저네임 대신 사용
				
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

	public int checkAuthority(int no, int group_no, int group_writing_no) {

		connect();
		String sql = "SELECT COUNT(*) AS CNT "
						+ "FROM GROUPSD T10 "
						+ "     , GROUPSD_WRITING_LIST T20 "
						+ "WHERE T10.GROUP_NO = T20.GROUP_NO "
						+ "AND T10.GROUP_NO = ? "
						+ "AND T20.GROUP_WRITING_NO = ? "
						+ "AND (T10.GROUP_MANAGER_NO = ? OR T20.GROUP_WRITING_USER_NO = ?)";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, group_no);
			ps.setInt(2, group_writing_no);
			ps.setInt(3, no);
			ps.setInt(4, no);
			rs = ps.executeQuery();
			if(rs.next()) { 
				cnt = rs.getInt("cnt");

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
	
	public int updateWriting(int wirtingNo, int groupNo,String title,String content) {
		connect();
		String sql = "UPDATE GROUPSD_WRITING_LIST "
						+ "SET GROUP_WRITING_TITLE = ? "
						+ "    ,GROUP_WRITING_CONTENT = ? "
						+ "WHERE GROUP_NO = ? "
						+ "    AND GROUP_WRITING_NO = ?";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,title);
			ps.setString(2,content);
			ps.setInt(3,groupNo);
			ps.setInt(4,wirtingNo);

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

	public int deleteWriting(int group_writing_no, int group_no) {
		connect();
		String sql = "DELETE GROUPSD_WRITING_LIST "
						+ "WHERE GROUP_WRITING_NO = ? "
						+ "AND GROUP_NO = ? ";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,group_writing_no);
			ps.setInt(2,group_no);

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

	public int InsertWriting(int group_no, String title, String content, int no) {
		connect();
		String sql = "INSERT INTO GROUPSD_WRITING_LIST "
						+ "VALUES (?,(SELECT NVL(MAX(GROUP_WRITING_NO),0)+1 FROM GROUPSD_WRITING_LIST) "
							+ " ,?,?,SYSDATE,?)";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, group_no);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setInt(4, no);

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
