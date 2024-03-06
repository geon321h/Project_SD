import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.management.loading.PrivateClassLoader;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {
	
    private JLabel logo;
    private JTextField tfId;
    private JTextField tfPwDefault;
    private JPasswordField tfPw;
    private JLabel loginMessage;
    private JButton btnLogin;
    private JButton btnSign_Up;
    private JPanel LoginForm;
    private JPanel tfIdForm;
    private JPanel tfPwForm;
    
    private boolean loginErr = false;
    private boolean sign_UPErr = false;
    
    Style st = new Style();
    
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

		tfId.addKeyListener(new keyHandler());
		tfId.addFocusListener(new focusHandler());

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
		
		// 로그인 div //
		LoginForm = new JPanel();
		LoginForm.setBackground(Color.white);
		LoginForm.setSize(300,400);
		LoginForm.setLocation(43, 75);
		LoginForm.setLayout(null);
		display.add(LoginForm);

		// 로고 //
		logo = new JLabel("Shared Diary");
		logo.setBounds(85, 0,150,50);
		logo.setFont(st.godo_B.deriveFont((float)20));
		logo.setForeground(st.mainColor);
		LoginForm.add(logo);
		
		// 아이디 입력 //
		tfIdForm = new JPanel();
		tfIdForm.setBounds(0, 100,300,45);
		tfIdForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfIdForm.setLayout(null);
		LoginForm.add(tfIdForm);
		
		tfId = new JTextField("아이디");
		tfId.setBounds(1, 1,298,43);
		tfId.setBackground(st.inputWhite);
		tfId.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfId.setForeground(st.inputBlack);
		tfId.setFont(st.noto_P);
		tfIdForm.add(tfId);
		
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
		tfPwDefault.setForeground(st.inputBlack);
		tfPwDefault.setFont(st.noto_P);
		tfPwForm.add(tfPwDefault);
		
		tfPw = new JPasswordField("");
		tfPw.setBounds(1, 1,298,43);
		tfPw.setBackground(st.inputWhite);
		tfPw.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfPw.setForeground(st.inputBlack);
		tfPwForm.add(tfPw);
		
		// 로그인 경고 //
		loginMessage = new JLabel();
		loginMessage.setBounds(0, 215,300,20);
		loginMessage.setForeground(Color.red);
		loginMessage.setFont(st.noto_P.deriveFont((float)12));
		LoginForm.add(loginMessage);
		
		// 로그인 버튼 //
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(0, 250,300,45);
		btnLogin.setBackground(st.mainColor);
		btnLogin.setBorder(new LineBorder(st.mainColor,1,true));
		btnLogin.setForeground(Color.white);
		btnLogin.setFont(st.godo_M);
		LoginForm.add(btnLogin);
		
		// 회원가입 버튼 //
		btnSign_Up = new JButton("회원가입");
		btnSign_Up.setBounds(0, 306,300,45);
		btnSign_Up.setBackground(Color.white);
		btnSign_Up.setBorder(new LineBorder(st.mainColor,1,true));
		btnSign_Up.setForeground(st.mainColor);
		btnSign_Up.setFont(st.godo_M);
		LoginForm.add(btnSign_Up);
		
	}
	
	// 키보드 이벤트 //
	class keyHandler extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
		
			Object obj = e.getSource();
			
			// Input 박스 //
			if(obj == tfId) {
				tfId.requestFocus();
				if(tfId.getText().equals("아이디")) {
					tfId.setText("");
					tfIdForm.setBorder(new LineBorder(st.mainColor,1,true));
					loginMessageClear();
					loginErr = false;
				}
			}
			if(obj == tfPw) {
				tfPwForm.setBorder(new LineBorder(st.mainColor,1,true));
				loginMessageClear();
				sign_UPErr = false;
			}
			
		}
		
		public void keyReleased(KeyEvent e){
			
			Object obj = e.getSource();
			
			// Input 박스 //
			if(obj == tfId) {
				if(tfId.getText().equals("")){
					tfId.setText("아이디");
					tfId.select(0, 0);
				}
			}
			

		}
		
		private void loginMessageClear() {
			loginMessage.setText("");
		}
		
	}
	
	// 포커스 이벤트 //
	class focusHandler extends FocusAdapter{
		
		
		public void focusGained(FocusEvent e) {

			Object obj = e.getSource();
					
			// Input 박스 //
			if(obj == tfId) {
				if(tfId.getText().equals("아이디")){
					tfId.select(0, 0);
				}
				if(loginErr == false) {
					tfIdForm.setBorder(new LineBorder(st.mainColor,1,true));
				}
			}else if(obj == tfPw && sign_UPErr == false) {
				tfPwForm.setBorder(new LineBorder(st.mainColor,1,true));
			}
			if(obj == tfPwDefault) {
				tfPw.requestFocus();
				tfPwDefault.setVisible(false);
			}
		
		}
		
		public void focusLost(FocusEvent e) {
		
			Object obj = e.getSource();
			
			// Input 박스 //
			if(obj == tfId && loginErr == false) {
				tfIdForm.setBorder(new LineBorder(st.lightGray,1,true));
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
				btnSign_Up.setBorder(new LineBorder(st.mainColor_shades,1,true));
				btnSign_Up.setForeground(st.mainColor_shades);
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
	
	class ActionHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			
			// Login 버튼 //
			if(obj == btnLogin) {
				
				checkLogin();
				setVisible(false);
				MainFrame suf = new MainFrame("Shared Diary"); 
				
			}
			
			// Sign up 버튼 //
			if(obj == btnSign_Up) {
				
				setVisible(false);
				Sign_UpFrame suf = new Sign_UpFrame("Shared Diary : Sign Up"); 
				
			}
			
		}

	}
	
	// 아이디와 비밀번호 입력 체크 //
	private void checkLogin() {
		
		String pwValue = new String(tfPw.getPassword());
		if(tfId.getText().equals("아이디")) {
			loginMessage.setText("아이디 입력해주세요.");
			tfIdForm.setBorder(new LineBorder(Color.red,1,true));
			loginErr = true;
		}
		if(loginErr && pwValue.equals("")) {
			loginMessage.setText("아이디와 비밀번호를 입력해주세요.");
			tfPwForm.setBorder(new LineBorder(Color.red,1,true));
			sign_UPErr = true;
			
		}else if(pwValue.equals("")){
			loginMessage.setText("비밀번호를 입력해주세요.");
			tfPwForm.setBorder(new LineBorder(Color.red,1,true));
			sign_UPErr = true;
		}
		
		if(!loginErr && !sign_UPErr) {
			
			
			
		}
		
		
	}
	
}
