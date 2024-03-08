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
			if(rs.next()) { 
				no = rs.getInt("no");
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

	public int insertUser(String emailValue, String pwValue, String nameValue, String birthValue) {
		connect();
		String sql = "insert into userSD values(userSD_seq.nextval,?,?,?,?)";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, emailValue);
			ps.setString(2, pwValue);
			ps.setString(3, nameValue);
			ps.setString(4, birthValue);

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

	public UserSD_DTO getUserById(int no) {
		connect();
		String sql = "select * from userSD where no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) { 
				no = rs.getInt("no");
				String email = rs.getString("name");
				String name = rs.getString("name");
				String birth = String.valueOf(rs.getDate("birth"));

				Dto = new UserSD_DTO();
				Dto.setNo(no);
				Dto.setEmail(email);
				Dto.setName(name);
				Dto.setBirth(birth);
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
	
	
	
}
 