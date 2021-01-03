package cezaSistemi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Font;

public class ERROR {

	private JFrame frmHata;

	public static void error(String hataMesaji) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ERROR window = new ERROR(hataMesaji);
					window.frmHata.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ERROR(String hataMesaji) {
		initialize(hataMesaji);
	}

	
	private void initialize(String hataMesaji) {
		frmHata = new JFrame();
		frmHata.getContentPane().setBackground(new Color(255, 255, 204));
		frmHata.setType(Type.POPUP);
		frmHata.setTitle("B\u0130LG\u0130LEND\u0130RME");
		frmHata.setBounds(100, 100, 414, 185);
		frmHata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHata.getContentPane().setLayout(null);
		
		JLabel hataLbl = new JLabel(hataMesaji);
		hataLbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		hataLbl.setHorizontalAlignment(SwingConstants.CENTER);
		hataLbl.setBounds(0, 0, 398, 146);
		frmHata.getContentPane().add(hataLbl);
	}
}
