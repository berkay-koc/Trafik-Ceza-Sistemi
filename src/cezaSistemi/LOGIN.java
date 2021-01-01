package cezaSistemi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LOGIN {
	
	private JFrame frmTrafikCezaSistemi;

	public static void login() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGIN window = new LOGIN();
					window.frmTrafikCezaSistemi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	public LOGIN() {
		initialize();
	}
	
	private void initialize() {
		frmTrafikCezaSistemi = new JFrame();
		frmTrafikCezaSistemi.setTitle("Trafik Ceza Sistemi");
		frmTrafikCezaSistemi.setBounds(100, 100, 433, 388);
		frmTrafikCezaSistemi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrafikCezaSistemi.getContentPane().setLayout(null);
		
		JFormattedTextField tcknField = new JFormattedTextField();
		tcknField.setToolTipText("");
		tcknField.setBounds(115, 140, 180, 20);
		frmTrafikCezaSistemi.getContentPane().add(tcknField);
		
		JLabel tcknLabel = new JLabel("TC K\u0130ML\u0130K NUMARASI");
		tcknLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tcknLabel.setBounds(115, 115, 180, 14);
		frmTrafikCezaSistemi.getContentPane().add(tcknLabel);
		
		JButton girisButton = new JButton("Giri\u015F");
		girisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tckn = tcknField.getText();
				Queries.kimlikno = tcknField.getText();
				try {
					Queries.login_control(tckn);
					frmTrafikCezaSistemi.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		girisButton.setBounds(157, 171, 89, 23);
		frmTrafikCezaSistemi.getContentPane().add(girisButton);
	}
}
