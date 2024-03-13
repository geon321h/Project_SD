import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import DB_SD.GroupJoin_DAO;
import DB_SD.GroupJoin_DTO;
import DB_SD.GroupSD_DAO;
import DB_SD.Groupsd_user_list_DAO;

public class MainFrame_group {

	// content area //
	JPanel contentForm;
	JPanel groupForm;
	private JPanel display;
	// header //
	private JLabel groupTitle;
	private JLabel showGruopAll;
	private JLabel showGruopManager;
	private JButton btnInviteList;
	private JButton btnCreateGroup;
	// content //
	private JPanel groupListForm;
	private JPanel groupInfoForm;
	private JLabel groupInfoTitle;
	private JLabel groupInfoDay;
	private JLabel groupInfoUserCount;
	ArrayList<JPanel> FormList;
	ArrayList<Integer> groupNo;
	// 참조 클래스 //
    Style st = new Style();
    UserSD_DTO userInfo = new UserSD_DTO();
    ArrayList<GroupJoin_DTO> lists = null;
    GroupSD_DAO groupDao = new GroupSD_DAO();
    GroupJoin_DAO gJoinDao = new GroupJoin_DAO();
    Groupsd_user_list_DAO groupUserDao = new Groupsd_user_list_DAO();
    MainFrame mainFrame = null;
    
    MainFrame_group mf_g = this;
	
	public MainFrame_group() {

	}
	
