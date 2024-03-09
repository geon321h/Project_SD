import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Style {

    Color mainColor = new Color(156, 93, 71);
    Color mainColor_shades = new Color(117, 70, 53);
    Color lightGray = new Color(211, 211, 211);
    Color backgroundGray = new Color(235, 235, 235);
    Color inputWhite = new Color(255, 255, 255);
    Color inputGray = new Color(128, 128, 128);
    Color DimGray = new Color(61, 61, 61);
    Color inputBlack = new Color(55, 55, 55);
    Color menuBlack = new Color(20, 12, 9);
    
    Color mainColor_thin = new Color(206, 115, 56);
    Color headerColor = new Color(66, 46, 45);
    

    Font neo_H = new Font("나눔스퀘어 네오 Heavy", Font.PLAIN, 16);
    Font neo_EB = new Font("나눔스퀘어 네오 ExtraBold", Font.PLAIN, 16);
    Font neo_B = new Font("나눔스퀘어 네오 Bold", Font.PLAIN, 14);
    Font neo_R = new Font("나눔스퀘어 네오 Regular", Font.PLAIN, 14);
    Font neo_L = new Font("나눔스퀘어 네오 light", Font.PLAIN, 14);
    
	public void composeJOptionPane() {

		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", Color.white);
		UIManager.put("Button.foreground", inputBlack);
		UIManager.put("Button.font", neo_B.deriveFont((float)12));
		
	}

    // Font noto_M = new Font("Noto Sans KR Medium", Font.PLAIN, 14);
    // Font noto_B = new Font("Noto Sans KR Bold", Font.PLAIN, 14);
    
}
