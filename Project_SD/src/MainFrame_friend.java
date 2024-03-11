import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MainFrame_friend {

	// content area //
	private JPanel contentForm = null;
	JPanel friendForm = null;
	// menu //
	private JPanel menuForm = null;
	private JPanel menuLine = null;
	private JLabel friendListMenu;
	private JLabel userSearchMenu;
	private JLabel friendToMenu; // 보낸 친구신청 삭제 메뉴
	private JLabel friendFromMenu; // 받은 친구신청 수락 메뉴
	// 컨텐츠 //
	private JLabel title;
	// 참조 클래스 //
    Style st = new Style();
    UserSD_DAO userDao = new UserSD_DAO();
    UserSD_DTO userInfo = new UserSD_DTO();
    MainFrame mainFrame = null;
    JPanel display = null;
    friend_content fc = null;
    MainFrame_friend.MouseHandler mouseHandler = new MainFrame_friend.MouseHandler();
    MainFrame_friend mf_f = this;
    
    public MainFrame_friend() {

    }
    
	public MainFrame_friend(MainFrame mainFrame, UserSD_DTO userInfo) {

		this.display = mainFrame.display;
		this.mainFrame = mainFrame;
		this.userInfo = userInfo;
		compose(display,userInfo);
		setEvent();
		st.composeJOptionPane();
		
		mouseHandler.menuOpen(friendListMenu);
		
		// 껏다 켜야 제데로 출력됨
		contentForm.setVisible(false);
		contentForm.setVisible(true);
		
	}
	
	public void MainFrame_friend_exit() {

		contentForm.setVisible(false);
		
	}

	private void setEvent() {
		
		friendListMenu.addMouseListener(new MouseHandler());
		userSearchMenu.addMouseListener(new MouseHandler());
		friendToMenu.addMouseListener(new MouseHandler());
		friendFromMenu.addMouseListener(new MouseHandler());
		
	}

	private void compose(JPanel display, UserSD_DTO userInfo) {

		/*** container and menu ***/
		contentForm = new JPanel();
		contentForm.setBackground(st.backgroundGray);
		contentForm.setSize(980,800);
		contentForm.setLayout(null);
		contentForm.setLocation(220, 0);
		display.add(contentForm);
		
		friendForm = new JPanel();
		friendForm.setBackground(st.inputWhite);
		friendForm.setBounds(61,50,849,660);
		friendForm.setLayout(null);
		friendForm.setBorder(new LineBorder(st.lightGray,1,true));
		contentForm.add(friendForm);
		
		// 메뉴 //
		menuLine = new JPanel();
		menuLine.setBackground(st.mainColor);
		menuLine.setBounds(0,59,849,1);
		friendForm.add(menuLine);
		
        menuForm = new JPanel();
        menuForm.setBackground(st.inputWhite);
        menuForm.setBounds(0,0,849,60);
        menuForm.setBorder(new LineBorder(st.lightGray,1,true));
        menuForm.setLayout(null);
        friendForm.add(menuForm);
        
        friendListMenu = new JLabel("친구 목록",JLabel.CENTER);
        friendListMenu.setBounds(0,0,213,60);
        friendListMenu.setFont(st.neo_B.deriveFont((float)16));
        friendListMenu.setForeground(st.lightGray);
        friendListMenu.setBorder(new LineBorder(st.lightGray,1,true));
        menuForm.add(friendListMenu);
        // friendListMenu.setBorder(null);

        userSearchMenu = new JLabel("친구 추가",JLabel.CENTER);
        userSearchMenu.setBounds(212,0,213,60);
        userSearchMenu.setFont(st.neo_B.deriveFont((float)16));
        userSearchMenu.setForeground(st.lightGray);
        userSearchMenu.setBorder(new LineBorder(st.lightGray,1,true));
        menuForm.add(userSearchMenu);
        
        friendToMenu = new JLabel("보낸 친구 요청",JLabel.CENTER);
        friendToMenu.setBounds(424,0,213,60);
        friendToMenu.setFont(st.neo_B.deriveFont((float)16));
        friendToMenu.setForeground(st.lightGray);
        friendToMenu.setBorder(new LineBorder(st.lightGray,1,true));
        menuForm.add(friendToMenu);
        
        friendFromMenu = new JLabel("받은 친구 요청",JLabel.CENTER);
        friendFromMenu.setBounds(636,0,213,60);
        friendFromMenu.setFont(st.neo_B.deriveFont((float)16));
        friendFromMenu.setForeground(st.lightGray);
        friendFromMenu.setBorder(new LineBorder(st.lightGray,1,true));
        menuForm.add(friendFromMenu);
        
        // 컨텐츠
		title = new JLabel();
		title.setBounds(50,100,200,30);
		title.setFont(st.neo_EB.deriveFont((float)24));
		title.setForeground(st.inputBlack);
		friendForm.add(title);
		
		JPanel titleLine = new JPanel();
		titleLine.setBackground(st.inputBlack);
		titleLine.setBounds(50,154,749,1);
		friendForm.add(titleLine);
        
        
	}
	
	// 마우스 이벤트 //
		class MouseHandler extends MouseAdapter{
			
			public void mouseEntered(MouseEvent e) {

				Object obj = e.getSource();

			}
			
			public void mouseExited(MouseEvent e) {

				Object obj = e.getSource();
				
			}
			
			public void mouseClicked(MouseEvent e) {
				
				Object obj = e.getSource();
				menuOpen(obj);
				
			}
			
			public void menuOpen(Object obj) {

				if(obj == friendListMenu) {
					
					initMenu();
					menuForm.add(friendListMenu);
			        menuForm.add(userSearchMenu);
			        menuForm.add(friendToMenu);
			        menuForm.add(friendFromMenu);
					friendListMenu.setBorder(new LineBorder(st.mainColor,1,true));
					friendListMenu.setForeground(st.mainColor);
					
					if(fc != null) {
						fc.closeContent();						
					}
					fc = new friend_content(mf_f,userInfo,friendListMenu.getText()); 
					
				}else if(obj == userSearchMenu) {
					
					initMenu();
			        menuForm.add(userSearchMenu);
			        menuForm.add(friendListMenu);
			        menuForm.add(friendToMenu);
			        menuForm.add(friendFromMenu);
					userSearchMenu.setBorder(new LineBorder(st.mainColor,1,true));
					userSearchMenu.setForeground(st.mainColor);
					
					if(fc != null) {
						fc.closeContent();						
					}
					fc = new friend_content(mf_f,userInfo,userSearchMenu.getText());
					
				}else if(obj == friendToMenu) {
					
					initMenu();
					menuForm.add(friendToMenu);
					menuForm.add(friendListMenu);
			        menuForm.add(userSearchMenu);
			        menuForm.add(friendFromMenu);
					friendToMenu.setBorder(new LineBorder(st.mainColor,1,true));
					friendToMenu.setForeground(st.mainColor);
					
					if(fc != null) {
						fc.closeContent();						
					}
					fc = new friend_content(mf_f,userInfo,friendToMenu.getText());
					
				}else if(obj == friendFromMenu) {
					
					initMenu();
					menuForm.add(friendFromMenu);
					menuForm.add(friendListMenu);
			        menuForm.add(userSearchMenu);
			        menuForm.add(friendToMenu);
					friendFromMenu.setBorder(new LineBorder(st.mainColor,1,true));
					friendFromMenu.setForeground(st.mainColor);
					
					if(fc != null) {
						fc.closeContent();						
					}
					fc = new friend_content(mf_f,userInfo,friendFromMenu.getText());
					
				}

				
			}

			public void initMenu() { // 메뉴 컬러 초기화 메서드

				friendListMenu.setBorder(new LineBorder(st.lightGray,1,true));
				friendListMenu.setForeground(st.lightGray);
				userSearchMenu.setBorder(new LineBorder(st.lightGray,1,true));
				userSearchMenu.setForeground(st.lightGray);
				friendToMenu.setBorder(new LineBorder(st.lightGray,1,true));
				friendToMenu.setForeground(st.lightGray);
				friendFromMenu.setBorder(new LineBorder(st.lightGray,1,true));
				friendFromMenu.setForeground(st.lightGray);
				menuForm.add(menuLine); // 라인이 가장 앞으로 오도록 초기화

			}
			
		}
		
		public String getMenu(int num) {
			
			switch (num) {
			case 1: 
				return friendListMenu.getText();
			case 2: 
				return userSearchMenu.getText();
			case 3: 
				return friendToMenu.getText();
			default:
				return friendFromMenu.getText();
			}
			
		}
		
		public void renameTitle(String title) {
			this.title.setText(title);
		}
	
}
