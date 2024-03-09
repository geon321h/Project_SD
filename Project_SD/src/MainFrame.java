
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
	
    
	// 유저 식별 키 //
	UserSD_DTO userInfo = null;
	
	// 참조 클래스 //
    Style st = new Style();
    UserSD_DAO userDao = new UserSD_DAO();
    
	public MainFrame() {
		
	}
	
	public MainFrame(String title, int no) {

		super(title);
		userInfo = userDao.getUserById(no);
		compose(); // 화면 구성
		setEvent();
		st.composeJOptionPane();
		
		setSize(1200,800);
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
		
		MainFrame_user mf_user = new MainFrame_user(this,display,userInfo);
		
		/*** menu area ***/
		menuForm = new JPanel();
		menuForm.setBackground(st.menuBlack);
		menuForm.setSize(220,800);
		menuForm.setLayout(null);
		menuForm.setLocation(0, 0);
		display.add(menuForm);
		// 로고 //
		logo = new JLabel("<html><body style='text-align:left;'>Shared<br />Diary</body></html>");
		logo.setBounds(95, 57,150,50);
		logo.setFont(st.neo_EB.deriveFont((float)16));
		logo.setForeground(Color.lightGray);
		menuForm.add(logo);
		
		Icon = new JLabel(" ", JLabel.LEFT); 
		ImageIcon logo_icon = new ImageIcon("Project_SD/image/icon/diary_icon.png");
		img = logo_icon.getImage();
    	updateImg = img.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateImg);
        Icon.setIcon(updateIcon);
        Icon.setBounds(40, 50,64,64);
		menuForm.add(Icon);
		
		line = new JPanel();
		line.setBackground(st.DimGray);
		line.setBounds(20, 140,180,1);
		menuForm.add(line);
		
		// 프로필 //
		Icon = new JLabel(" ", JLabel.LEFT); 
		ImageIcon user_icon = new ImageIcon("Project_SD/image/icon/user_icon.png");
		img = user_icon.getImage();
    	updateImg = img.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateImg);
        Icon.setIcon(updateIcon);
        Icon.setBounds(35, 160,58,58);
		menuForm.add(Icon);
		
		userName = new JLabel(userInfo.getName());
		userName.setBounds(100, 170,100,20);
		userName.setFont(st.neo_B.deriveFont((float)16));
		userName.setForeground(st.lightGray);
		menuForm.add(userName);
		
		userCode = new JLabel("#"+String.valueOf(userInfo.getNo()));
		userCode.setBounds(100, 190,50,20);
		userCode.setFont(st.neo_R.deriveFont((float)12));
		userCode.setForeground(st.inputGray);
		menuForm.add(userCode);
		
		line = new JPanel();
		line.setBackground(st.DimGray);
		line.setBounds(20, 238,180,1);
		menuForm.add(line);
		
		// main menu //
		menuTitle = new JLabel("메뉴 선택");
		menuTitle.setBounds(30, 258,100,20);
		menuTitle.setFont(st.neo_B.deriveFont((float)14));
		menuTitle.setForeground(st.inputGray);
		menuForm.add(menuTitle);
		
		groupMenu = new JButton("내 그룹");
		groupMenu.setBounds(80, 309,55,20);
		groupMenu.setFont(st.neo_B.deriveFont((float)16));
		groupMenu.setForeground(st.inputGray);
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
		friendMenu.setBounds(80, 369,55,20);
		friendMenu.setFont(st.neo_B.deriveFont((float)16));
		friendMenu.setForeground(st.inputGray);
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
		
		calendarMenu = new JButton("계정 설정");
		calendarMenu.setBounds(80, 429,80,20);
		calendarMenu.setFont(st.neo_B.deriveFont((float)16));
		calendarMenu.setForeground(st.inputGray);
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
		line.setBackground(st.DimGray);
		line.setBounds(20, 477,180,1);
		menuForm.add(line);

		
	}
	
	public void replaceName(String name) {
		userName.setText(name);
	}

	public static void main(String[] args) {
		//LoginFrame rf = new LoginFrame("Shared Diary : Login"); // 프로그램 실행시 로그인 화면 출력
		new MainFrame("Shared Diary",3);
	}


}
