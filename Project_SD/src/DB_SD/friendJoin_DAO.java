package DB_SD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class friendJoin_DAO {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "sqlid";
	private String pw = "sqlpw";
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	friendJoin_DTO Dto = null;
	ArrayList<friendJoin_DTO> lists = null;
	
	public friendJoin_DAO() {

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
	
	public ArrayList<friendJoin_DTO> getMyFriend(int no) {
		connect();
		String sql = "select myF.FRIEND_NO,fInfo.NAME,fInfo.BIRTH "
						+ "from (SELECT (CASE WHEN ? = USER_NO THEN FRIEND_NO ELSE USER_NO END) AS FRIEND_NO"
						+ "      FROM friendSD"
						+ "      where (USER_NO = ? OR FRIEND_NO = ?)"
						+ "      and FRIEND_CHECK = 'Y') myF"
						+ "      inner join userSD fInfo"
						+ "      on myF.FRIEND_NO = fInfo.NO";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setInt(2, no);
			ps.setInt(3, no);
			rs = ps.executeQuery();
			while(rs.next()) { 
				int friend_no = rs.getInt("FRIEND_NO");
				String name = rs.getString("NAME");
				String birth = String.valueOf(rs.getDate("birth"));

				Dto = new friendJoin_DTO();
				Dto.setNo(friend_no);
				Dto.setName(name);
				Dto.setBirth(birth);
				
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

	public ArrayList<friendJoin_DTO> getBeforeFriend(int no,String column, String column2) {
		connect();
		String sql = "SELECT fInfo.NAME, fr."+column2
						+ " FROM (SELECT USER_NO,FRIEND_NO  "
						+ "      FROM friendSD "
						+ "      where "+column+" = ? "
						+ "      and FRIEND_CHECK = 'N') fr"
						+ "      ,USERSD fInfo "
						+ "where fr."+column2+" = fInfo.NO";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while(rs.next()) { 
				String name = rs.getString("NAME");
				int friend_no = rs.getInt(column2);

				Dto = new friendJoin_DTO();
				Dto.setNo(friend_no);
				Dto.setName(name);
				
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

	public ArrayList<friendJoin_DTO> getBeforeFriendByName(int no, String searchName) {
		connect();
		String sql = "select userN.NO,userN.NAME "
						+ "from (SELECT (CASE WHEN ? = USER_NO THEN FRieND_NO ELSE USER_NO END) AS FRIEND_NO "
						+ "      FROM friendSD  "
						+ "      where (USER_NO = ? OR FRieND_NO = ?) ) friendN "
						+ "      right join userSD userN "
						+ "      on friendN.FRIEND_NO = userN.NO "
						+ "where (friendN.FRIEND_NO is null and not userN.NO = ?) and userN.NAME like ?";
		
		lists = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setInt(2, no);
			ps.setInt(3, no);
			ps.setInt(4, no);
			ps.setString(5, searchName);
			rs = ps.executeQuery();
			while(rs.next()) { 
				int friend_no = rs.getInt("NO");
				String name = rs.getString("NAME");

				Dto = new friendJoin_DTO();
				Dto.setNo(friend_no);
				Dto.setName(name);
				
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
