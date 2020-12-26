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
	final String url = "jdbc:postgresql://loaclhost:5432/TCS";
	final String user = "postgres";
	final String password = "1234";
	
	private JFrame frmTrafikCezaSistemi;

	public static void main(String[] args) {
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

	private void connect(){ 
		try(Connection connection = DriverManager.getConnection(url,user,password);){
			if(connection != null){
				System.out.println("Connected!");
			}else{
		   System.out.println("Failed!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
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
				
			}
		});
		girisButton.setBounds(157, 171, 89, 23);
		frmTrafikCezaSistemi.getContentPane().add(girisButton);
	}
}
