import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class LoginFrame extends JFrame {
	
    private JLabel logo;
    private JTextField tfId;
    private JPasswordField tfPw;
    private JLabel loginMessage;
    private JButton btnLogin;
    private JButton btnSign_Up;
    private JPanel LoginForm ;
	
    Color mainColor = new Color(156, 93, 71);
    Color lightGray = new Color(211, 211, 211);
    Color inputWhite = new Color(247, 247, 247);
    
	public LoginFrame(String title) {

		super(title);
		
		compose(); // 화면 구성
		
		setSize(400,600);
		setVisible(true);
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 창 생성위치 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 프로그램 종료
	
	}

	private void compose() { // 화면 구성
		
		JPanel display = new JPanel();
		display.setBackground(Color.white);
		display.setLayout(null);
		this.add(display);
		
		LoginForm = new JPanel();
		LoginForm.setBackground(Color.white);
		LoginForm.setSize(300,400);
		LoginForm.setLocation(43, 75);
		LoginForm.setLayout(null);
		display.add(LoginForm);

		logo = new JLabel("Shared Diary");
		logo.setBounds(105, 0,90,50);
		LoginForm.add(logo);
		
		tfId = new JTextField(15);
		tfId.setBounds(0, 100,300,45);
		tfId.setBackground(inputWhite);
		tfId.setBorder(new LineBorder(lightGray,1,true));
		LoginForm.add(tfId);
		
		tfPw = new JPasswordField(15);
		tfPw.setBounds(0, 156,300,45);
		tfPw.setBackground(inputWhite);
		tfPw.setBorder(new LineBorder(lightGray,1,true));
		LoginForm.add(tfPw);
		
		loginMessage = new JLabel();
		loginMessage.setBounds(0, 220,100,20);
		LoginForm.add(loginMessage);
		
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(0, 250,300,45);
		btnLogin.setBackground(mainColor);
		btnLogin.setBorder(new LineBorder(mainColor,1,true));
		btnLogin.setForeground(Color.white);
		LoginForm.add(btnLogin);
		
		btnSign_Up = new JButton("회원가입");
		btnSign_Up.setBounds(0, 306,300,45);
		btnSign_Up.setBackground(Color.white);
		btnSign_Up.setBorder(new LineBorder(mainColor,1,true));
		btnSign_Up.setForeground(mainColor);
		LoginForm.add(btnSign_Up);
		
	}
	
	
	
}