	public MainFrame_group(MainFrame mainFrame, UserSD_DTO userInfo) {

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
	
	public void MainFrame_group_exit() {

		contentForm.setVisible(false);
		
	}

	private void setEvent() {
		
		showGruopAll.addMouseListener(new MouseHandler());
		showGruopManager.addMouseListener(new MouseHandler());
		btnInviteList.addActionListener(new ActionHandler());
		btnCreateGroup.addActionListener(new ActionHandler());
		
	}

	private void compose(JPanel display, UserSD_DTO userInfo) {

		/*** container  ***/
		contentForm = new JPanel();
		contentForm.setBackground(st.backgroundGray);
		contentForm.setSize(980,800);
		contentForm.setLayout(null);
		contentForm.setLocation(220, 0);
		display.add(contentForm);
		
		groupForm = new JPanel();
		groupForm.setBackground(st.inputWhite);
		groupForm.setBounds(61,50,849,660);
		groupForm.setLayout(null);
		groupForm.setBorder(new LineBorder(st.lightGray,1,true));
		contentForm.add(groupForm);

		/*** header  ***/
		groupTitle = new JLabel("내 그룹");
		groupTitle.setBounds(50,58,120,30);
		groupTitle.setFont(st.neo_EB.deriveFont((float)28));
		groupTitle.setForeground(st.inputBlack);
        groupForm.add(groupTitle);
		
        showGruopAll = new JLabel("전체 그룹");
        showGruopAll.setBounds(440,60,60,30);
        showGruopAll.setFont(st.neo_R.deriveFont((float)14));
        showGruopAll.setForeground(st.mainColor);
        groupForm.add(showGruopAll);
        
        JLabel showLine = new JLabel("|");
        showLine.setBounds(510,60,100,30);
        showLine.setFont(st.neo_R.deriveFont((float)14));
        showLine.setForeground(st.inputGray);
        groupForm.add(showLine);
        
        showGruopManager = new JLabel("관리자인 그룹");
        showGruopManager.setBounds(530,60,100,30);
        showGruopManager.setFont(st.neo_R.deriveFont((float)14));
        showGruopManager.setForeground(st.inputGray);
        groupForm.add(showGruopManager);
		
		btnInviteList = new JButton("초대 목록");
		btnInviteList.setBounds(630,60,80,30);
		btnInviteList.setFont(st.neo_B.deriveFont((float)14));
		btnInviteList.setBackground(st.inputWhite);
		btnInviteList.setBorder(new LineBorder(st.inputBlack,1,false));
		btnInviteList.setForeground(st.inputBlack);
		btnInviteList.setFocusable(false);
        groupForm.add(btnInviteList);
        
		btnCreateGroup = new JButton("그룹 생성");
		btnCreateGroup.setBounds(720,60,80,30);
		btnCreateGroup.setFont(st.neo_B.deriveFont((float)14));
		btnCreateGroup.setBackground(st.mainColor);
		btnCreateGroup.setBorder(new LineBorder(st.mainColor,1,false));
		btnCreateGroup.setForeground(st.inputWhite);
		btnCreateGroup.setFocusable(false);
        groupForm.add(btnCreateGroup);
        
		JPanel Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(50,105,749,1);
		groupForm.add(Line);
		
		/*** content  ***/
		lists = gJoinDao.getMyGroup(userInfo.getNo());
		createGroupList(lists);
		
	}

	private void createGroupList(ArrayList<GroupJoin_DTO> lists) {

		groupNo = new ArrayList<>();
		FormList = new ArrayList<>();
		
		/*** content  ***/
		if(groupListForm != null) {
			groupListForm.setVisible(false);
		}
		groupListForm = new JPanel();
		groupListForm.setBounds(50, 155,749,460);
		groupListForm.setBorder(new LineBorder(st.mainColor,0,true));
		groupListForm.setLayout(null);
		groupForm.add(groupListForm);
		
		int x=0,y=0;
		for(int i=0;i<lists.size();i++) {
			
			int no = lists.get(i).getGroup_no();
			groupNo.add(no);
			
			groupInfoForm = new JPanel();
			groupInfoForm.setBounds((167+25)*x,(136+25)*y,172,136);
			groupInfoForm.setBorder(new LineBorder(st.inputBlack,1,false));
			groupInfoForm.setLayout(null);
			FormList.add(groupInfoForm);
			groupListForm.add(FormList.get(i));
			
			String title = lists.get(i).getGroup_name();
			groupInfoTitle = new JLabel(title);
			groupInfoTitle.setBounds(15,20,120,20);
			groupInfoTitle.setFont(st.neo_B.deriveFont((float)16));
			groupInfoTitle.setForeground(st.inputBlack);
			FormList.get(i).add(groupInfoTitle);

			String day = lists.get(i).getGroup_create_day();
			groupInfoDay = new JLabel(day);
			groupInfoDay.setBounds(15,100,120,20);
			groupInfoDay.setFont(st.neo_R.deriveFont((float)12));
			groupInfoDay.setForeground(st.inputGray);
			FormList.get(i).add(groupInfoDay);

			JLabel user_Icon = new JLabel(" ", JLabel.CENTER);
			ImageIcon Icon = new ImageIcon("Project_SD/image/icon/user_count_icon.png");
			user_Icon.setIcon(Icon);
			user_Icon.setBounds(130, 98,24,24);
			FormList.get(i).add(user_Icon);
			
			String userCount = String.valueOf(lists.get(i).getGroup_manager_no());
			groupInfoUserCount = new JLabel(userCount);
			groupInfoUserCount.setHorizontalAlignment(JLabel.RIGHT);
			groupInfoUserCount.setBounds(143,100,15,20);
			groupInfoUserCount.setFont(st.neo_R.deriveFont((float)12));
			groupInfoUserCount.setForeground(st.inputGray);
			FormList.get(i).add(groupInfoUserCount);
			
			FormList.get(i).addMouseListener(new MouseHandler());
			x++;
			if((i+1)%4 == 0) {
				x=0;
				y++;
			}
			
		}
		
	}
	
	// 마우스 이벤트 //
	class MouseHandler extends MouseAdapter{

		public void mouseClicked(MouseEvent e) {
			
			Object obj = e.getSource();
			
			for(int i=0;i<FormList.size();i++) {
				
				if(FormList.get(i).equals(obj)) {
					groupForm.setVisible(false);
					Group_content gc = new Group_content(mf_g,userInfo,groupNo.get(i));
				}
				
			}
			
			if(obj == showGruopAll) {
				if(showGruopAll.getForeground() != st.mainColor) {
					showGruopAll.setForeground(st.mainColor);
					showGruopManager.setForeground(st.inputGray);
					lists = gJoinDao.getMyGroup(userInfo.getNo());
					createGroupList(lists);
				}
			}else if(obj == showGruopManager) {
				if(showGruopManager.getForeground() != st.mainColor) {
					showGruopAll.setForeground(st.inputGray);
					showGruopManager.setForeground(st.mainColor);
					lists = gJoinDao.getMyManagerGroup(userInfo.getNo());
					createGroupList(lists);
				}
			}
			
		}

	}
	
	// 액션 이벤트 //
	class ActionHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			
			// 그룹 생성 버튼 //
			if(obj == btnCreateGroup) {
				createGroupScreen();
			}
			
			// 초대 목록 확인 //
			if(obj == btnInviteList) {
				inviteListScreen();
			}
			
		}

