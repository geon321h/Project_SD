import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB_SD.GroupJoin_DAO;
import DB_SD.GroupJoin_DTO;
import DB_SD.GroupSD_DAO;
import DB_SD.GroupSD_DTO;
import DB_SD.Groupsd_user_list_DAO;

public class Group_content extends JFrame{

	// container //
	private JPanel contentForm;
	private JPanel groupForm;
	private JPanel groupListForm;
	private JPanel groupWorkspaceForm;
	private JTable table;
	private Object[][] rowData ;
	private JScrollPane scrollPane;
	// content_ListForm //
	private JLabel groupTitle;
	private JLabel groupNo;
	private JLabel back_Icon;
	private JButton btnUpdateGroup;
	private JButton btnInviteList;
	// content_WorkspaceForm //
	private JPanel settingForm;
	private JTextField groupName;
	private JButton btnUpdateGroupName;
	private JButton btnDeleteGroup;
	private JPanel groupUserForm;
	// 참조 클래스 //
    Style st = new Style();
    UserSD_DTO userInfo = new UserSD_DTO();
    GroupSD_DTO groupInfo = new GroupSD_DTO();
    ArrayList<GroupJoin_DTO> lists = null;
    GroupSD_DAO groupDao = new GroupSD_DAO();
    GroupJoin_DAO gJoinDao = new GroupJoin_DAO();
    Groupsd_user_list_DAO groupUserDao = new Groupsd_user_list_DAO();
    MainFrame_group mf_g;
    DefaultTableModel model;
    
	public Group_content() {

	}


	public Group_content(MainFrame_group mf_g, UserSD_DTO userInfo, Integer groupNo) {

		this.contentForm = mf_g.contentForm;
		this.mf_g = mf_g;
		this.userInfo = userInfo;
		groupInfo = groupDao.getGroupByNo(groupNo);
		
		compose();
		setEvent();
	
	}
	
	private void setEvent() {
	
		back_Icon.addMouseListener(new MouseHandler());
		btnInviteList.addActionListener(new ActionHandler());
		
	}

	private void compose() {

		/*** container  ***/
		groupForm = new JPanel();
		groupForm.setBackground(st.backgroundGray);
		groupForm.setBounds(61,50,849,660);
		groupForm.setLayout(null);
		contentForm.add(groupForm);
		
		/*** list area  ***/
		groupListForm = new JPanel();
		groupListForm.setBackground(st.inputWhite);
		groupListForm.setBounds(0,0,509,660);
		groupListForm.setLayout(null);
		groupListForm.setBorder(new LineBorder(st.lightGray,1,true));
		groupForm.add(groupListForm);
		
		// header //
		groupTitle = new JLabel(groupInfo.getGroup_name());
		groupTitle.setBounds(50,58,150,30);
		groupTitle.setFont(st.neo_EB.deriveFont((float)24));
		groupTitle.setForeground(st.inputBlack);
		groupListForm.add(groupTitle);
		
		groupNo = new JLabel("#"+String.valueOf(groupInfo.getGroup_no()));
		groupNo.setBounds(50,83,150,30);
		groupNo.setFont(st.neo_B.deriveFont((float)16));
		groupNo.setForeground(st.inputGray);
		groupListForm.add(groupNo);

		back_Icon = new JLabel(" ", JLabel.CENTER);
		ImageIcon Icon = new ImageIcon("Project_SD/image/icon/back_arrow_icon.png");
		back_Icon.setIcon(Icon);
		back_Icon.setBounds(470, 10,32,32);
		groupListForm.add(back_Icon);
		
		
		if(groupDao.checkManager(userInfo.getNo(),groupInfo.getGroup_no()) == 1) {
			btnUpdateGroup = new JButton("그룹 수정");
			btnUpdateGroup.setBounds(290,70,80,30);
			btnUpdateGroup.setFont(st.neo_B.deriveFont((float)14));
			btnUpdateGroup.setBackground(st.inputWhite);
			btnUpdateGroup.setBorder(new LineBorder(st.inputBlack,1,false));
			btnUpdateGroup.setForeground(st.inputBlack);
			btnUpdateGroup.setFocusable(false);
			groupListForm.add(btnUpdateGroup);
			btnUpdateGroup.addActionListener(new ActionHandler());
		}
        
		btnInviteList = new JButton("초대 하기");
		btnInviteList.setBounds(380,70,80,30);
		btnInviteList.setFont(st.neo_B.deriveFont((float)14));
		btnInviteList.setBackground(st.mainColor);
		btnInviteList.setBorder(new LineBorder(st.mainColor,1,false));
		btnInviteList.setForeground(st.inputWhite);
		btnInviteList.setFocusable(false);
		groupListForm.add(btnInviteList);
		
		JPanel Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(50,120,409,1);
		groupListForm.add(Line);
		
		/*** workspace area  ***/
		groupWorkspaceForm = new JPanel();
		groupWorkspaceForm.setBackground(st.inputWhite);
		groupWorkspaceForm.setBounds(529,0,320,660);
		groupWorkspaceForm.setLayout(null);
		groupWorkspaceForm.setBorder(new LineBorder(st.lightGray,1,true));
		groupForm.add(groupWorkspaceForm);
		
	}
	
