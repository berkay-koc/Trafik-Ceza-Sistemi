package cezaSistemi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
		frmHata.setTitle("HATA!");
		frmHata.setBounds(100, 100, 500, 300);
		frmHata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHata.getContentPane().setLayout(null);
		
		JLabel hataLbl = new JLabel(hataMesaji);
		hataLbl.setHorizontalAlignment(SwingConstants.CENTER);
		hataLbl.setBounds(10, 11, 464, 239);
		frmHata.getContentPane().add(hataLbl);
	}
}
