
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame(String title, int no) {

		super(title);
		
		compose(); // 화면 구성
		setEvent();
		
		setSize(1300,800);
		setVisible(true);
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 창 생성위치 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 프로그램 종료
		
	}
	
	private void setEvent() {
		
	}

	private void compose() {
		
	}

	public static void main(String[] args) {
		Sign_UpFrame rf = new Sign_UpFrame("test"); // 프로그램 실행시 로그인 화면 출력
		//LoginFrame rf = new LoginFrame("Shared Diary : Login"); // 프로그램 실행시 로그인 화면 출력
		
	}

}
