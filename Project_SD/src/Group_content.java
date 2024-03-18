import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JTextArea;
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
	private JPanel groupWritingForm;
	// content_WorkspaceForm //
	private JPanel settingForm;
	private JTextField groupName;
	private JButton btnUpdateGroupName;
	private JButton btnDeleteGroup;
	private JPanel groupUserForm;
	private JPanel wirtingForm;
	private JTextField wirtingTitle;
	private JTextArea wirtingContent;
	private JLabel wirtingLength ;
	private JButton btnUpdateWriting;
	private JButton btnDeleteWriting;
	private JButton btnInsertWriting;
	private JButton btnInsert;
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
    
    private int byteW = 0;
    
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
		ImageIcon Icon = new ImageIcon("image/icon/back_arrow_icon.png");
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
		
		btnInsertWriting = new JButton("글쓰기");
		btnInsertWriting.setBounds(380,130,80,30);
		btnInsertWriting.setFont(st.neo_B.deriveFont((float)14));
		btnInsertWriting.setBackground(st.inputWhite);
		btnInsertWriting.setBorder(new LineBorder(st.inputBlack,1,false));
		btnInsertWriting.setForeground(st.inputBlack);
		btnInsertWriting.setFocusable(false);
		groupListForm.add(btnInsertWriting);
		btnInsertWriting.addActionListener(new ActionHandler());

		JPanel Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(50,120,409,1);
		groupListForm.add(Line);
		
		// list //
		groupWritingForm = new JPanel();
		groupWritingForm.setBackground(st.inputWhite);
		groupWritingForm.setBounds(50, 170,409,440);
		groupWritingForm.setLayout(null);
		groupListForm.add(groupWritingForm);

		Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(0,0,409,1);
		groupWritingForm.add(Line);
		
		Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(0,439,409,1);
		groupWritingForm.add(Line);
		
		createTable_writing_list();
		
		/*** workspace area  ***/
		groupWorkspaceForm = new JPanel();
		groupWorkspaceForm.setBackground(st.inputWhite);
		groupWorkspaceForm.setBounds(529,0,320,660);
		groupWorkspaceForm.setLayout(null);
		groupWorkspaceForm.setBorder(new LineBorder(st.lightGray,1,true));
		groupForm.add(groupWorkspaceForm);
		
	}
	
	private void createWriting(int writingNo) {

		lists = gJoinDao.getWritingInfo(groupInfo.getGroup_no(),writingNo);
		if(groupWorkspaceForm != null) {
			groupWorkspaceForm.setVisible(false);
		}
		groupWorkspaceForm = new JPanel();
		groupWorkspaceForm.setBackground(st.inputWhite);
		groupWorkspaceForm.setBounds(529,0,320,660);
		groupWorkspaceForm.setLayout(null);
		groupWorkspaceForm.setBorder(new LineBorder(st.lightGray,1,true));
		groupForm.add(groupWorkspaceForm);
		
		wirtingForm = new JPanel();
		wirtingForm.setBackground(st.inputWhite);
		wirtingForm.setBounds(20,40,280,590);
		wirtingForm.setLayout(null);
		wirtingForm.setBorder(new LineBorder(st.lightGray,0,true));
		groupWorkspaceForm.add(wirtingForm);
		
		wirtingTitle = new JTextField(lists.get(0).getGroup_writing_title());
		wirtingTitle.setBounds(0, 0,200,36);
		wirtingTitle.setFont(st.neo_B.deriveFont((float)24));
		wirtingTitle.setForeground(st.inputBlack);
		wirtingTitle.setBackground(st.inputWhite);
		wirtingTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 4, 5));
		wirtingForm.add(wirtingTitle);
		
		JPanel Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(0,45,280,1);
		wirtingForm.add(Line);
		
		JLabel writing_userLb = new JLabel("작성자: "+lists.get(0).getGroup_name()+" #"+lists.get(0).getGroup_writing_user_no());
		writing_userLb.setBounds(0,55,200,26);
		writing_userLb.setFont(st.neo_R.deriveFont((float)14));
		writing_userLb.setForeground(st.inputGray);
		wirtingForm.add(writing_userLb);
		
		JLabel writing_dayLb = new JLabel("작성일: "+lists.get(0).getGroup_writing_day());
		writing_dayLb.setBounds(0,80,200,26);
		writing_dayLb.setFont(st.neo_R.deriveFont((float)14));
		writing_dayLb.setForeground(st.inputGray);
		wirtingForm.add(writing_dayLb);
		
		Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(0,115,280,1);
		wirtingForm.add(Line);
		
		wirtingContent = new JTextArea(lists.get(0).getGroup_writing_content());
		wirtingContent.setBounds(0, 130,280,400);
		wirtingContent.setFont(st.neo_R.deriveFont((float)16));
		wirtingContent.setForeground(st.inputBlack);
		wirtingContent.setBackground(st.textAreaWhite);
		wirtingContent.setLineWrap(true); // 자동 줄바꿈
		wirtingContent.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		wirtingForm.add(wirtingContent);
		wirtingContent.addKeyListener(new keyHandler());
		
		wirtingLength = new JLabel();
		wirtingLength.setBounds(0,535,120,24);
		wirtingLength.setFont(st.neo_R.deriveFont((float)12));
		wirtingLength.setForeground(st.inputGray);
		wirtingForm.add(wirtingLength);
		byteShow();
		
		checkAuthority(lists);
		
	}
	
	private void createInsertWriting() {

		if(groupWorkspaceForm != null) {
			groupWorkspaceForm.setVisible(false);
		}
		groupWorkspaceForm = new JPanel();
		groupWorkspaceForm.setBackground(st.inputWhite);
		groupWorkspaceForm.setBounds(529,0,320,660);
		groupWorkspaceForm.setLayout(null);
		groupWorkspaceForm.setBorder(new LineBorder(st.lightGray,1,true));
		groupForm.add(groupWorkspaceForm);
		
		wirtingForm = new JPanel();
		wirtingForm.setBackground(st.inputWhite);
		wirtingForm.setBounds(20,40,280,590);
		wirtingForm.setLayout(null);
		wirtingForm.setBorder(new LineBorder(st.lightGray,0,true));
		groupWorkspaceForm.add(wirtingForm);
		
		wirtingTitle = new JTextField("제목을 입력해주세요.");
		wirtingTitle.setBounds(0, 30,250,36);
		wirtingTitle.setFont(st.neo_B.deriveFont((float)24));
		wirtingTitle.setForeground(st.inputGray);
		wirtingTitle.setBackground(st.inputWhite);
		wirtingTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 4, 5));
		wirtingForm.add(wirtingTitle);
		wirtingTitle.addFocusListener(new focusHandler());
		
		JPanel Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(0,75,280,1);
		wirtingForm.add(Line);

		Line = new JPanel();
		Line.setBackground(st.inputBlack);
		Line.setBounds(0,115,280,1);
		wirtingForm.add(Line);
		
		wirtingContent = new JTextArea("내용을 입력해주세요.");
		wirtingContent.setBounds(0, 130,280,400);
		wirtingContent.setFont(st.neo_R.deriveFont((float)16));
		wirtingContent.setForeground(st.inputGray);
		wirtingContent.setBackground(st.textAreaWhite);
		wirtingContent.setLineWrap(true); // 자동 줄바꿈
		wirtingContent.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		wirtingForm.add(wirtingContent);
		wirtingContent.addKeyListener(new keyHandler());
		wirtingContent.addFocusListener(new focusHandler());
		
		wirtingLength = new JLabel();
		wirtingLength.setBounds(0,535,120,24);
		wirtingLength.setFont(st.neo_R.deriveFont((float)12));
		wirtingLength.setForeground(st.inputGray);
		wirtingForm.add(wirtingLength);
		byteShow();
		
		btnInsert = new JButton("글 등록");
		btnInsert.setBounds(200,560,80,30);
		btnInsert.setFont(st.neo_B.deriveFont((float)14));
		btnInsert.setBackground(st.inputWhite);
		btnInsert.setBorder(new LineBorder(st.inputBlack,1,false));
		btnInsert.setForeground(st.inputBlack);
		btnInsert.setFocusable(false);
		wirtingForm.add(btnInsert);
		btnInsert.addActionListener(new ActionHandler());
		
	}
	
	private void checkAuthority(ArrayList<GroupJoin_DTO> lists2) {

		int cnt = gJoinDao.checkAuthority(userInfo.getNo(),groupInfo.getGroup_no(),lists2.get(0).getGroup_writing_no());
		
		if(cnt != 0 && cnt != -1) {
			btnUpdateWriting = new JButton("게시글 수정");
			btnUpdateWriting.setBounds(110,560,80,30);
			btnUpdateWriting.setFont(st.neo_B.deriveFont((float)14));
			btnUpdateWriting.setBackground(st.inputWhite);
			btnUpdateWriting.setBorder(new LineBorder(st.inputBlack,1,false));
			btnUpdateWriting.setForeground(st.inputBlack);
			btnUpdateWriting.setFocusable(false);
			wirtingForm.add(btnUpdateWriting);
			btnUpdateWriting.addActionListener(new ActionHandler());
	        
			btnDeleteWriting = new JButton("게시글 삭제");
			btnDeleteWriting.setBounds(200,560,80,30);
			btnDeleteWriting.setFont(st.neo_B.deriveFont((float)14));
			btnDeleteWriting.setBackground(st.mainColor);
			btnDeleteWriting.setBorder(new LineBorder(st.mainColor,1,false));
			btnDeleteWriting.setForeground(st.inputWhite);
			btnDeleteWriting.setFocusable(false);
			wirtingForm.add(btnDeleteWriting);
			btnDeleteWriting.addActionListener(new ActionHandler());
			
		}else {
			wirtingTitle.setEditable(false);
			wirtingContent.setEditable(false);
		}
		
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
		
		createTable_friend();
        
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
	
	private void createTable_writing_list() {

		
		lists = gJoinDao.getWritingList(groupInfo.getGroup_no());
		Object[] columnNames = {"글번호","글제목","작성자"};
		rowData = new Object[lists.size()][3];
		writingData(); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.addMouseListener(new MouseHandler2());
		table.setTableHeader(null); // 테이블 헤더 지우기

		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 1, 409,438);
		groupWritingForm.add(scrollPane);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("글번호").setPreferredWidth(30); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("글제목").setPreferredWidth(249);
		table.getColumn("작성자").setPreferredWidth(130);

	}
	
	private void writingData() {

		int j=0;
		for(int i=0;i<lists.size();i++) {
			rowData[i][j++] = lists.get(i).getGroup_writing_no()+".";
			rowData[i][j++] = lists.get(i).getGroup_writing_title();
			rowData[i][j++] = lists.get(i).getGroup_name()+" #"+lists.get(i).getGroup_writing_user_no();
			j = 0;
		}
		
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
		scrollPane.setBounds(1, 1, 278,358);
		groupUserForm.add(scrollPane);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(240); // 테이블 내 컨텐츠 행길이 조정
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

		table.getColumn("이름").setPreferredWidth(240); // 테이블 내 컨텐츠 행길이 조정
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
			
			try {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				String user = String.valueOf(table.getValueAt(row,0));
				String tableValue = String.valueOf(table.getValueAt(row,col));
				int userNo = Integer.parseInt(user.substring(user.indexOf("#")+1));
				
				if(tableValue.equals("추방")) {
					System.out.println(userNo);
					System.out.println(groupInfo.getGroup_no());
					int cnt = groupUserDao.deleteGroupUser(userNo,groupInfo.getGroup_no());
					System.out.println(cnt);
					if(cnt != -1 && cnt != 0) {
						createTable_friend_after();
					}
				}
			} catch (Exception e2) {
			}
			
		}

	}
	
	// 마우스 이벤트 //
		class MouseHandler2 extends MouseAdapter{

			public void mouseClicked(MouseEvent e) {
				
				Object obj = e.getSource();
				
				if(obj == back_Icon) {
					groupForm.setVisible(false);
					mf_g.groupForm.setVisible(true);
					mf_g.renewalContent();
				}
				
				try {
					int row = table.getSelectedRow();
					String writing = String.valueOf(table.getValueAt(row,0));
					int writingNo = Integer.parseInt(writing.substring(0,writing.indexOf(".")));

					createWriting(writingNo);
					
				} catch (ArrayIndexOutOfBoundsException e2) {
				}catch(NullPointerException e2) {
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
			
			// 그룹 삭제 버튼 // 
			if(obj == btnDeleteGroup) {

				JLabel paneMessage = new JLabel("정말로 ("+groupInfo.getGroup_name()+ ")그룹을 삭제하시겠습니까?");
				paneMessage.setFont(st.neo_R.deriveFont((float)12));
				paneMessage.setForeground(st.inputBlack);
				int yesOrNo = JOptionPane.showConfirmDialog(null, paneMessage,"그룹 삭제",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
				if(yesOrNo == 0) {
					int cnt = groupDao.deleteGroup(groupInfo.getGroup_no());
					if(cnt != -1) {
						JLabel paneMessage_delete = new JLabel("삭제 되었습니다.");
						paneMessage_delete.setFont(st.neo_R.deriveFont((float)12));
						paneMessage_delete.setForeground(st.inputBlack);
						JOptionPane.showMessageDialog(null, paneMessage_delete,"그룹 삭제",JOptionPane.PLAIN_MESSAGE);
						
						groupForm.setVisible(false);
						mf_g.groupForm.setVisible(true); 
						mf_g.renewalContent();
					}else {
						System.out.println("삭제 실패");
					}
				}
			}
			
			// 게시글 버튼 //
			if(obj == btnUpdateWriting) {
				if(wirtingTitle.getText().length()<=8 && wirtingContent.getText().getBytes().length<=1000) {
					
					int cnt = gJoinDao.updateWriting(lists.get(0).getGroup_writing_no(),groupInfo.getGroup_no(),wirtingTitle.getText(),wirtingContent.getText());
					if(cnt != -1) {
						JLabel paneMessage_update = new JLabel("수정되었습니다.");
						paneMessage_update.setFont(st.neo_R.deriveFont((float)12));
						paneMessage_update.setForeground(st.inputBlack);
						JOptionPane.showMessageDialog(null, paneMessage_update,"게시글 수정",JOptionPane.PLAIN_MESSAGE);
						
						if(groupListForm != null) {
							groupForm.setVisible(false);
						}
						Group_content gc = new Group_content(mf_g,userInfo,groupInfo.getGroup_no());
					}else {
						System.out.println("수정 실패");
					}
				}else {
					JLabel paneMessage_update = new JLabel("제목을 8글자이하 내용은 1000byte를 초과하지 않도록 입력해주세요.");
					paneMessage_update.setFont(st.neo_R.deriveFont((float)12));
					paneMessage_update.setForeground(st.inputBlack);
					JOptionPane.showMessageDialog(null, paneMessage_update,"게시글 수정",JOptionPane.PLAIN_MESSAGE);
				}
				
			}else if(obj == btnDeleteWriting) {
				JLabel paneMessage = new JLabel("게시글을 삭제하시겠습니까?");
				paneMessage.setFont(st.neo_R.deriveFont((float)12));
				paneMessage.setForeground(st.inputBlack);
				int yesOrNo = JOptionPane.showConfirmDialog(null, paneMessage,"게시글 삭제",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);

				if(yesOrNo == 0) {
					int cnt = gJoinDao.deleteWriting(lists.get(0).getGroup_writing_no(),groupInfo.getGroup_no());
					if(cnt != -1) {
						JLabel paneMessage_update = new JLabel("삭제되었습니다.");
						paneMessage_update.setFont(st.neo_R.deriveFont((float)12));
						paneMessage_update.setForeground(st.inputBlack);
						JOptionPane.showMessageDialog(null, paneMessage_update,"게시글 삭제",JOptionPane.PLAIN_MESSAGE);

						if(groupListForm != null) {
							groupForm.setVisible(false);
						}
						Group_content gc = new Group_content(mf_g,userInfo,groupInfo.getGroup_no());
					}else {
						System.out.println("삭제 실패");
					}
				}

			}else if(obj == btnInsertWriting) {
				createInsertWriting();
			}else if(obj == btnInsert) {
				System.out.println(userInfo.getNo());
				int cnt = gJoinDao.InsertWriting(groupInfo.getGroup_no(),wirtingTitle.getText(),wirtingContent.getText(),userInfo.getNo());
				if(cnt != -1 && cnt != 0) {
					JLabel paneMessage = new JLabel("등록되었습니다.");
					paneMessage.setFont(st.neo_R.deriveFont((float)12));
					paneMessage.setForeground(st.inputBlack);
					JOptionPane.showMessageDialog(null, paneMessage,"게시글 등록",JOptionPane.PLAIN_MESSAGE);

					if(groupListForm != null) {
						groupForm.setVisible(false);
					}
					Group_content gc = new Group_content(mf_g,userInfo,groupInfo.getGroup_no());
				}else {
					System.out.println("등록 실패");
				}
			}
			
		}
		
	}
	
	// 키보드 이벤트 //
	class keyHandler extends KeyAdapter{

		public void keyTyped(KeyEvent e) {
			Object obj = e.getSource();
			if(obj == wirtingContent) {
				if(wirtingContent.getText().getBytes().length>=1000) {
					e.consume();
				}
			}
		}

		public void keyReleased(KeyEvent e){

			Object obj = e.getSource();

			// Input 박스 //
			if(obj == wirtingContent) {

				byteShow();
				
			}


		}


	}
	
	// 포커스 이벤트 //
	class focusHandler extends FocusAdapter{
		
		public void focusGained(FocusEvent e) {
			
			Object obj = e.getSource();
			
			if(obj == wirtingTitle) {
				if(wirtingTitle.getText().equals("제목을 입력해주세요.")) {
					wirtingTitle.setText("");
					wirtingTitle.setForeground(st.inputBlack);
				}
			}else if(obj == wirtingContent){
				if(wirtingContent.getText().equals("내용을 입력해주세요.")) {
					wirtingContent.setText("");
					wirtingContent.setForeground(st.inputBlack);
				}
			}
				
		}
		
		public void focusLost(FocusEvent e) {
			
			Object obj = e.getSource();
			
			if(obj == wirtingTitle) {
				if(wirtingTitle.getText().equals("")) {
					wirtingTitle.setText("제목을 입력해주세요.");
					wirtingTitle.setForeground(st.inputGray);
				}
			}else if(obj == wirtingContent){
				if(wirtingContent.getText().equals("")) {
					wirtingContent.setText("내용을 입력해주세요.");
					wirtingContent.setForeground(st.inputGray);
				}
			}
			
		}
		
	}
	private void byteShow() {
		byteW = wirtingContent.getText().getBytes().length;
		wirtingLength.setText(byteW+"/1000 byte");		
	}
	
}