	private void createSetting() {

		if(groupWorkspaceForm != null) {
			groupWorkspaceForm.setVisible(false);
		}
		groupWorkspaceForm = new JPanel();
		groupWorkspaceForm.setBackground(st.inputWhite);
		groupWorkspaceForm.setBounds(529,0,320,660);
		groupWorkspaceForm.setLayout(null);
		groupWorkspaceForm.setBorder(new LineBorder(st.lightGray,1,true));
		groupForm.add(groupWorkspaceForm);
		
		settingForm = new JPanel();
		settingForm.setBackground(st.inputWhite);
		settingForm.setBounds(20,40,280,580);
		settingForm.setLayout(null);
		settingForm.setBorder(new LineBorder(st.lightGray,0,true));
		groupWorkspaceForm.add(settingForm);
		
		JLabel settingTitle = new JLabel("그룹 설정");
		settingTitle.setBounds(90,0,150,30);
		settingTitle.setFont(st.neo_EB.deriveFont((float)24));
		settingTitle.setForeground(st.inputBlack);
		settingForm.add(settingTitle);
		
		JPanel Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(0,50,280,1);
		settingForm.add(Line);
		
		JLabel groupNameLb = new JLabel("그룹명");
		groupNameLb.setBounds(0,70,150,30);
		groupNameLb.setFont(st.neo_B.deriveFont((float)18));
		groupNameLb.setForeground(st.inputBlack);
		settingForm.add(groupNameLb);
		
		JPanel groupNameForm = new JPanel();
		groupNameForm.setBackground(st.inputWhite);
		groupNameForm.setBounds(0, 105,150,30);
		groupNameForm.setLayout(null);
		groupNameForm.setBorder(new LineBorder(st.inputBlack,1,false));
		settingForm.add(groupNameForm);
		
		groupName = new JTextField(groupInfo.getGroup_name());
		groupName.setBounds(1, 1,148,28);
		groupName.setFont(st.neo_R.deriveFont((float)14));
        groupName.setForeground(st.inputBlack);
        groupName.setBackground(st.inputWhite);
        groupName.setBorder(BorderFactory.createEmptyBorder(5, 5, 4, 5));
        groupNameForm.add(groupName);
        
        btnUpdateGroupName = new JButton("변경");
        btnUpdateGroupName.setBounds(160, 105,50,30);
        btnUpdateGroupName.setBackground(st.inputBlack);
        btnUpdateGroupName.setBorder(new LineBorder(st.inputBlack,1,false));
        btnUpdateGroupName.setFont(st.neo_B.deriveFont((float)14));
        btnUpdateGroupName.setForeground(st.inputWhite);
        btnUpdateGroupName.setFocusPainted(false);
        settingForm.add(btnUpdateGroupName);
        btnUpdateGroupName.addActionListener(new ActionHandler());
        
		JLabel groupUserLb = new JLabel("참여자");
		groupUserLb.setBounds(0,145,150,30);
		groupUserLb.setFont(st.neo_B.deriveFont((float)18));
		groupUserLb.setForeground(st.inputBlack);
		settingForm.add(groupUserLb);
        
		groupUserForm = new JPanel();
		groupUserForm.setBackground(st.inputWhite);
		groupUserForm.setBounds(0, 180,280,360);
		groupUserForm.setLayout(null);
		groupUserForm.setBorder(new LineBorder(st.inputBlack,1,false));
		settingForm.add(groupUserForm);
        
        btnDeleteGroup = new JButton("그룹 삭제");
        btnDeleteGroup.setBounds(200, 550,80,30);
        btnDeleteGroup.setBackground(st.inputWhite);
        btnDeleteGroup.setBorder(new LineBorder(st.inputBlack,1,false));
        btnDeleteGroup.setFont(st.neo_B.deriveFont((float)14));
        btnDeleteGroup.setForeground(st.inputBlack);
        btnDeleteGroup.setFocusPainted(false);
        settingForm.add(btnDeleteGroup);
        btnDeleteGroup.addActionListener(new ActionHandler());
		
	}
	
