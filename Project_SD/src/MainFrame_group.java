import javax.swing.JPanel;

public class MainFrame_group {

	// content area //
	private JPanel contentForm = null;
	// 참조 클래스 //
    Style st = new Style();
    UserSD_DAO userDao = new UserSD_DAO();
    UserSD_DTO userInfo = new UserSD_DTO();
    MainFrame mainFrame = null;
    JPanel display = null;
	
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
		
	}

	private void compose(JPanel display, UserSD_DTO userInfo) {

		/*** container and header ***/
		contentForm = new JPanel();
		contentForm.setBackground(st.backgroundGray);
		contentForm.setSize(980,800);
		contentForm.setLayout(null);
		contentForm.setLocation(220, 0);
		display.add(contentForm);
		
	}
	
	
}
