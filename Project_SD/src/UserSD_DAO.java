import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserSD_DAO {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "sqlid";
	private String pw = "sqlpw";
	
	UserSD_DTO Dto = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ArrayList<UserSD_DTO> lists = null;
	
	public UserSD_DAO() {

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

	public int checkUser(String emailValue, String pwValue) {
		connect();
		String sql = "select no from userSD where email=? and pw=?";
		int no = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, emailValue);
			ps.setString(2, pwValue);
			rs = ps.executeQuery();
			System.out.println(no);
			if(rs.next()) { 
				no = rs.getInt("no");
				System.out.println(no);
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
		return no;
		
	}
	
	
	
}
 