import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class infoMessage extends JFrame {

	// 안내창 //
	private JLabel Message;
	private JButton btnCheck;
	
	// 참조 클래스 //
	Style st = new Style();
	Sign_UpFrame sf = null;
	
	public infoMessage(String title) {
		super(title);
		compose(); // 화면 구성
		setEvent();
		
		setSize(350,200);
		setVisible(true);
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 창 생성위치 설정
	}

	private void setEvent() {

		btnCheck.addActionListener(new ActionHandler());
		
	}

	private void compose() {

		JPanel display = new JPanel();
		display.setBackground(Color.white);
		display.setLayout(null);
		this.add(display);
		
		// 메세지 //
		Message = new JLabel("가입 성공");
		Message.setBounds(130, 30,150,50);
		Message.setFont(st.neo_R.deriveFont((float)18));
		Message.setForeground(st.inputBlack);
		display.add(Message);
		
		// 확인 버튼 //
		btnCheck = new JButton("확인");
		btnCheck.setBounds(140, 100,50,25);
		btnCheck.setBackground(st.mainColor);
		btnCheck.setBorder(new LineBorder(st.mainColor,1,true));
		btnCheck.setForeground(Color.white);
		btnCheck.setFont(st.neo_B);
		display.add(btnCheck);

		
	}
	
	class ActionHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			
			// Login 버튼 //
			if(obj == btnCheck) {
				
				setVisible(false);
				LoginFrame lf = new LoginFrame("Shared Diary : Login");
				
			}
			
		}

	}

}
