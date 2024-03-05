import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
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
    private JPanel LoginForm;
    private JPanel tfIdForm;
    private JPanel tfPwForm;
	
    Style st = new Style();
    
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
		logo.setBounds(85, 0,150,50);
		logo.setFont(st.godo_B.deriveFont((float)20));
		logo.setForeground(st.mainColor);
		LoginForm.add(logo);
		
		tfIdForm = new JPanel();
		tfIdForm.setBounds(0, 100,300,45);
		tfIdForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfIdForm.setLayout(null);
		LoginForm.add(tfIdForm);
		tfId = new JTextField();
		tfId.setBounds(1, 1,298,43);
		tfId.setBackground(st.inputWhite);
		tfId.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfId.setForeground(st.inputBlack);
		tfId.setFont(st.noto_P);
		tfIdForm.add(tfId);
		//tfId.setMargin(new Insets(0, 0, 0, 0));
		
		tfPwForm = new JPanel();
		tfPwForm.setBounds(0, 156,300,45);
		tfPwForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfPwForm.setLayout(null);
		LoginForm.add(tfPwForm);
		tfPw = new JPasswordField();
		tfPw.setBounds(1, 1,298,43);
		tfPw.setBackground(st.inputWhite);
		tfPw.setBorder(new LineBorder(st.lightGray,1,true));
		tfPw.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfPw.setForeground(st.inputBlack);
		tfPwForm.add(tfPw);
		
		loginMessage = new JLabel();
		loginMessage.setBounds(0, 220,100,20);
		LoginForm.add(loginMessage);
		
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(0, 250,300,45);
		btnLogin.setBackground(st.mainColor);
		btnLogin.setBorder(new LineBorder(st.mainColor,1,true));
		btnLogin.setForeground(Color.white);
		btnLogin.setFont(st.godo_M);
		LoginForm.add(btnLogin);
		
		btnSign_Up = new JButton("회원가입");
		btnSign_Up.setBounds(0, 306,300,45);
		btnSign_Up.setBackground(Color.white);
		btnSign_Up.setBorder(new LineBorder(st.mainColor,1,true));
		btnSign_Up.setForeground(st.mainColor);
		btnSign_Up.setFont(st.godo_M);
		LoginForm.add(btnSign_Up);
		
	}
	
	
	
}
