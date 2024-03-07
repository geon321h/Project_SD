import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Sign_UpFrame extends JFrame{

	// main container //
	private JPanel Sign_UpForm;
	// title //
	private JLabel Sign_Up;
	// name //
	private JLabel name;
	private JPanel tfNameForm;
	private JTextField tfName;
	private JLabel nameMessage;
	private boolean nameErr;
	// email //
	private JLabel email;
	private JPanel tfEmailForm;
	private JTextField tfEmail;
	private JLabel emailMessage;
	private boolean emailErr;
	// pw //
	private JLabel pw;
	private JPanel tfPwForm;
	private JTextField tfPwDefault;
	private JPasswordField tfPw;
	
	private JPanel tfPwCkForm;
	private JTextField tfPwCkDefault;
	private JPasswordField tfPwCk;
	private JLabel pwMessage;
	private boolean pwErr;
	// birth //
	private JLabel birth;
	private JPanel yearForm;
	private JTextField tfYear;
	private JLabel year;
	private JPanel monthForm;
	private JTextField tfMonth;
	private JLabel month;
	private JPanel dayForm;
	private JTextField tfDay;
	private JLabel day;
	// button  //
	private JButton btnSign_Up;
	private JLabel btnInfo;
	private JLabel rogin;
	HashMap<Object, Boolean> tfErr = new HashMap<>();
	
	// 객체 //
    Style st = new Style();
    UserSD_DAO userDao = new UserSD_DAO();
    
	public Sign_UpFrame(String title) {

		super(title);
		
		compose(); // 화면 구성
		setEvent();
		
		tfErr.put(tfEmail, false);
		tfErr.put(tfPw, false);
		tfErr.put(tfPwCk, false);
		tfErr.put(tfName, false);
		
		setSize(400,700);
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
		
		tfPwCk.addFocusListener(new focusHandler());
		tfPwCk.addKeyListener(new keyHandler());
		tfPwCkDefault.addFocusListener(new focusHandler());
	
		tfName.addFocusListener(new focusHandler());
		tfName.addKeyListener(new keyHandler());
		
		tfYear.addFocusListener(new focusHandler());
		tfMonth.addFocusListener(new focusHandler());
		tfDay.addFocusListener(new focusHandler());
		
		//btnSign_Up.addActionListener(new ActionHandler());
		btnSign_Up.addMouseListener(new MouseHandler());

		//rogin.addActionListener(new ActionHandler());
		rogin.addMouseListener(new MouseHandler());
		
	}

	private void compose() { // 화면 구성
		
		JPanel display = new JPanel();
		display.setBackground(Color.white);
		display.setLayout(null);
		this.add(display);
		
		// 회원가입 div //
		Sign_UpForm = new JPanel();
		Sign_UpForm.setBackground(Color.white);
		Sign_UpForm.setSize(300,600);
		Sign_UpForm.setLocation(43, 30);
		Sign_UpForm.setLayout(null);
		display.add(Sign_UpForm);
		
		// 타이틀 //
		Sign_Up = new JLabel("회원 가입");
		Sign_Up.setBounds(95, 0,120,50);
		Sign_Up.setFont(st.neo_EB.deriveFont((float)26));
		Sign_Up.setForeground(st.inputBlack);
		Sign_UpForm.add(Sign_Up);
		
		// 아이디 입력 //
		email = new JLabel("이메일");
		email.setBounds(0, 75,50,20);
		email.setFont(st.neo_B);
		email.setForeground(st.inputBlack);
		Sign_UpForm.add(email);
		
		tfEmailForm = new JPanel();
		tfEmailForm.setBounds(0, 100,300,45);
		tfEmailForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfEmailForm.setLayout(null);
		Sign_UpForm.add(tfEmailForm);
		
		tfEmail = new JTextField("이메일");
		tfEmail.setBounds(1, 1,298,43);
		tfEmail.setBackground(st.inputWhite);
		tfEmail.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfEmail.setForeground(st.inputGray);
		tfEmail.setFont(st.neo_R);
		tfEmailForm.add(tfEmail);
		
		emailMessage = new JLabel("");
		emailMessage.setBounds(0, 150,200,20);
		emailMessage.setFont(st.neo_R.deriveFont((float)12));
		emailMessage.setForeground(Color.red);
		Sign_UpForm.add(emailMessage);
		
		// 비밀번호 입력 //
		pw = new JLabel("비밀번호");
		pw.setBounds(0, 175,80,20);
		pw.setFont(st.neo_B.deriveFont((float)14));
		pw.setForeground(st.inputBlack);
		Sign_UpForm.add(pw);
		
		tfPwForm = new JPanel();
		tfPwForm.setBounds(0, 200,300,45);
		tfPwForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfPwForm.setLayout(null);
		Sign_UpForm.add(tfPwForm);
		
		tfPwDefault = new JTextField("비밀번호는 8~20자로 입력해주세요");
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
		
		tfPwCkForm = new JPanel();
		tfPwCkForm.setBounds(0, 255,300,45);
		tfPwCkForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfPwCkForm.setLayout(null);
		Sign_UpForm.add(tfPwCkForm);
		
		tfPwCkDefault = new JTextField("비밀번호 확인");
		tfPwCkDefault.setBounds(1, 1,298,43);
		tfPwCkDefault.setBackground(st.inputWhite);
		tfPwCkDefault.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfPwCkDefault.setForeground(st.inputGray);
		tfPwCkDefault.setFont(st.neo_R);
		tfPwCkForm.add(tfPwCkDefault);
		
		tfPwCk = new JPasswordField("");
		tfPwCk.setBounds(1, 1,298,43);
		tfPwCk.setBackground(st.inputWhite);
		tfPwCk.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfPwCk.setForeground(st.inputBlack);
		tfPwCk.setVisible(false);
		tfPwCkForm.add(tfPwCk);
		
		pwMessage = new JLabel("");
		pwMessage.setBounds(0, 305,200,20);
		pwMessage.setFont(st.neo_R.deriveFont((float)12));
		pwMessage.setForeground(Color.red);
		Sign_UpForm.add(pwMessage);
		
		// 닉네임 입력 //
		name = new JLabel("닉네임");
		name.setBounds(0, 330,50,20);
		name.setFont(st.neo_B);
		name.setForeground(st.inputBlack);
		Sign_UpForm.add(name);
		
		tfNameForm = new JPanel();
		tfNameForm.setBounds(0, 355,300,45);
		tfNameForm.setBorder(new LineBorder(st.lightGray,1,true));
		tfNameForm.setLayout(null);
		Sign_UpForm.add(tfNameForm);
		
		tfName = new JTextField("닉네임은 6자이내 한글로 입력해주세요");
		tfName.setBounds(1, 1,298,43);
		tfName.setBackground(st.inputWhite);
		tfName.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfName.setForeground(st.inputGray);
		tfName.setFont(st.neo_R);
		tfNameForm.add(tfName);
		
		nameMessage = new JLabel("한글로만 입력해주세요");
		nameMessage.setBounds(0, 405,200,20);
		nameMessage.setFont(st.neo_R.deriveFont((float)12));
		nameMessage.setForeground(Color.red);
		Sign_UpForm.add(nameMessage);
		
		// 생일 입력 //
		birth = new JLabel("생일");
		birth.setBounds(0, 430,50,20);
		birth.setFont(st.neo_B);
		birth.setForeground(st.inputBlack);
		Sign_UpForm.add(birth);
		
		yearForm = new JPanel();
		yearForm.setBounds(0, 455,60,45);
		yearForm.setBorder(new LineBorder(st.lightGray,1,true));
		yearForm.setLayout(null);
		Sign_UpForm.add(yearForm);
		
		tfYear = new JTextField("YYYY");
		tfYear.setBounds(1, 1,58,43);
		tfYear.setBackground(st.inputWhite);
		tfYear.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfYear.setForeground(st.inputGray);
		tfYear.setFont(st.neo_R);
		tfYear.setHorizontalAlignment(JTextField.RIGHT);
		yearForm.add(tfYear);
		
		year = new JLabel("년");
		year.setBounds(65, 470,20,20);
		year.setFont(st.neo_B);
		year.setForeground(st.inputBlack);
		Sign_UpForm.add(year);
		
		monthForm = new JPanel();
		monthForm.setBounds(100, 455,50,45);
		monthForm.setBorder(new LineBorder(st.lightGray,1,true));
		monthForm.setLayout(null);
		Sign_UpForm.add(monthForm);
		
		tfMonth = new JTextField("MM");
		tfMonth.setBounds(1, 1,48,43);
		tfMonth.setBackground(st.inputWhite);
		tfMonth.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfMonth.setForeground(st.inputGray);
		tfMonth.setFont(st.neo_R);
		tfMonth.setHorizontalAlignment(JTextField.RIGHT);
		monthForm.add(tfMonth);
		
		month = new JLabel("월");
		month.setBounds(155, 470,20,20);
		month.setFont(st.neo_B);
		month.setForeground(st.inputBlack);
		Sign_UpForm.add(month);
		
		dayForm = new JPanel();
		dayForm.setBounds(190, 455,50,45);
		dayForm.setBorder(new LineBorder(st.lightGray,1,true));
		dayForm.setLayout(null);
		Sign_UpForm.add(dayForm);
		
		tfDay = new JTextField("DD");
		tfDay.setBounds(1, 1,48,43);
		tfDay.setBackground(st.inputWhite);
		tfDay.setBorder(BorderFactory.createEmptyBorder(5, 9, 4, 9));
		tfDay.setForeground(st.inputGray);
		tfDay.setFont(st.neo_R);
		tfDay.setHorizontalAlignment(JTextField.RIGHT);
		dayForm.add(tfDay);
		
		day = new JLabel("일");
		day.setBounds(245, 470,20,20);
		day.setFont(st.neo_B);
		day.setForeground(st.inputBlack);
		Sign_UpForm.add(day);
		
		// 버튼 //
		btnSign_Up = new JButton("회원가입");
		btnSign_Up.setBounds(0, 525,300,45);
		btnSign_Up.setBackground(st.mainColor);
		btnSign_Up.setBorder(new LineBorder(st.mainColor,1,true));
		btnSign_Up.setForeground(Color.white);
		btnSign_Up.setFont(st.neo_B);
		Sign_UpForm.add(btnSign_Up);
		
		btnInfo = new JLabel("이미 회원이신가요?");
		btnInfo.setBounds(65, 575,100,20);
		btnInfo.setFont(st.neo_R.deriveFont((float)12));
		btnInfo.setForeground(st.inputBlack);
		Sign_UpForm.add(btnInfo);

		rogin = new JLabel("로그인");
		rogin.setBounds(175, 575,40,20);
		rogin.setFont(st.neo_B.deriveFont((float)13));
		rogin.setForeground(st.inputBlack);
		Sign_UpForm.add(rogin);
		
	}
	
	// 키보드 이벤트 //
	class keyHandler extends KeyAdapter{

		public void keyTyped(KeyEvent e) {
			
			String pwValue = new String(tfPw.getPassword());
			String pwCkValue = new String(tfPwCk.getPassword());
			Object obj = e.getSource();
			if(obj == tfPw) {
				if(pwValue.length()>=19) {
					e.consume();
				}
			}else if(obj == tfPwCk) {
				if(pwCkValue.length()>=19) {
					e.consume();
				}
			}else if(obj == tfName) {
				if(tfName.getText().length()>=5) {
					e.consume();
				}
			}
			
		}
		
		public void keyPressed(KeyEvent e){
		
			Object obj = e.getSource();
			
			String pwValue = new String(tfPw.getPassword());
			String pwCkValue = new String(tfPwCk.getPassword());
			// Input 박스 //
			if(obj == tfEmail) {
				if(!tfEmail.getText().contains("@") || !tfEmail.getText().contains(".")){
					tfEmailForm.setBorder(new LineBorder(Color.red,1,true));
					tfErr.replace(obj, true);
					emailMessage.setText("이메일 형식으로 입력해주세요");
				}
			}else if(obj == tfPw) {
				
			}else if(obj == tfPwCk) {
				if(!pwValue.equals(pwCkValue)) {
					tfPwCkForm.setBorder(new LineBorder(Color.red,1,true));
					tfErr.replace(obj, true);
					pwMessage.setText("비밀번호가 일치하지 않습니다");
				}
			}else if(obj == tfName) {
				tfPwForm.setBorder(new LineBorder(st.mainColor,1,true));
			}
			
		}
		

		public void keyReleased(KeyEvent e){
			
			Object obj = e.getSource();
			String pwValue = new String(tfPw.getPassword());
			String pwCkValue = new String(tfPwCk.getPassword());
			// Input 박스 //
			if(obj == tfEmail) {
				if(tfEmail.getText().contains("@") & tfEmail.getText().contains(".")){
					tfEmailForm.setBorder(new LineBorder(st.mainColor,1,true));
					tfErr.replace(obj, false);
					emailMessage.setText("");
				}
			}else if(obj == tfPwCk) {
				if(pwValue.equals(pwCkValue)) {
					tfPwCkForm.setBorder(new LineBorder(st.mainColor,1,true));
					tfErr.replace(obj, false);
					pwMessage.setText("");
				}else {
					tfPwCkForm.setBorder(new LineBorder(Color.red,1,true));
					tfErr.replace(obj, true);
					pwMessage.setText("비밀번호가 일치하지 않습니다");
				}
			}

		}		
		
	}
	
	// 포커스 이벤트 //
		class focusHandler extends FocusAdapter{
			
			
			public void focusGained(FocusEvent e) {

				Object obj = e.getSource();
				// Input 박스 //
				if(obj == tfEmail && !checkErr(obj)) {
						tfEmailForm.setBorder(new LineBorder(st.mainColor,1,true));
				}else if(obj == tfPw && !checkErr(obj)) {
					tfPwForm.setBorder(new LineBorder(st.mainColor,1,true));
				}else if(obj == tfPwCk && !checkErr(obj)) {
					tfPwCkForm.setBorder(new LineBorder(st.mainColor,1,true));
				}else if(obj == tfName && !checkErr(obj)) {
					tfNameForm.setBorder(new LineBorder(st.mainColor,1,true));
				}
				
				if(obj == tfPwDefault) {
					tfPw.setVisible(true);
					tfPw.requestFocus();
					tfPwDefault.setVisible(false);
				}else if(obj == tfPwCkDefault) {
					tfPwCk.setVisible(true);
					tfPwCk.requestFocus();
					tfPwCkDefault.setVisible(false);
				}else if(obj == tfEmail) {
					if(tfEmail.getText().equals("이메일")) {
						tfEmail.setForeground(st.inputBlack);
						tfEmail.setText("");
					}
				}else if(obj == tfName) {
					if(tfName.getText().equals("닉네임은 6자이내 한글로 입력해주세요")) {
						tfName.setForeground(st.inputBlack);
						tfName.setText("");
					}
				}else if(obj == tfYear) {
					yearForm.setBorder(new LineBorder(st.mainColor,1,true));
					if(tfYear.getText().equals("YYYY")) {
						tfYear.setForeground(st.inputBlack);
						tfYear.setText("");
					}
				}else if(obj == tfMonth) {
					monthForm.setBorder(new LineBorder(st.mainColor,1,true));
					if(tfMonth.getText().equals("MM")) {
						tfMonth.setForeground(st.inputBlack);
						tfMonth.setText("");
					}
				}else if(obj == tfDay) {
					dayForm.setBorder(new LineBorder(st.mainColor,1,true));
					if(tfDay.getText().equals("DD")) {
						tfDay.setForeground(st.inputBlack);
						tfDay.setText("");
					}
				}
				
			
			}
			
			public void focusLost(FocusEvent e) {
			
				Object obj = e.getSource();
				
				// Input 박스 //
				if(obj == tfEmail && !checkErr(obj)) {
					tfEmailForm.setBorder(new LineBorder(st.lightGray,1,true));
				}else if(obj == tfPw && !checkErr(obj)) {
					tfPwForm.setBorder(new LineBorder(st.lightGray,1,true));
				}else if(obj == tfPwCk && !checkErr(obj)) {
					tfPwCkForm.setBorder(new LineBorder(st.lightGray,1,true));
				}else if(obj == tfName && !checkErr(obj)) {
					tfNameForm.setBorder(new LineBorder(st.lightGray,1,true));
				}
				
				if(obj == tfPw) {
					String pwValue = new String(tfPw.getPassword());
					if(pwValue.equals("")){
						tfPwDefault.setVisible(true);
					}
				}else if(obj == tfPwCk) {
					String pwValue = new String(tfPwCk.getPassword());
					if(pwValue.equals("")){
						tfPwCkDefault.setVisible(true);
					}
				}else if(obj == tfEmail) {
					if(tfEmail.getText().equals("")) {
						tfEmail.setForeground(st.inputGray);
						tfEmail.setText("이메일");
					}
				}else if(obj == tfName) {
					if(tfName.getText().equals("")) {
						tfName.setForeground(st.inputGray);
						tfName.setText("닉네임은 6자이내 한글로 입력해주세요");
					}
				}else if(obj == tfYear) {
					yearForm.setBorder(new LineBorder(st.lightGray,1,true));
					if(tfYear.getText().equals("")) {
						tfYear.setForeground(st.inputGray);
						tfYear.setText("YYYY");
					}
				}else if(obj == tfMonth) {
					monthForm.setBorder(new LineBorder(st.lightGray,1,true));
					if(tfMonth.getText().equals("")) {
						tfMonth.setForeground(st.inputGray);
						tfMonth.setText("MM");
					}
				}else if(obj == tfDay) {
					dayForm.setBorder(new LineBorder(st.lightGray,1,true));
					if(tfDay.getText().equals("")) {
						tfDay.setForeground(st.inputGray);
						tfDay.setText("DD");
					}
				}
				
			}
			
		}
		
		// 마우스 이벤트 //
		class MouseHandler extends MouseAdapter{
			
			public void mouseEntered(MouseEvent e) {

				Object obj = e.getSource();
				
				if(obj == btnSign_Up) {
					btnSign_Up.setBackground(st.mainColor_shades);
				}else if(obj  == rogin) {
					rogin.setForeground(st.mainColor);
				}
				
			}
			
			public void mouseExited(MouseEvent e) {

				Object obj = e.getSource();
				
				if(obj == btnSign_Up) {
					btnSign_Up.setBackground(st.mainColor);
				}else if(obj  == rogin) {
					rogin.setForeground(st.inputBlack);
				}
				
			}
			
		}
		
		private boolean checkErr(Object obj) {
			try {
				return tfErr.get(obj);
			} catch (NullPointerException e) {
			
			}
			return false;
		}
		
	
}
