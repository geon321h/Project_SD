
public class UserSD_DTO {

	private int no; 
	private String email;
	private String pw;
	private String name;
	private String birth;
	
	public UserSD_DTO(int no, String email, String pw, String name, String birth) {
		super();
		this.no = no;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return email;
	}

	public void setId(String id) {
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
	
	
	
}
