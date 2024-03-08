
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {
	
	// container //
	private JPanel menuForm = null;
	private JPanel contentForm = null;
	// content area //
	private JPanel userSettingForm = null;
	// logo // 
    private JLabel logo;
    // line //
    private JPanel line = null;
    // icon //
    private JLabel Icon = null;
    private Image img =null;
    private Image updateImg = null;
    private ImageIcon updateIcon = null;
	// profile //
    private JLabel userName;
    private JLabel userCode;
	// menuTitle //
	private JLabel menuTitle ;
	// menu //
	private JButton groupMenu ;
	private JButton friendMenu ;
	private JButton calendarMenu ;
	// user setting //
	private JTextField userNameSetting;
	
    
	// 유저 식별 키 //
	UserSD_DTO userInfo = null;
	
	// 참조 클래스 //
    Style st = new Style();
    UserSD_DAO userDao = new UserSD_DAO();
	
	public MainFrame(String title, int no) {

		super(title);
		userInfo = userDao.getUserById(no);
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
		
		JPanel display = new JPanel();
		display.setBackground(Color.white);
		display.setLayout(null);
		this.add(display);
		
		/*** menu area ***/
		menuForm = new JPanel();
		menuForm.setBackground(st.menuBlack);
		menuForm.setSize(250,800);
		menuForm.setLayout(null);
		menuForm.setLocation(0, 0);
		display.add(menuForm);
		
		// 라인 //
		line = new JPanel();
		line.setBackground(st.inputGray);
		line.setSize(210,1);
		
		// 로고 //
		logo = new JLabel("Shared Diary");
		logo.setBounds(65, 50,150,50);
		logo.setFont(st.neo_EB.deriveFont((float)18));
		logo.setForeground(Color.white);
		menuForm.add(logo);
		
		line = new JPanel();
		line.setBackground(st.inputGray);
		line.setBounds(20, 140,210,1);
		menuForm.add(line);
		
		// 프로필 //
		Icon = new JLabel(" ", JLabel.CENTER); 
		ImageIcon user_icon = new ImageIcon("Project_SD/image/icon/user_icon.png");
		img = user_icon.getImage();
    	updateImg = img.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateImg);
        Icon.setIcon(updateIcon);
        Icon.setBounds(35, 160,58,58);
		menuForm.add(Icon);
		
		userName = new JLabel(userInfo.getName());
		userName.setBounds(95, 170,100,20);
		userName.setFont(st.neo_B.deriveFont((float)16));
		userName.setForeground(st.lightGray);
		menuForm.add(userName);
		
		userCode = new JLabel("#"+String.valueOf(userInfo.getNo()));
		userCode.setBounds(95, 190,50,20);
		userCode.setFont(st.neo_R.deriveFont((float)12));
		userCode.setForeground(st.inputGray);
		menuForm.add(userCode);
		
		line = new JPanel();
		line.setBackground(st.inputGray);
		line.setBounds(20, 238,210,1);
		menuForm.add(line);
		
		// main menu //
		menuTitle = new JLabel("메뉴 선택");
		menuTitle.setBounds(30, 258,100,20);
		menuTitle.setFont(st.neo_B.deriveFont((float)14));
		menuTitle.setForeground(st.lightGray);
		menuForm.add(menuTitle);
		
		groupMenu = new JButton("내 그룹");
		groupMenu.setBounds(75, 309,55,20);
		groupMenu.setFont(st.neo_R.deriveFont((float)17));
		groupMenu.setForeground(st.lightGray);
		groupMenu.setBackground(st.menuBlack);
		groupMenu.setBorder(new LineBorder(st.menuBlack,1,true));
		groupMenu.setHorizontalAlignment(SwingConstants.LEFT);
		groupMenu.setContentAreaFilled(false);
		groupMenu.setFocusPainted(false);
		menuForm.add(groupMenu);
		
		Icon = new JLabel(" ", JLabel.CENTER); 
		ImageIcon group_icon = new ImageIcon("Project_SD/image/icon/group_icon.png");
        img = group_icon.getImage();
    	updateImg = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateImg);
        Icon.setIcon(updateIcon);
        Icon.setBounds(30, 302,32,32);
		menuForm.add(Icon);
		
		friendMenu = new JButton("내 친구");
		friendMenu.setBounds(75, 369,55,20);
		friendMenu.setFont(st.neo_R.deriveFont((float)17));
		friendMenu.setForeground(st.lightGray);
		friendMenu.setBackground(st.menuBlack);
		friendMenu.setBorder(new LineBorder(st.menuBlack,1,true));
		friendMenu.setHorizontalAlignment(SwingConstants.LEFT);
		friendMenu.setContentAreaFilled(false);
		friendMenu.setFocusPainted(false);
		menuForm.add(friendMenu);
		
		Icon = new JLabel(" ", JLabel.CENTER); 
		ImageIcon friend_icon = new ImageIcon("Project_SD/image/icon/friend_icon.png");
        img = friend_icon.getImage();
    	updateImg = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateImg);
        Icon.setIcon(updateIcon);
        Icon.setBounds(30, 362,32,32);
		menuForm.add(Icon);
		
		calendarMenu = new JButton("캘린더");
		calendarMenu.setBounds(75, 429,55,20);
		calendarMenu.setFont(st.neo_R.deriveFont((float)17));
		calendarMenu.setForeground(st.lightGray);
		calendarMenu.setBackground(st.menuBlack);
		calendarMenu.setBorder(new LineBorder(st.menuBlack,1,true));
		calendarMenu.setHorizontalAlignment(SwingConstants.LEFT);
		calendarMenu.setContentAreaFilled(false);
		calendarMenu.setFocusPainted(false);
		menuForm.add(calendarMenu);
		
		Icon = new JLabel(" ", JLabel.CENTER); 
		ImageIcon calendar_icon = new ImageIcon("Project_SD/image/icon/calendar_icon.png");
        img = calendar_icon.getImage();
    	updateImg = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateImg);
        Icon.setIcon(updateIcon);
        Icon.setBounds(30, 422,32,32);
		menuForm.add(Icon);
		
		line = new JPanel();
		line.setBackground(st.inputGray);
		line.setBounds(20, 477,210,1);
		menuForm.add(line);
		
		/*** main area ***/
		contentForm = new JPanel();
		contentForm.setBackground(st.backgroundGray);
		contentForm.setSize(1050,800);
		contentForm.setLayout(null);
		contentForm.setLocation(250, 0);
		display.add(contentForm);
		
		userSettingForm = new JPanel();
		userSettingForm.setBackground(st.inputWhite);
		userSettingForm.setBounds(55,50,925,660);
		userSettingForm.setLayout(null);
		userSettingForm.setBorder(new LineBorder(st.lightGray,1,true));
		contentForm.add(userSettingForm);
		
		Icon = new JLabel(" ", JLabel.CENTER); 
		user_icon = new ImageIcon("Project_SD/image/icon/user_icon.png");
        Icon.setIcon(user_icon);
        Icon.setBounds(70, 80,74,74);
        userSettingForm.add(Icon);
		
        JPanel userNameSettingForm = new JPanel(); 
        userNameSettingForm.setBackground(st.inputWhite);
        userNameSettingForm.setBounds(160, 87,162,38);
        userNameSettingForm.setLayout(null);
        userNameSettingForm.setBorder(new LineBorder(st.lightGray,2,true));
        userSettingForm.add(userNameSettingForm);
        
        userNameSetting = new JTextField(userInfo.getName());
        userNameSetting.setBounds(6, 6,148,26);
        userNameSetting.setFont(st.neo_R.deriveFont((float)24));
        userNameSetting.setForeground(st.inputBlack);
        userNameSetting.setBorder(new LineBorder(st.inputWhite,1,true));
        userNameSettingForm.add(userNameSetting);
		
		userCode = new JLabel("#"+String.valueOf(userInfo.getNo()));
		userCode.setBounds(160, 130,50,20);
		userCode.setFont(st.neo_R.deriveFont((float)16));
		userCode.setForeground(st.inputGray);
		userSettingForm.add(userCode);
		
		
	}

	public static void main(String[] args) {
		// LoginFrame rf = new LoginFrame("Shared Diary : Login"); // 프로그램 실행시 로그인 화면 출력
		new MainFrame("Shared Diary",22);
	}


}
