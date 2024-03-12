package DB_SD;

public class FriendJoin_DTO {

	// userSD //
	private int no; 
	private String email;
	private String pw;
	private String name;
	private String birth;
	
	// freindSD //
	private int user_no;
	private int friend_no;
	private String friend_check;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getFreind_no() {
		return friend_no;
	}
	public void setFreind_no(int freind_no) {
		this.friend_no = freind_no;
	}
	public String getFreind_check() {
		return friend_check;
	}
	public void setFreind_check(String freind_check) {
		this.friend_check = freind_check;
	}
	
	
	
}
