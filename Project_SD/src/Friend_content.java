import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB_SD.FriendJoin_DAO;
import DB_SD.FriendJoin_DTO;
import DB_SD.FriendSD_DAO;

public class Friend_content {

	// content area //
	private JPanel friendContentForm;
	// content //
	private Object[][] rowData ;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField searchUser;
	// 참조 클래스 //
	Style st = new Style();
	UserSD_DAO userDao = new UserSD_DAO();
	UserSD_DTO userInfo = new UserSD_DTO();
	MainFrame_friend mainFrame_friend;
	private JPanel friendForm;
	ArrayList<FriendJoin_DTO> lists = null;
	FriendJoin_DAO fJoinDao = new FriendJoin_DAO();
	FriendSD_DAO friendDao = new FriendSD_DAO();
	DefaultTableModel model;
	
	public Friend_content() {

	}

	public Friend_content(MainFrame_friend mf_f, UserSD_DTO userInfo, String menu) {

		this.friendForm = mf_f.friendForm;
		this.mainFrame_friend = mf_f;
		this.userInfo = userInfo;
		compose(userInfo,menu);
		st.composeJOptionPane();

	}

	private void compose(UserSD_DTO userInfo, String menu) {

		mainFrame_friend.renameTitle(menu); // 제목 변경

		friendContentForm = new JPanel();
		friendContentForm.setBounds(50,200,749,410);
		friendContentForm.setLayout(null);
		friendForm.add(friendContentForm);

		if(menu.equals(mainFrame_friend.getMenu(1))) {
			// 테이블 짜기 //
			createTableList();
		}else if(menu.equals(mainFrame_friend.getMenu(2))) {
			// 검색창 짜기 //
			JPanel searchUserForm = new JPanel();
			searchUserForm.setBounds(225,0,300,56);
			searchUserForm.setBorder(new LineBorder(st.mainColor,2,true));
			searchUserForm.setLayout(null);
			friendContentForm.add(searchUserForm);

			JLabel search_Icon = new JLabel(" ", JLabel.CENTER);
			ImageIcon Icon = new ImageIcon("image/icon/search_icon.png");
			search_Icon.setIcon(Icon);
			search_Icon.setBounds(13, 11,34,34);
			searchUserForm.add(search_Icon);

			searchUser = new JTextField("");
			searchUser.setBounds(47, 2,251,52);
			searchUser.setBackground(st.inputWhite);
			searchUser.setHorizontalAlignment(JTextField.RIGHT);
			searchUser.setBorder(BorderFactory.createEmptyBorder(5, 20, 4, 20));
			searchUser.setForeground(st.inputBlack);
			searchUser.setFont(st.neo_R.deriveFont((float)16));
			searchUserForm.add(searchUser);
			searchUser.addKeyListener(new keyHandler());
			// 테이블 짜기 //
			createTableAdd("%%");
		}else if(menu.equals(mainFrame_friend.getMenu(3))) {
			// 테이블 짜기 //
			createTableTo();
		}else if(menu.equals(mainFrame_friend.getMenu(4))) {
			// 테이블 짜기 //
			createTableFrom();
		}
		
	}

	private void createTableAdd(String searchName) {

		lists = fJoinDao.getBeforeFriendByName(userInfo.getNo(),searchName);
		Object[] columnNames = {"이름","친구신청"};
		rowData = new Object[lists.size()][2];
		freindData(2); 
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);

		JPanel searchTableForm = new JPanel();
		searchTableForm.setBounds(100, 100,566,310);
		searchTableForm.setBorder(new LineBorder(st.mainColor,0,true));
		searchTableForm.setLayout(null);
		friendContentForm.add(searchTableForm);

