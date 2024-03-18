import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.management.loading.PrivateClassLoader;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {
	
	// main container //
	private JPanel LoginForm; 
	// logo // 
    private JLabel logo;
    // IdInput //
    private JPanel tfEmailForm;
    private JTextField tfEmail;
    // PwInput //
    private JPanel tfPwForm;
    private JTextField tfPwDefault;
    private JPasswordField tfPw;
    // Message  //
    private JLabel loginMessage;
    // Button //
    private JButton btnLogin;
    private JButton btnSign_Up;
    private boolean loginErr = false;
    private boolean sign_UPErr = false;
    // 참조 클래스 //
    Style st = new Style();
    UserSD_DAO userDao = new UserSD_DAO();
    
	public LoginFrame(String title) {

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

		tfEmail.addKeyListener(new keyHandler());
		tfEmail.addFocusListener(new focusHandler());

		tfPw.addFocusListener(new focusHandler());
		tfPw.addKeyListener(new keyHandler());
		tfPwDefault.addFocusListener(new focusHandler());
		
		btnLogin.addActionListener(new ActionHandler());
		btnLogin.addMouseListener(new MouseHandler());
		btnSign_Up.addActionListener(new ActionHandler());
		btnSign_Up.addMouseListener(new MouseHandler());
		
	}

	private void compose() { // 화면 구성
		
		JPanel display = new JPanel();
		display.setBackground(Color.white);
		display.setLayout(null);
		this.add(display);
		
		// 로그인 area //
		LoginForm = new JPanel();
		LoginForm.setBackground(Color.white);
		LoginForm.setSize(300,400);
		LoginForm.setLocation(43, 75);
		LoginForm.setLayout(null);
		display.add(LoginForm);

		// 로고 //
		logo = new JLabel(" ", JLabel.CENTER);
		logo.setBounds(70, 0,180,50);
		ImageIcon Icon = new ImageIcon("image/logo.png");
		logo.setIcon(Icon);
		LoginForm.add(logo);
		
		
		// 아이디 입력 //
		tfEmailForm = new JPanel();
		tfEmailForm.setBounds(0, 100,300,45);
		tfEmailForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfEmailForm.setLayout(null);
		LoginForm.add(tfEmailForm);
		
		tfEmail = new JTextField("이메일");
		tfEmail.setBounds(1, 1,298,43);
		tfEmail.setBackground(st.inputWhite);
		tfEmail.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfEmail.setForeground(st.inputGray);
		tfEmail.setFont(st.neo_R);
		tfEmailForm.add(tfEmail);

		
		//tfId.setMargin(new Insets(0, 0, 0, 0));
		
		// 비밀번호 입력 //
		tfPwForm = new JPanel();
		tfPwForm.setBounds(0, 156,300,45);
		tfPwForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfPwForm.setLayout(null);
		LoginForm.add(tfPwForm);
		
		tfPwDefault = new JTextField("비밀번호");
		tfPwDefault.setBounds(1, 1,298,43);
		tfPwDefault.setBackground(st.inputWhite);
		tfPwDefault.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfPwDefault.setForeground(st.inputGray);
		tfPwDefault.setFont(st.neo_R);
		tfPwForm.add(tfPwDefault);
		
		tfPw = new JPasswordField("");
		tfPw.setBounds(1, 1,298,43);
		tfPw.setBackground(st.inputWhite);
		tfPw.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfPw.setForeground(st.inputBlack);
		tfPw.setVisible(false);
		tfPwForm.add(tfPw);
		
		// 로그인 경고 //
		loginMessage = new JLabel();
		loginMessage.setBounds(0, 215,300,20);
		loginMessage.setForeground(Color.red);
		loginMessage.setFont(st.neo_R.deriveFont((float)12));
		LoginForm.add(loginMessage);
		
		// 로그인 버튼 //
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(0, 250,300,45);
		btnLogin.setBackground(st.mainColor);
		btnLogin.setBorder(new LineBorder(st.mainColor,1,true));
		btnLogin.setForeground(Color.white);
		btnLogin.setFont(st.neo_B);
		
		btnLogin.setFocusPainted(false);
		LoginForm.add(btnLogin);
		
		// 회원가입 버튼 //
		btnSign_Up = new JButton("회원가입");
		btnSign_Up.setBounds(0, 306,300,45);
		btnSign_Up.setBackground(Color.white);
		btnSign_Up.setBorder(new LineBorder(st.mainColor,1,true));
		btnSign_Up.setForeground(st.mainColor);
		btnSign_Up.setFont(st.neo_B);
		btnSign_Up.setContentAreaFilled(false);
		btnSign_Up.setFocusPainted(false);
		LoginForm.add(btnSign_Up);
		
	}
	
	// 키보드 이벤트 //
	class keyHandler extends KeyAdapter{
		
		public void keyTyped(KeyEvent e) {
			Object obj = e.getSource();
			if(obj == tfEmail) {
				tfEmail.requestFocus();
				if(tfEmail.getText().contains("이메일")) {
					tfEmail.setForeground(st.inputBlack);
					String emailInput = tfEmail.getText().replace("이메일","");
					tfEmail.setText(emailInput);
					tfEmailForm.setBorder(new LineBorder(st.mainColor,1,true));
					loginMessageClear();
					loginErr = false;
				}
			}
		}
		
		public void keyPressed(KeyEvent e){
		
			Object obj = e.getSource();
			
			// Input 박스 //
			if(obj == tfPw) {
				tfPwForm.setBorder(new LineBorder(st.mainColor,1,true));
				loginMessageClear();
				sign_UPErr = false;
			}
			
		}
		
		public void keyReleased(KeyEvent e){
			
			Object obj = e.getSource();
			
			// Input 박스 //
			if(obj == tfEmail) {
				if(tfEmail.getText().equals("")){
					tfEmail.setForeground(st.inputGray);
					tfEmail.setText("이메일");
					tfEmail.select(0, 0);
				}
			}
			

		}
		
		// 로그인 메세지 클리어 //
		private void loginMessageClear() {
			loginMessage.setText("");
		}
		
	}
	
	// 포커스 이벤트 //
	class focusHandler extends FocusAdapter{
		
		
		public void focusGained(FocusEvent e) {

			Object obj = e.getSource();
					
			// Input 박스 //
			if(obj == tfEmail) {
				if(tfEmail.getText().equals("이메일")){
					tfEmail.select(0, 0);
				}
				if(loginErr == false) {
					tfEmailForm.setBorder(new LineBorder(st.mainColor,1,true));
				}
			}else if(obj == tfPw && sign_UPErr == false) {
				tfPwForm.setBorder(new LineBorder(st.mainColor,1,true));
			}
			if(obj == tfPwDefault) {
				tfPw.setVisible(true);
				tfPw.requestFocus();
				tfPwDefault.setVisible(false);
			}
		
		}
		
		public void focusLost(FocusEvent e) {
		
			Object obj = e.getSource();
			
			// Input 박스 //
			if(obj == tfEmail && loginErr == false) {
				tfEmailForm.setBorder(new LineBorder(st.lightGray,1,true));
			}else if(obj == tfPw && sign_UPErr == false) {
				tfPwForm.setBorder(new LineBorder(st.lightGray,1,true));
			}
			if(obj == tfPw) {
				String pwValue = new String(tfPw.getPassword());
				if(pwValue.equals("")){
					tfPwDefault.setVisible(true);
				}
			}
			
		}
		
	}
	
	// 마우스 이벤트 //
	class MouseHandler extends MouseAdapter{
		
		public void mouseEntered(MouseEvent e) {

			Object obj = e.getSource();
			
			if(obj == btnLogin) {
				btnLogin.setBackground(st.mainColor_shades);
			}else if(obj  == btnSign_Up) {
				btnSign_Up.setBorder(new LineBorder(st.mainColor_thin,1,true));
				btnSign_Up.setForeground(st.mainColor_thin);
			}
			
		}
		
		public void mouseExited(MouseEvent e) {

			Object obj = e.getSource();
			
			if(obj == btnLogin) {
				btnLogin.setBackground(st.mainColor);
			}else if(obj  == btnSign_Up) {
				btnSign_Up.setBorder(new LineBorder(st.mainColor,1,true));
				btnSign_Up.setForeground(st.mainColor);
			}
			
		}
		
	}
	
	// 액션 이벤트 //
	class ActionHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			
			// Login 버튼 //
			if(obj == btnLogin) {
				
				int no = checkLogin();
				if(no != -1) {
					
					System.out.println("로그인 성공");
					setVisible(false);
					MainFrame suf = new MainFrame("Shared Diary",no); 
					
				}
				
			}
			
			// Sign up 버튼 //
			if(obj == btnSign_Up) {
				
				setVisible(false);
				Sign_UpFrame suf = new Sign_UpFrame("Shared Diary : Sign Up"); 
				
			}
			
		}

	}
	// 아이디와 비밀번호 입력 체크 //
	private int checkLogin() {
		
		String emailValue = tfEmail.getText();
		String pwValue = new String(tfPw.getPassword());
		if(tfEmail.getText().equals("이메일")) {
			loginMessage.setText("이메일을 입력해주세요.");
			tfEmailForm.setBorder(new LineBorder(Color.red,1,true));
			loginErr = true;
		}
		if(loginErr && pwValue.equals("")) {
			loginMessage.setText("이메일과 비밀번호를 입력해주세요.");
			tfPwForm.setBorder(new LineBorder(Color.red,1,true));
			sign_UPErr = true;
			
		}else if(pwValue.equals("")){
			loginMessage.setText("비밀번호를 입력해주세요.");
			tfPwForm.setBorder(new LineBorder(Color.red,1,true));
			sign_UPErr = true;
		}
		
		if(!loginErr && !sign_UPErr) {
			
			int no = userDao.checkUser(emailValue,pwValue);
			
			if(no == -1) {
				
				loginMessage.setText("이메일 또는 비밀번호가 잘못되었습니다.");
				tfEmailForm.setBorder(new LineBorder(Color.red,1,true));
				tfPwForm.setBorder(new LineBorder(Color.red,1,true));
				tfEmail.setText("이메일");
				tfPw.setText("");
				loginErr = true;
				sign_UPErr = true;
				
			}else {
				
				return no;
				
			}
			
		}
		return -1;
		
	}
	
}
