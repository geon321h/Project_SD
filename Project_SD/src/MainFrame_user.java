import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class MainFrame_user extends JFrame {

	// content area //
	private JPanel contentForm = null;
	private JPanel userSettingForm = null;
    // icon //
    private JLabel Icon = null;
//    private Image img =null;
//    private Image updateImg = null;
//    private ImageIcon updateIcon = null;
	// user setting //
	private JLabel userEmailSetting;
	private JTextField userNameSetting;
	private JLabel userBirthSetting;
	private JPasswordField userPwSetting;
	private JPasswordField userNewPwSetting;
	private JPasswordField userPwCkSetting;
	private JLabel pwLabel;
	private JLabel newPwLabel;
	private JLabel pwCkLabel;
	private JLabel PwInput;
	private JLabel newPwInput;
	private JLabel PwCkInput;
	private JPanel userNameLine;
	private JPanel userPwLine;
	private JLabel nameErr;
	private JLabel PwErr;
	private JLabel PwCkErr;
	// user setting button //
	private JButton btnUpdateName;
	private JButton btnUpdatePw;
	private JButton btnDeleteUser;
	// JOptionPane //

	
	// 참조 클래스 //
    Style st = new Style();
    UserSD_DAO userDao = new UserSD_DAO();
    UserSD_DTO userInfo = new UserSD_DTO();
    MainFrame mainFrame = null;
    JPanel display = null;
	
	public MainFrame_user(MainFrame mainFrame, UserSD_DTO userInfo) {

		this.display = mainFrame.display;
		this.mainFrame = mainFrame;
		this.userInfo = userInfo;
		compose(display,userInfo);
		setEvent();
		st.composeJOptionPane();
		
		// 껏다 켜야 제데로 출력됨
		contentForm.setVisible(false);
		contentForm.setVisible(true);
		
	}
	
	public void MainFrame_user_exit() {

		contentForm.setVisible(false);
		
	}


	private void setEvent() {

		userNameSetting.addKeyListener(new keyHandler());
		userPwSetting.addKeyListener(new keyHandler());

		btnUpdateName.addActionListener(new ActionHandler());
		btnUpdateName.addMouseListener(new MouseHandler());

		btnUpdatePw.addActionListener(new ActionHandler());
		btnUpdatePw.addMouseListener(new MouseHandler());
		
		btnDeleteUser.addActionListener(new ActionHandler());
		btnDeleteUser.addMouseListener(new MouseHandler());
		
	}

	private void compose(JPanel display, UserSD_DTO userInfo) {

		/*** container and header ***/
		contentForm = new JPanel();
		contentForm.setBackground(st.backgroundGray);
		contentForm.setSize(980,800);
		contentForm.setLayout(null);
		contentForm.setLocation(220, 0);
		display.add(contentForm);
		
		userSettingForm = new JPanel();
		userSettingForm.setBackground(st.inputWhite);
		userSettingForm.setBounds(60,50,850,660);
		userSettingForm.setLayout(null);
		userSettingForm.setBorder(new LineBorder(st.lightGray,1,true));
		contentForm.add(userSettingForm);
		
		Icon = new JLabel(" ", JLabel.CENTER); 
		ImageIcon user_icon = new ImageIcon("Project_SD/image/icon/userBig_icon.png");
        Icon.setIcon(user_icon);
        Icon.setBounds(60, 80,144,144);
        userSettingForm.add(Icon);
        
        JPanel headerForm = new JPanel();
        headerForm.setBackground(st.headerColor);
        headerForm.setBounds(0,0,850,140);
        headerForm.setLayout(null);
        userSettingForm.add(headerForm);
        
        JLabel headerTitle = new JLabel("계정 설정");
        headerTitle.setFont(st.neo_EB.deriveFont((float)24));
        headerTitle.setForeground(st.backgroundGray);
        headerTitle.setBounds(720, 85,120,30);
        headerForm.add(headerTitle);
        
        /*** content ***/
        JLabel infoLabel = null;
        // 이메일 //
        infoLabel = new JLabel("이메일");
        infoLabel.setFont(st.neo_B.deriveFont((float)14));
        infoLabel.setForeground(st.inputBlack);
        infoLabel.setBounds(260, 175,50,26);
        userSettingForm.add(infoLabel);

        userEmailSetting = new JLabel(userInfo.getEmail());
        userEmailSetting.setBounds(340, 175,250,26);
        userEmailSetting.setFont(st.neo_R.deriveFont((float)14));
        userEmailSetting.setForeground(st.inputBlack);
        userEmailSetting.setBorder(new LineBorder(st.inputWhite,1,true));
        userSettingForm.add(userEmailSetting);
		
        JPanel userEmailLine = new JPanel(); 
        userEmailLine.setBackground(st.inputGray);
        userEmailLine.setBounds(260, 210,520,1);
        userSettingForm.add(userEmailLine);
        
        // 닉네임 //
        infoLabel = new JLabel("닉네임");
        infoLabel.setFont(st.neo_B.deriveFont((float)14));
        infoLabel.setForeground(st.inputBlack);
        infoLabel.setBounds(260, 255,50,26);
        userSettingForm.add(infoLabel);
        
        userNameSetting = new JTextField(userInfo.getName());
        userNameSetting.setBounds(340, 255,160,26);
        userNameSetting.setFont(st.neo_R.deriveFont((float)14));
        userNameSetting.setForeground(st.inputBlack);
        userNameSetting.setBackground(st.inputWhite);
        userNameSetting.setBorder(new LineBorder(st.inputWhite,1,true));
        userSettingForm.add(userNameSetting);
        userNameSetting.setEditable(false);
		
        btnUpdateName = new JButton("변경");
        btnUpdateName.setBounds(720, 255,35,26);
        btnUpdateName.setBackground(Color.white);
        btnUpdateName.setBorder(new LineBorder(Color.white,1,true));
        btnUpdateName.setFont(st.neo_B.deriveFont((float)14));
        btnUpdateName.setForeground(st.inputBlack);
        btnUpdateName.setContentAreaFilled(false);
        btnUpdateName.setFocusPainted(false);
        userSettingForm.add(btnUpdateName);
        
        userNameLine = new JPanel(); 
        userNameLine.setBackground(st.inputGray);
        userNameLine.setBounds(260, 290,520,1);
        userSettingForm.add(userNameLine);
        
        nameErr = new JLabel("닉네임은 6자이하 한글로만 입력해주세요.");
        nameErr.setFont(st.neo_R.deriveFont((float)12));
        nameErr.setForeground(Color.red);
        nameErr.setBounds(340, 295,220,26);
        nameErr.setVisible(false);
		userSettingForm.add(nameErr);
        
        // 생일 //
        infoLabel = new JLabel("생일");
        infoLabel.setFont(st.neo_B.deriveFont((float)14));
        infoLabel.setForeground(st.inputBlack);
        infoLabel.setBounds(260, 335,50,26);
        userSettingForm.add(infoLabel);
        
        userBirthSetting = new JLabel(userInfo.getBirth());
        userBirthSetting.setBounds(340, 335,160,26);
        userBirthSetting.setFont(st.neo_R.deriveFont((float)14));
        userBirthSetting.setForeground(st.inputBlack);
        userBirthSetting.setBorder(new LineBorder(st.inputWhite,1,true));
        userSettingForm.add(userBirthSetting);
		
        JPanel userBirthLine = new JPanel(); 
        userBirthLine.setBackground(st.inputGray);
        userBirthLine.setBounds(260, 370,520,1);
        userSettingForm.add(userBirthLine);
        
        // 비밀번호 //
        pwLabel = new JLabel("비밀번호");
        pwLabel.setFont(st.neo_B.deriveFont((float)14));
        pwLabel.setForeground(st.inputBlack);
        pwLabel.setBounds(260, 415,80,26);
        userSettingForm.add(pwLabel);
        
        userPwSetting = new JPasswordField(userInfo.getPw());
        userPwSetting.setBounds(340, 415,200,26);
        userPwSetting.setFont(st.neo_R.deriveFont((float)14));
        userPwSetting.setForeground(st.inputBlack);
        userPwSetting.setBackground(st.inputWhite);
        userPwSetting.setBorder(new LineBorder(st.inputWhite,1,true));
        userSettingForm.add(userPwSetting);
        userPwSetting.setEditable(false);
        
        btnUpdatePw = new JButton("비밀번호 변경");
        btnUpdatePw.setBounds(668, 415,85,26);
        btnUpdatePw.setBackground(Color.white);
        btnUpdatePw.setBorder(new LineBorder(Color.white,1,true));
        btnUpdatePw.setFont(st.neo_B.deriveFont((float)14));
        btnUpdatePw.setForeground(st.inputBlack);
        btnUpdatePw.setContentAreaFilled(false);
        btnUpdatePw.setFocusPainted(false);
        userSettingForm.add(btnUpdatePw);
		
        userPwLine = new JPanel(); 
        userPwLine.setBackground(st.inputGray);
        userPwLine.setBounds(260, 450,520,1);
        userSettingForm.add(userPwLine);
        // 계정 삭제 //
        btnDeleteUser = new JButton("계정 탈퇴");
        btnDeleteUser.setBounds(700,610,85,30);
        btnDeleteUser.setBackground(st.inputWhite);
        btnDeleteUser.setBorder(new LineBorder(st.mainColor,1,true));
        btnDeleteUser.setFont(st.neo_B.deriveFont((float)14));
        btnDeleteUser.setForeground(st.mainColor);
        btnDeleteUser.setContentAreaFilled(false);
        btnDeleteUser.setFocusPainted(false);
        userSettingForm.add(btnDeleteUser);
        
	}
	
	// 키보드 이벤트 //
		class keyHandler extends KeyAdapter{

			public void keyTyped(KeyEvent e) {
				
				Object obj = e.getSource();
				
				String nameValue = userNameSetting.getText();
				String pwValue = new String(userPwSetting.getPassword());
				if(obj == userNameSetting) {
					if(nameValue.length()>=6) {
						e.consume();
					}
				}else if(obj == userPwSetting) {
					if(pwValue.length()>=20) {
						e.consume();
					}
				}else if(obj == userPwSetting) {
					if(pwValue.length()>=20) {
						e.consume();
					}
				}else if(obj == userPwSetting) {
					if(pwValue.length()>=20) {
						e.consume();
					}
				}
				
		}
	}
		
	// 마우스 이벤트 //
	class MouseHandler extends MouseAdapter{
		
		public void mouseEntered(MouseEvent e) {

			Object obj = e.getSource();
			
			if(obj == btnUpdateName) {
				btnUpdateName.setForeground(st.mainColor);
			}else if(obj  == btnUpdatePw) {
				btnUpdatePw.setForeground(st.mainColor);
			}else if(obj  == btnDeleteUser) {
				btnDeleteUser.setForeground(st.mainColor_thin);
				btnDeleteUser.setBorder(new LineBorder(st.mainColor_thin,1,true));
			}
			
		}
		
		public void mouseExited(MouseEvent e) {

			Object obj = e.getSource();
			
			if(obj == btnUpdateName) {
				btnUpdateName.setForeground(st.inputBlack);
			}else if(obj  == btnUpdatePw) {
				btnUpdatePw.setForeground(st.inputBlack);
			}else if(obj  == btnDeleteUser) {
				btnDeleteUser.setForeground(st.mainColor);
				btnDeleteUser.setBorder(new LineBorder(st.mainColor,1,true));
			}
			
		}
		
		public void mouseClicked(MouseEvent e) {
			
			Object obj = e.getSource();
			
			if(obj == PwInput) {
				userPwSetting.setVisible(true);
				userPwSetting.requestFocus();
				userPwSetting.setForeground(st.mainColor);
			}else if(obj == newPwInput) {
				userNewPwSetting.setVisible(true);
				userNewPwSetting.requestFocus();
				userNewPwSetting.setForeground(st.mainColor);
			}else if(obj == PwCkInput) {
				userPwCkSetting.setVisible(true);
				userPwCkSetting.requestFocus();
				userPwCkSetting.setForeground(st.mainColor);
			}
			
		}
		
	}

	// 액션 이벤트 //
	class ActionHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			
			// 닉네임 수정 //
			if(obj == btnUpdateName) {
				
				if(btnUpdateName.getText().equals("변경")) {
					btnUpdateName.setText("변경하기");
					btnUpdateName.setBounds(695, 255,60,26);
					userNameSetting.setEditable(true);
					userNameSetting.setForeground(st.mainColor);
					userNameSetting.requestFocus();
					
				}else if(btnUpdateName.getText().equals("변경하기")) {
					
					String korCk = "^[가-힣]*$";
					String name = userNameSetting.getText();
					if(name.matches(korCk) && name.length()<=6) { 
						nameErr.setVisible(false);
						String column = "name";
						userDao.updateUser(userInfo.getNo(),name,column);
						
						btnUpdateName.setText("변경");
						userNameLine.setBackground(st.inputGray);
						btnUpdateName.setBounds(720, 255,35,26);
						userNameSetting.setForeground(st.inputBlack);
						userNameSetting.setEditable(false);
						mainFrame.replaceName(name);
						
						JLabel paneMessage_update = new JLabel("변경 되었습니다.");
						paneMessage_update.setFont(st.neo_R.deriveFont((float)12));
						paneMessage_update.setForeground(st.inputBlack);
						JOptionPane.showMessageDialog(null, paneMessage_update,"변경 완료",JOptionPane.PLAIN_MESSAGE);
						
					}else {
						nameErr.setVisible(true);
						userNameLine.setBackground(Color.red);
					}
					
				}
			} 
			
			// 비밀번호 수정 //
			if(obj  == btnUpdatePw) {
				
				if(btnUpdatePw.getText().equals("비밀번호 변경")) {
					pwChange();
				}else if(btnUpdatePw.getText().equals("변경하기")){
					pwChangeCheck();
				}
				
			}
			
			
			// 계정 탈퇴 //
			if(obj  == btnDeleteUser) {

				JLabel paneMessage = new JLabel("탈퇴 하시겠습니까?");
				paneMessage.setFont(st.neo_R.deriveFont((float)12));
				paneMessage.setForeground(st.inputBlack);
				int yesOrNo = JOptionPane.showConfirmDialog(null, paneMessage,"탈퇴 메세지",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);

				if(yesOrNo == 0) {
					
					int cnt =userDao.deleteUser(userInfo.getNo());
					if(cnt != -1) {
						System.out.println("삭제완료");
						JLabel paneMessage_delete = new JLabel("탈퇴 되었습니다.");
						paneMessage_delete.setFont(st.neo_R.deriveFont((float)12));
						paneMessage_delete.setForeground(st.inputBlack);
						JOptionPane.showMessageDialog(null, paneMessage_delete,"계정 탈퇴 완료",JOptionPane.PLAIN_MESSAGE);
						mainFrame.setVisible(false);
						LoginFrame lf = new LoginFrame("Shared Diary : Login");
					}else {
						System.out.println("삭제실패");
					}
					
				}
				
			}
			
		}

		private void pwChangeCheck() {

			String pwValue = new String(userPwSetting.getPassword());
			String newPwValue = new String(userNewPwSetting.getPassword());
			String pwCkValue = new String(userPwCkSetting.getPassword());
			
			int cnt = userDao.checkUser(userInfo.getEmail(),pwValue);
			
			if(cnt != -1) {
				PwErr.setVisible(false);
				if(newPwValue.equals(pwCkValue) && newPwValue.length()>=8) {
					PwCkErr.setVisible(false);
					PwCkErr.setForeground(st.inputGray);
					PwCkErr.setText("비밀번호는 8자이상 입력해주세요.");
					userDao.updateUser(userInfo.getNo(),newPwValue,"pw");
					pwChangeReset(newPwValue);
					
					JLabel paneMessage_update = new JLabel("변경 되었습니다.");
					paneMessage_update.setFont(st.neo_R.deriveFont((float)12));
					paneMessage_update.setForeground(st.inputBlack);
					JOptionPane.showMessageDialog(null, paneMessage_update,"변경 완료",JOptionPane.PLAIN_MESSAGE);
				}else if(newPwValue.length()<8){
					PwCkErr.setForeground(Color.red);
					PwCkErr.setText("비밀번호는 8자이상 입력해주세요.");
				}else if(!newPwValue.equals(pwCkValue)){
					PwCkErr.setForeground(Color.red);
					PwCkErr.setText("비밀번호가 확인과 일치하지 않습니다.");
				}
			}else{
				PwErr.setVisible(true);
			}
			
		}

		private void pwChangeReset(String newPwValue) {

			pwLabel.setText("비밀번호");
			pwLabel.setSize(80,26);
			userPwSetting.setBounds(340, 415,200,26);
			userPwSetting.setText(newPwValue);
			
			btnUpdatePw.setText("비밀번호 변경");
			btnUpdatePw.setBounds(668, 415,85,26);
			
			PwInput.setVisible(false);
			newPwInput.setVisible(false);
			PwCkInput.setVisible(false);
			newPwLabel.setVisible(false);
			pwCkLabel.setVisible(false);
			userNewPwSetting.setVisible(false);
			userPwCkSetting.setVisible(false);
			userPwSetting.setEditable(false);
			userPwSetting.setVisible(true);
			userPwSetting.setForeground(st.inputBlack);
			
	        userPwLine.setBounds(260, 450,520,1);
			
		}

		private void pwChange() {
			btnUpdatePw.setText("변경하기");
			btnUpdatePw.setBounds(695, 555,60,26);

			// 현재 비밀번호 //
			
	        PwInput = new JLabel("현재 비밀번호를 입력해주세요.");
	        PwInput.setFont(st.neo_B.deriveFont((float)14));
	        PwInput.setForeground(st.inputGray);
	        PwInput.setBounds(380, 415,200,26);
			userSettingForm.add(PwInput);
			userPwSetting.setEditable(true);
			userPwSetting.setVisible(false);
			
			pwLabel.setText("현재 비밀번호");
			pwLabel.setSize(120,26);
			userPwSetting.setBounds(380, 415,200,26);
			userPwSetting.setText("");
			
			PwErr = new JLabel("현재 비밀번호와 일치하지 않습니다.");
			PwErr.setFont(st.neo_R.deriveFont((float)12));
			PwErr.setForeground(Color.red);
			PwErr.setBounds(380, 435,200,26);
			PwErr.setVisible(false);
			userSettingForm.add(PwErr);
			
			// 새 비밀번호 //
	        newPwInput = new JLabel("새 비밀번호를 입력해주세요.");
	        newPwInput.setFont(st.neo_B.deriveFont((float)14));
	        newPwInput.setForeground(st.inputGray);
	        newPwInput.setBounds(380, 485,200,26);
			userSettingForm.add(newPwInput);
	        
			newPwLabel = new JLabel("새 비밀번호");
			newPwLabel.setFont(st.neo_B.deriveFont((float)14));
			newPwLabel.setForeground(st.inputBlack);
			newPwLabel.setBounds(260, 485,120,26);
			userSettingForm.add(newPwLabel);
			
			userNewPwSetting = new JPasswordField();
			userNewPwSetting.setBounds(380, 485,200,26);
			userNewPwSetting.setFont(st.neo_R.deriveFont((float)14));
			userNewPwSetting.setForeground(st.inputBlack);
			userNewPwSetting.setBackground(st.inputWhite);
			userNewPwSetting.setBorder(new LineBorder(st.inputWhite,1,true));
			userNewPwSetting.setVisible(false);
			userSettingForm.add(userNewPwSetting);
			
			PwCkErr = new JLabel("비밀번호는 8자이상 입력해주세요.");
			PwCkErr.setFont(st.neo_R.deriveFont((float)12));
			PwCkErr.setForeground(st.inputGray);
			PwCkErr.setBounds(380, 505,200,26);
			userSettingForm.add(PwCkErr);

			// 비밀번호 확인 //
	        PwCkInput = new JLabel("비밀번호를 다시 입력해주세요.");
	        PwCkInput.setFont(st.neo_B.deriveFont((float)14));
	        PwCkInput.setForeground(st.inputGray);
	        PwCkInput.setBounds(380, 555,200,26);
			userSettingForm.add(PwCkInput);
			
			pwCkLabel = new JLabel("비밀번호 확인");
			pwCkLabel.setFont(st.neo_B.deriveFont((float)14));
			pwCkLabel.setForeground(st.inputBlack);
			pwCkLabel.setBounds(260, 555,120,26);
			userSettingForm.add(pwCkLabel);
			
			userPwCkSetting = new JPasswordField();
			userPwCkSetting.setBounds(380, 555,200,26);
			userPwCkSetting.setFont(st.neo_R.deriveFont((float)14));
			userPwCkSetting.setForeground(st.inputBlack);
			userPwCkSetting.setBackground(st.inputWhite);
			userPwCkSetting.setBorder(new LineBorder(st.inputWhite,1,true));
			userPwCkSetting.setVisible(false);
			userSettingForm.add(userPwCkSetting);
			
			userPwLine.setBounds(260, 590,520,1);
			
			setEventFocus();
						
		}

		private void setEventFocus() {

			PwInput.addMouseListener(new MouseHandler());
			newPwInput.addMouseListener(new MouseHandler());
			PwCkInput.addMouseListener(new MouseHandler());
			
			userPwSetting.addFocusListener(new focusHandler());
			userNewPwSetting.addFocusListener(new focusHandler());
			userPwCkSetting.addFocusListener(new focusHandler());
			
		}

	} // 액션 이벤트
	
	// 포커스 이벤트 //
	class focusHandler extends FocusAdapter{
		
		
		public void focusGained(FocusEvent e) {

			Object obj = e.getSource();
			
			if(obj == userPwSetting) {
				PwInput.setVisible(false);
			}else if(obj == userNewPwSetting) {
				newPwInput.setVisible(false);
			}else if(obj == userPwCkSetting) {
				PwCkInput.setVisible(false);
			}
			
		}
		
		public void focusLost(FocusEvent e) {
		
			Object obj = e.getSource();
			
			String pwValue = new String(userPwSetting.getPassword());
			String newPwValue = new String(userNewPwSetting.getPassword());
			String pwCkValue = new String(userPwCkSetting.getPassword());
			if(obj == userPwSetting) {
				if(pwValue.equals("")){
					userPwSetting.setVisible(false);
					PwInput.setVisible(true);
				}
			}else if(obj == userNewPwSetting) {
				if(newPwValue.equals("")){
					userNewPwSetting.setVisible(false);
					newPwInput.setVisible(true);
				}
			}else if(obj == userPwCkSetting) {
				if(pwCkValue.equals("")){
					userPwCkSetting.setVisible(false);
					PwCkInput.setVisible(true);
				}
			}
			
		}
		
	}
	
}
