import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DB_SD.friendJoin_DAO;
import DB_SD.friendJoin_DTO;
import DB_SD.friendSD_DAO;

public class friend_content {
	
	// content area //
	private JPanel friendContentForm;
	// content //
	private Object[][] rowData ;
	private JTable table;
	private JScrollPane scrollPane;
	// 참조 클래스 //
    Style st = new Style();
    UserSD_DAO userDao = new UserSD_DAO();
    UserSD_DTO userInfo = new UserSD_DTO();
    MainFrame_friend mainFrame_friend;
    private JPanel friendForm;
    ArrayList<friendJoin_DTO> lists = null;
    friendJoin_DAO fJoinDao = new friendJoin_DAO();
    friendSD_DAO friendDao = new friendSD_DAO();
	
	public friend_content() {
	
	}

	public friend_content(MainFrame_friend mf_f, UserSD_DTO userInfo, String menu) {

		this.friendForm = mf_f.friendForm;
		this.mainFrame_friend = mf_f;
		this.userInfo = userInfo;
		compose(userInfo,menu);
		setEvent();
		st.composeJOptionPane();
		
		// 껏다 켜야 제데로 출력됨
		//friendForm.setVisible(false);
		//friendForm.setVisible(true);
	
	}

	private void setEvent() {
		try {
			table.addMouseListener(new MouseHandler());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
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
		}else if(menu.equals(mainFrame_friend.getMenu(3))) {
			// 테이블 짜기 //
			createTableTo();
		}else if(menu.equals(mainFrame_friend.getMenu(4))) {
			// 테이블 짜기 //
			createTableFrom();
			
		}
	}
	
	private void createTableTo() {

		lists = fJoinDao.getBeforeFriend(userInfo.getNo(),"user_no","friend_no");
		Object[] columnNames = {"이름","취소"};
		rowData = new Object[lists.size()][2];
		freindData(3); 
		
		// 테이블 기본값 설정(여기서 해야함) 및 스크롤에 올리기 //
		DefaultTableModel model = tableProtect(rowData,columnNames);
		table = new JTable(model) {
			public Class<?> getColumnClass(int column)  {
                   // row - JTable에 입력된 2차원 배열의  행에 속한다면
                   // 모든 컬럼을 입력된 형으로  반환한다.
                   
                   // 다시말해, 어떤 행이든 간에 입력된  column의 class를 반환하도록 한 것
                   return getValueAt(0,  column).getClass();
              }
		};
		table.setTableHeader(null); // 테이블 헤더 지우기
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 0, 766,410);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		friendContentForm.add(scrollPane);
		
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
		DefaultTableModel model = tableProtect(rowData,columnNames);
		table = new JTable(model) {
			public Class<?> getColumnClass(int column) throws NullPointerException {
                   // row - JTable에 입력된 2차원 배열의  행에 속한다면
                   // 모든 컬럼을 입력된 형으로  반환한다.
                   
                   // 다시말해, 어떤 행이든 간에 입력된  column의 class를 반환하도록 한 것
                   return getValueAt(0,  column).getClass();
              }
		};
		table.setTableHeader(null); // 테이블 헤더 지우기
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 0, 766,410);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		friendContentForm.add(scrollPane);
		
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
		DefaultTableModel model = tableProtect(rowData,columnNames);
		table = new JTable(model) {
			public Class<?> getColumnClass(int column)  {
                   // row - JTable에 입력된 2차원 배열의  행에 속한다면
                   // 모든 컬럼을 입력된 형으로  반환한다.
                   
                   // 다시말해, 어떤 행이든 간에 입력된  column의 class를 반환하도록 한 것
                   return getValueAt(0,  column).getClass();
              }
		};
		table.setTableHeader(null); // 테이블 헤더 지우기
		
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(st.inputWhite);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 0, 766,410);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		friendContentForm.add(scrollPane);
		
		// 테이블 상세 설정 //
		tableStyle(table); // 기본 테이블 스타일 설정
		
		table.getColumn("이름").setPreferredWidth(120); // 테이블 내 컨텐츠 행길이 조정
		table.getColumn("생일").setPreferredWidth(550);
		table.getColumn("삭제").setPreferredWidth(10);
		
	}

	private DefaultTableModel tableProtect(Object[][] rowData, Object[] columnNames) {
		// Jtable 내용 편집 막기

		DefaultTableModel model = new DefaultTableModel(rowData,columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
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

	public void test(String menu) { // 이전에 사용한 페이지 끄기
		
		friendContentForm.setVisible(false);
		
	}

	private void freindData(int num) {
		
		if(num == 1) {
			ImageIcon Icon = new ImageIcon("Project_SD/image/icon/delete_user_icon.png");
			int j=0;
			for(int i=0;i<lists.size();i++) {
				rowData[i][j++] = lists.get(i).getName()+" #"+lists.get(i).getNo();
				rowData[i][j++] = lists.get(i).getBirth();
				rowData[i][j++] = Icon;
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
				
				//Object obj = e.getSource();
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				String tableValue = String.valueOf(table.getValueAt(row,col));
				
				String icon = "Project_SD/image/icon/delete_user_icon.png";
				String friend = String.valueOf(table.getValueAt(row,0));
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
							
							createTableList();
						}
					}// 삭제
				}else if(tableValue.equals("수락")) {
					int cnt = friendDao.updateFriend(userInfo.getNo(),friendNo);
					if(cnt != -1 && cnt != 0) {
						createTableFrom();
					}
				}else if(tableValue.equals("거절")) {
					int cnt = friendDao.deleteBeforeFriend(userInfo.getNo(),friendNo);
					if(cnt != -1 && cnt != 0) {
						createTableFrom();
					}
				}else if(tableValue.equals("취소")) {
					int cnt = friendDao.deleteBeforeFriend(friendNo,userInfo.getNo());
					if(cnt != -1 && cnt != 0) {
						createTableTo();
					}
				}
				
			}


			
		}
	
	
}