		private void createGroupScreen() {

			JLabel paneMessage = new JLabel("<html><body style='text-align:left;'><p  style='font-family: 나눔스퀘어 네오 Bold; font-size:16;'>그룹명 입력</p><p  style='font-size:12; color:rgb(128, 128, 128);'>그룹명은 8자 이하로 입력해주세요.</p></body></html>");
			paneMessage.setFont(st.neo_R.deriveFont((float)14));
			paneMessage.setForeground(st.inputBlack);
			
			try {
				String groupName = JOptionPane.showInputDialog(null, paneMessage,"그룹 생성",JOptionPane.PLAIN_MESSAGE);
				if(groupName.length()<=8 && !groupName.trim().equals("")) {
	
					int cnt = groupUserDao.getMyGroupCount(userInfo.getNo());
					if(cnt>=12) {
						JLabel paneMessage_guide = new JLabel("그룹은 12개까지 포함될 수 있습니다.");
						paneMessage_guide.setFont(st.neo_R.deriveFont((float)14));
						paneMessage_guide.setForeground(st.inputBlack);
						JOptionPane.showMessageDialog(null, paneMessage_guide,"그룹 생성",JOptionPane.PLAIN_MESSAGE);
					}else {
						cnt = groupDao.insertGroup(userInfo.getNo(),groupName);
						if(cnt > 0) {
							JLabel paneMessage_guide = new JLabel("그룹 "+groupName+" (이)/가 생성되었습니다.");
							paneMessage_guide.setFont(st.neo_R.deriveFont((float)14));
							paneMessage_guide.setForeground(st.inputBlack);
							JOptionPane.showMessageDialog(null, paneMessage_guide,"그룹 생성",JOptionPane.PLAIN_MESSAGE);
							
							renewalContent();
						}
					}
				
				}else {
					JLabel paneMessage_guide = new JLabel("그룹명을 8자 이하로 입력해주세요.");
					paneMessage_guide.setFont(st.neo_R.deriveFont((float)14));
					paneMessage_guide.setForeground(st.inputBlack);
					JOptionPane.showMessageDialog(null, paneMessage_guide,"그룹 생성",JOptionPane.PLAIN_MESSAGE);
				}	
			} catch (NullPointerException e) {
				System.out.println("그룹명을 입력하지 않음");
			}
			
		}


	}
	
	private void inviteListScreen() {

		InviteList il = new InviteList("받은 초대 목록",userInfo.getNo(),this);
		
	}
	
	public void renewalContent() {
		
		if(showGruopAll.getForeground() == st.mainColor) {
			lists = gJoinDao.getMyGroup(userInfo.getNo());
			createGroupList(lists);
		}else {
			lists = gJoinDao.getMyManagerGroup(userInfo.getNo());
			createGroupList(lists);
		}
		
	}
}