		JPanel line = new JPanel();
		line.setBounds(0, 0,586,1);
		line.setBackground(st.inputBlack);
		searchTableForm.add(line);
		table.setTableHeader(null); // 테이블 헤더 지우기
		table.addMouseListener(new MouseHandler());

		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 1, 586,308);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setBackground(st.inputWhite);
		searchTableForm.add(scrollPane);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(480); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("친구신청").setPreferredWidth(80);

		line = new JPanel();
		line.setBackground(st.inputBlack);
		line.setBounds(0, 309,586,1);
		searchTableForm.add(line);

	}

	private void createTableAdd_after(String searchName) {

		lists = fJoinDao.getBeforeFriendByName(userInfo.getNo(),searchName);
		Object[] columnNames = {"이름","친구신청"};
		rowData = new Object[lists.size()][2];
		freindData(2); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		JPanel searchTableForm = new JPanel();
		searchTableForm.setBounds(100, 100,586,310);
		searchTableForm.setBorder(new LineBorder(st.mainColor,0,true));
		searchTableForm.setLayout(null);
		friendContentForm.add(searchTableForm);
		table.setTableHeader(null); // 테이블 헤더 지우기
		table.addMouseListener(new MouseHandler());
		scrollPane.setViewportView(table);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(480); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("친구신청").setPreferredWidth(80);

	}

	private void createTableTo() {

		lists = fJoinDao.getBeforeFriend(userInfo.getNo(),"user_no","friend_no");
		Object[] columnNames = {"이름","취소"};
		rowData = new Object[lists.size()][2];
		freindData(3); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.addMouseListener(new MouseHandler());
		table.setTableHeader(null); // 테이블 헤더 지우기

		JPanel line = new JPanel();
		line.setBounds(0, 0,766,1);
		line.setBackground(st.inputBlack);
		friendContentForm.add(line);

		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 1, 766,408);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		friendContentForm.add(scrollPane);

		line = new JPanel();
		line.setBounds(0, 409,766,1);
		line.setBackground(st.inputBlack);
		friendContentForm.add(line);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(720); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("취소").setPreferredWidth(40);

	}

	private void createTableTo_after() {

		lists = fJoinDao.getBeforeFriend(userInfo.getNo(),"user_no","friend_no");
		Object[] columnNames = {"이름","취소"};
		rowData = new Object[lists.size()][2];
		freindData(3); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.setTableHeader(null); // 테이블 헤더 지우기
		table.addMouseListener(new MouseHandler());
		scrollPane.setViewportView(table);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(720); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("취소").setPreferredWidth(40);

	}

	private void createTableFrom() {

		lists = fJoinDao.getBeforeFriend(userInfo.getNo(),"friend_no","user_no");
		Object[] columnNames = {"이름","수락"," ","거절"};
		rowData = new Object[lists.size()][4];
		freindData(4); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.addMouseListener(new MouseHandler());
		table.setTableHeader(null); // 테이블 헤더 지우기

		JPanel line = new JPanel();
		line.setBounds(0, 0,766,1);
		line.setBackground(st.inputBlack);
		friendContentForm.add(line);

		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 1, 766,408);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		friendContentForm.add(scrollPane);

		line = new JPanel();
		line.setBounds(0, 409,766,1);
		line.setBackground(st.inputBlack);
		friendContentForm.add(line);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(660); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("수락").setPreferredWidth(40);
		table.getColumn(" ").setPreferredWidth(20);
		table.getColumn("거절").setPreferredWidth(40);

	}

	private void createTableFrom_after() {

		lists = fJoinDao.getBeforeFriend(userInfo.getNo(),"friend_no","user_no");
		Object[] columnNames = {"이름","수락"," ","거절"};
		rowData = new Object[lists.size()][4];
		freindData(4); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.setTableHeader(null); // 테이블 헤더 지우기
		table.addMouseListener(new MouseHandler());
		scrollPane.setViewportView(table);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(660); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("수락").setPreferredWidth(40);
		table.getColumn(" ").setPreferredWidth(20);
		table.getColumn("거절").setPreferredWidth(40);

	}

	private void createTableList() {

		lists = fJoinDao.getMyFriend(userInfo.getNo());
		Object[] columnNames = {"이름","생일","삭제"};
		rowData = new Object[lists.size()][3];
		freindData(1); 
		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.addMouseListener(new MouseHandler());
		table.setTableHeader(null); // 테이블 헤더 지우기

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정

		table.getColumn("이름").setPreferredWidth(120); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("생일").setPreferredWidth(550);
		table.getColumn("삭제").setPreferredWidth(10);

		JPanel line = new JPanel();
		line.setBounds(0, 0,766,1);
		line.setBackground(st.inputBlack);
		friendContentForm.add(line);

		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 1, 766,408);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		friendContentForm.add(scrollPane);

		line = new JPanel();
		line.setBounds(0, 409,766,1);
		line.setBackground(st.inputBlack);
		friendContentForm.add(line);

	}

	private void createTableList_after() {

		lists = fJoinDao.getMyFriend(userInfo.getNo());
		Object[] columnNames = {"이름","생일","삭제"};
		rowData = new Object[lists.size()][3];
		freindData(1); 
		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.setTableHeader(null); // 테이블 헤더 지우기
		table.addMouseListener(new MouseHandler());
		scrollPane.setViewportView(table);
		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.getColumn("이름").setPreferredWidth(120); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("생일").setPreferredWidth(550);
		table.getColumn("삭제").setPreferredWidth(10);

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

	public void closeContent() { // 이전에 사용한 페이지 끄기

		friendContentForm.setVisible(false);

	}

	private void freindData(int num) {

		if(num == 1) {
			ImageIcon Icon = new ImageIcon("image/icon/delete_user_icon.png");
			int j=0;
			for(int i=0;i<lists.size();i++) {
				rowData[i][j++] = lists.get(i).getName()+" #"+lists.get(i).getNo();
				rowData[i][j++] = lists.get(i).getBirth();
				rowData[i][j++] = Icon;
				j = 0;
			}
		}else if(num == 2) {
			int j=0;
			for(int i=0;i<lists.size();i++) {
				rowData[i][j++] = lists.get(i).getName()+" #"+lists.get(i).getNo();
				rowData[i][j++] = "친구 신청";
				j = 0;
			}
		}else if(num == 3) {
			int j=0;
			for(int i=0;i<lists.size();i++) {
				rowData[i][j++] = lists.get(i).getName()+" #"+lists.get(i).getNo();
				rowData[i][j++] = "취소";
				j = 0;
			}
		}else if(num == 4) {
			int j=0;
			for(int i=0;i<lists.size();i++) {
				rowData[i][j++] = lists.get(i).getName()+" #"+lists.get(i).getNo();
				rowData[i][j++] = "수락";
				rowData[i][j++] = " ";
				rowData[i][j++] = "거절";
				j = 0;
			}
		}


	}

	// 마우스 이벤트 //
	class MouseHandler extends MouseAdapter{

		public void mouseClicked(MouseEvent e) {

			int row = -1;
			String tableValue = null;
			String friend = null;

			try {
				row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				friend = String.valueOf(table.getValueAt(row,0));
				tableValue = String.valueOf(table.getValueAt(row,col));

				String icon = "image/icon/delete_user_icon.png";
				int friendNo = Integer.parseInt(friend.substring(friend.indexOf("#")+1));
				friend = friend.substring(0,friend.indexOf(" "));

				if(tableValue.equals(icon)) {
					JLabel paneMessage = new JLabel("친구 "+friend+"님을 삭제하시겠어요?");
					paneMessage.setFont(st.neo_R.deriveFont((float)12));
					paneMessage.setForeground(st.inputBlack);

					int yesOrNo = JOptionPane.showConfirmDialog(null, paneMessage,"친구 삭제",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);

					if(yesOrNo == 0) {
						int cnt = friendDao.deleteFriend(userInfo.getNo(),friendNo);
						if(cnt != -1 && cnt != 0) {
							JLabel paneMessage_delete = new JLabel("삭제 되었습니다.");
							paneMessage_delete.setFont(st.neo_R.deriveFont((float)12));
							paneMessage_delete.setForeground(st.inputBlack);
							JOptionPane.showMessageDialog(null, paneMessage_delete,"친구 삭제 완료",JOptionPane.PLAIN_MESSAGE);
							createTableList_after();
						}
					}// 삭제
				}else if(tableValue.equals("수락")) {
					int cnt = friendDao.updateFriend(userInfo.getNo(),friendNo);
					if(cnt != -1 && cnt != 0) {
						createTableFrom_after();
					}
				}else if(tableValue.equals("거절")) {
					int cnt = friendDao.deleteBeforeFriend(userInfo.getNo(),friendNo);
					if(cnt != -1 && cnt != 0) {
						createTableFrom_after();
					}
				}else if(tableValue.equals("취소")) {
					int cnt = friendDao.deleteBeforeFriend(friendNo,userInfo.getNo());
					if(cnt != -1 && cnt != 0) {
						createTableTo_after();
					}
				}else if(tableValue.equals("친구 신청")) {
					int cnt = friendDao.insertFriend(userInfo.getNo(),friendNo);
					String searchName = "%"+searchUser.getText()+"%";
					if(cnt != -1 && cnt != 0) {
						createTableAdd_after(searchName);
					}
				}

			} catch (ArrayIndexOutOfBoundsException e2) {
			}
		}



	}

	// 키보드 이벤트 //
	class keyHandler extends KeyAdapter{

		public void keyTyped(KeyEvent e) {
			Object obj = e.getSource();
			if(obj == searchUser) {
				if(searchUser.getText().length()>=6) {
					e.consume();
				}
			}
		}

		public void keyReleased(KeyEvent e){

			Object obj = e.getSource();

			// Input 박스 //
			if(obj == searchUser) {
				String searchName = "%"+searchUser.getText()+"%";
				System.out.println(searchName);
				createTableAdd_after(searchName);

			}


		}

	}


}