	private void createTable_friend() {

		lists = gJoinDao.getGroupUserList(groupInfo.getGroup_no(),userInfo.getNo());
		Object[] columnNames = {"이름","추방"};
		rowData = new Object[lists.size()][2];
		freindData(); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.addMouseListener(new MouseHandler());
		table.setTableHeader(null); // 테이블 헤더 지우기

		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(1, 1, 298,358);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		groupUserForm.add(scrollPane);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(260); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("추방").setPreferredWidth(40);

	}

	private void createTable_friend_after() {

		lists = gJoinDao.getGroupUserList(groupInfo.getGroup_no(),userInfo.getNo());
		Object[] columnNames = {"이름","추방"};
		rowData = new Object[lists.size()][2];
		freindData(); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.setTableHeader(null); // 테이블 헤더 지우기
		table.addMouseListener(new MouseHandler());
		scrollPane.setViewportView(table);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(260); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("추방").setPreferredWidth(40);

	}
	
	private void freindData() {

		int j=0;
		for(int i=0;i<lists.size();i++) {
			rowData[i][j++] = lists.get(i).getGroup_name()+" #"+lists.get(i).getGroup_no();
			rowData[i][j++] = "추방";
			j = 0;
		}
		
	}
	
	private DefaultTableModel tableProtect(Object[][] rowData, Object[] columnNames) {
		// Jtable 내용 편집 막기

		model = new DefaultTableModel(rowData,columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			public Class<?> getColumnClass(int column)  {
				// row - JTable에 입력된 2차원 배열의  행에 속한다면
				// 모든 컬럼을 입력된 형으로  반환한다.

				// 다시말해, 어떤 행이든 간에 입력된  column의 class를 반환하도록 한 것
				return getValueAt(0,  column).getClass();
			}

		};
		return model;
	}
	
	private void tableStyle(JTable table) { // 테이블 초기 스타일 설정

		table.setFillsViewportHeight(true); // 테이블 수정 가능하게 변경
		table.setBackground(st.inputWhite);
		table.setFont(st.neo_R.deriveFont((float)16));
		table.setForeground(st.inputBlack);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setRowHeight(50);
		table.setFocusable(false);
		table.setSelectionBackground(null);
		table.setSelectionForeground(st.mainColor);

	}

	// 마우스 이벤트 //
	class MouseHandler extends MouseAdapter{

		public void mouseClicked(MouseEvent e) {
			
			Object obj = e.getSource();
			
			if(obj == back_Icon) {
				groupForm.setVisible(false);
				mf_g.groupForm.setVisible(true);
				mf_g.renewalContent();
			}
			
		}

	}
	
	// 액션 이벤트 //
	class ActionHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			// 그룹 생성 버튼 //
			if(obj == btnUpdateGroup) {
				createSetting();
			}

			// 친구 목록 확인 //
			if(obj == btnInviteList) {
				InviteList il = new InviteList("초대 하기",groupInfo.getGroup_no(),mf_g);
			}

			// 그룹명 수정 //
			if(obj == btnUpdateGroupName) {
				String gName = groupName.getText();
				try {
					if(gName.length()<=8 && !gName.trim().equals("")) {

						JLabel paneMessage = new JLabel(gName+"로 수정하시겠습니까?");
						paneMessage.setFont(st.neo_R.deriveFont((float)12));
						paneMessage.setForeground(st.inputBlack);
						int yesOrNo = JOptionPane.showConfirmDialog(null, paneMessage,"그룹명 수정",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
						if(yesOrNo == 0) {

							int cnt = groupDao.updateGroupName(gName,groupInfo.getGroup_no());
							if(cnt != -1) {
								JLabel paneMessage_update = new JLabel("수정 되었습니다.");
								paneMessage_update.setFont(st.neo_R.deriveFont((float)12));
								paneMessage_update.setForeground(st.inputBlack);
								JOptionPane.showMessageDialog(null, paneMessage_update,"그룹명 수정",JOptionPane.PLAIN_MESSAGE);
								
								if(groupListForm != null) {
									groupForm.setVisible(false);
								}
								Group_content gc = new Group_content(mf_g,userInfo,groupInfo.getGroup_no());
								
							}else {
								System.out.println("수정 실패");
							}
						}
					}else {
						JLabel paneMessage_guide = new JLabel("그룹명을 8자 이하로 입력해주세요.");
						paneMessage_guide.setFont(st.neo_R.deriveFont((float)14));
						paneMessage_guide.setForeground(st.inputBlack);
						JOptionPane.showMessageDialog(null, paneMessage_guide,"그룹 생성",JOptionPane.PLAIN_MESSAGE);
					}

				} catch (NullPointerException e2) {
					System.out.println("그룹명을 입력하지 않음");
				}

			}
		}
	}
}
