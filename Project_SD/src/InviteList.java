import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DB_SD.GroupJoin_DAO;
import DB_SD.GroupJoin_DTO;
import DB_SD.Groupsd_user_list_DAO;

public class InviteList extends JFrame{

	// content //
	private JLabel title;
	private JScrollPane scrollPane;
	private JPanel listForm;
	private JTable table;
	private Object[][] rowData ;
	// 참조 클래스 //
	Style st = new Style();
	Sign_UpFrame sf = null;
	DefaultTableModel model;
	GroupJoin_DAO gJoinDao = new GroupJoin_DAO();
	UserSD_DAO userDao = new UserSD_DAO();
	Groupsd_user_list_DAO groupUserDao = new Groupsd_user_list_DAO();
	MainFrame_group mainFrame_group;
	ArrayList<GroupJoin_DTO> lists;
	ArrayList<UserSD_DTO> userLists;
	int userNo = 0;
	private int no;
	
	public InviteList(String title, int no, MainFrame_group mainFrame_group) {
		super(title);
		this.no = no;
		this.mainFrame_group = mainFrame_group;
		userNo =  mainFrame_group.userInfo.getNo();
		compose(title); // 화면 구성
		setEvent();
		
		setSize(400,600);
		setVisible(true);
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 창 생성위치 설정
	}
	
	private void setEvent() {

		
	}

	private void compose(String titleInput) {

		JPanel display = new JPanel();
		display.setBackground(Color.white);
		display.setLayout(null);
		this.add(display);
		
		// title //
		
		listForm = new JPanel();
		listForm.setBackground(Color.white);
		listForm.setBounds(40, 120,300,500);
		listForm.setLayout(null);
		display.add(listForm);
		
		if(titleInput.equals("받은 초대 목록")) {

			title = new JLabel(titleInput);
			title.setBounds(120, 40,150,50);
			title.setFont(st.neo_EB.deriveFont((float)24));
			title.setForeground(st.inputBlack);
			display.add(title);
			createTable();
			
		}else if(titleInput.equals("초대 하기")) {
			
			title = new JLabel(titleInput);
			title.setBounds(140, 40,150,50);
			title.setFont(st.neo_EB.deriveFont((float)24));
			title.setForeground(st.inputBlack);
			display.add(title);
			createTable2();
			
		}
		
		
	}
	
	private void createTable() {

		lists = gJoinDao.getInviteGroup(no);
		Object[] columnNames = {"이름","수락"," ","거절"};
		rowData = new Object[lists.size()][4];
		groupData(); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.addMouseListener(new MouseHandler());
		table.setTableHeader(null); // 테이블 헤더 지우기

		JPanel Line = new JPanel();
		Line.setBounds(0, 0,300,1);
		Line.setBackground(st.inputBlack);
		listForm.add(Line);
		
		Line = new JPanel();
		Line.setBounds(0, 380,300,1);
		Line.setBackground(st.inputBlack);
		listForm.add(Line);
		
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 11, 320,358);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listForm.add(scrollPane);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(210); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("수락").setPreferredWidth(40);
		table.getColumn(" ").setPreferredWidth(10);
		table.getColumn("거절").setPreferredWidth(40);

	}

	private void createTable_after() {

		lists = gJoinDao.getInviteGroup(no);
		Object[] columnNames = {"이름","수락"," ","거절"};
		rowData = new Object[lists.size()][4];
		groupData(); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.setTableHeader(null); // 테이블 헤더 지우기
		table.addMouseListener(new MouseHandler());
		scrollPane.setViewportView(table);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(210); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("수락").setPreferredWidth(40);
		table.getColumn(" ").setPreferredWidth(10);
		table.getColumn("거절").setPreferredWidth(40);

	}
	
	private void createTable2() {

		lists = gJoinDao.getInviteFriend(no,userNo);
		userLists = new ArrayList<>();
		for(int i=0;i<lists.size();i++) {
			userLists.add(userDao.getUserById(lists.get(i).getGroup_no()));
		}
		Object[] columnNames = {"이름","초대"};
		rowData = new Object[lists.size()][2];
		freindData(); 

		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		model = tableProtect(rowData,columnNames);
		table = new JTable(model);
		table.addMouseListener(new MouseHandler());
		table.setTableHeader(null); // 테이블 헤더 지우기

		JPanel Line = new JPanel();
		Line.setBounds(0, 0,300,1);
		Line.setBackground(st.inputBlack);
		listForm.add(Line);
		
		Line = new JPanel();
		Line.setBounds(0, 380,300,1);
		Line.setBackground(st.inputBlack);
		listForm.add(Line);
		
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 11, 320,358);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listForm.add(scrollPane);

		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		table.setSelectionForeground(st.inputBlack);

		table.getColumn("이름").setPreferredWidth(260); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("초대").setPreferredWidth(40);
	}

	private void createTable2_after() {

		lists = gJoinDao.getInviteFriend(no,userNo);
		Object[] columnNames = {"이름","초대"};
		rowData = new Object[lists.size()][2];
		
		userLists = new ArrayList<>();
		for(int i=0;i<lists.size();i++) {
			userLists.add(userDao.getUserById(lists.get(i).getGroup_no()));
		}
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
		table.getColumn("초대").setPreferredWidth(40);

	}
	
	private void groupData() {

		int j=0;
		for(int i=0;i<lists.size();i++) {
			rowData[i][j++] = lists.get(i).getGroup_name()+" #"+lists.get(i).getGroup_no();
			rowData[i][j++] = "수락";
			rowData[i][j++] = " ";
			rowData[i][j++] = "거절";
			j = 0;
		}
		
	}
	
	private void freindData() {

		int j=0;
		for(int i=0;i<lists.size();i++) {
			rowData[i][j++] = userLists.get(i).getName()+" #"+userLists.get(i).getNo();
			rowData[i][j++] = "초대";
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
			
			try {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				String group = String.valueOf(table.getValueAt(row,0));
				String tableValue = String.valueOf(table.getValueAt(row,col));
				int groupNo = Integer.parseInt(group.substring(group.indexOf("#")+1));
				
				if(tableValue.equals("수락")) {
					int cnt = groupUserDao.getMyGroupCount(no);
					if(cnt<12) {
						cnt = groupUserDao.updateGroup(no,groupNo);
						if(cnt != -1 && cnt != 0) {
							createTable_after();
							mainFrame_group.renewalContent();
						}
					}else {
						JLabel paneMessage_guide = new JLabel("그룹은 12개까지 포함될 수 있습니다.");
						paneMessage_guide.setFont(st.neo_R.deriveFont((float)14));
						paneMessage_guide.setForeground(st.inputBlack);
						JOptionPane.showMessageDialog(null, paneMessage_guide,"그룹 생성",JOptionPane.PLAIN_MESSAGE);
					}
					
				}else if(tableValue.equals("거절")) {
					int cnt = groupUserDao.deleteGroup(no,groupNo);
					if(cnt != -1 && cnt != 0) {
						createTable_after();
					}
				}else if(tableValue.equals("초대")) {
					int cnt = groupUserDao.insertGroup(groupNo,no);
					System.out.println(1);
					if(cnt != -1 && cnt != 0) {
						createTable2_after();
					}
				}
				
			} catch (ArrayIndexOutOfBoundsException e2) {
			}
		}
		
	}
	
	
}
