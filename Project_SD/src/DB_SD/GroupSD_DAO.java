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
	
}
