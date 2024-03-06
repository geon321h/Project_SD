import javax.swing.JFrame;

public class Sign_UpFrame extends JFrame{

	public Sign_UpFrame(String title) {

		super(title);
		
		compose(); // 화면 구성
		setEvent();
		
		setSize(400,600);
		setVisible(true);
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 창 생성위치 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 프로그램 종료
		
	}

	private void setEvent() {
		
	}

	private void compose() {
		
	}
	
}
