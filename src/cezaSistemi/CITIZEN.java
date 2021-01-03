package cezaSistemi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;

public class CITIZEN {

	private JFrame frmTrafikCezaSistemi;
	static JTable cezaSorTable;
	static JComboBox cezaOdeBox;
	static JTextPane borcSorText;
	public static void citizen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CITIZEN window = new CITIZEN();
					Queries.insertBorclarToComboBox_Citizen();
					window.frmTrafikCezaSistemi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CITIZEN() {
		initialize();
	}

	private void initialize() {
		frmTrafikCezaSistemi = new JFrame();
		frmTrafikCezaSistemi.setTitle("Trafik Ceza Sistemi");
		frmTrafikCezaSistemi.setBounds(100, 100, 1050, 700);
		frmTrafikCezaSistemi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrafikCezaSistemi.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1034, 661);
		frmTrafikCezaSistemi.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setLayout(null);
		tabbedPane.addTab("Sorgulama", null, panel_1, null);
		
		JButton cezaSorButton = new JButton("Ceza Sorgula");
		cezaSorButton.setBackground(new Color(204, 255, 255));
		cezaSorButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cezaSorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cezaSorTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Ceza ID", "\u0130sim", "Soyisim", "Ceza Sebebi", "Ceza \u00DCcreti", "Kesilme Tarihi", "Son \u00D6deme Tarihi", "Plaka"
						}
					));
				try {
					Queries.cezaSorgula_Citizen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//cezaSorButton.setEnabled(false);
			}
			
		});
		cezaSorButton.setBounds(414, 58, 200, 30);
		panel_1.add(cezaSorButton);
		
		JButton borcSorButton = new JButton("Borç Sorgula");
		borcSorButton.setBackground(new Color(204, 255, 255));
		borcSorButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		borcSorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Queries.borcSorgula_Citizen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		borcSorButton.setBounds(819, 17, 200, 30);
		panel_1.add(borcSorButton);
		
		JScrollPane cezaSorScroll = new JScrollPane();
		cezaSorScroll.setBounds(10, 99, 1009, 523);
		panel_1.add(cezaSorScroll);
		
		cezaSorTable = new JTable();
		cezaSorTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ceza ID", "\u0130sim", "Soyisim", "Ceza Sebebi", "Ceza \u00DCcreti", "Kesilme Tarihi", "Son \u00D6deme Tarihi", "Plaka"
			}
		));
		cezaSorScroll.setViewportView(cezaSorTable);
		
		borcSorText = new JTextPane();
		borcSorText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		borcSorText.setBackground(new Color(204, 255, 204));
		borcSorText.setEditable(false);
		borcSorText.setBounds(869, 58, 150, 30);
		panel_1.add(borcSorText);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(153, 204, 255));
		panel_4.setLayout(null);
		tabbedPane.addTab("Ceza Ödeme", null, panel_4, null);
		
		JLabel cezaOdeLbl = new JLabel("\u00D6demek \u0130stedi\u011Finiz Ceza");
		cezaOdeLbl.setForeground(new Color(255, 255, 255));
		cezaOdeLbl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cezaOdeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cezaOdeLbl.setBounds(400, 200, 200, 40);
		panel_4.add(cezaOdeLbl);
		
		cezaOdeBox = new JComboBox();
		cezaOdeBox.setBounds(400, 250, 200, 40);
		panel_4.add(cezaOdeBox);
		
		JButton cezaOdeButton = new JButton("\u00D6de");
		cezaOdeButton.setForeground(new Color(255, 255, 255));
		cezaOdeButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cezaOdeButton.setBackground(new Color(51, 102, 51));
		cezaOdeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Queries.borcOde_Citizen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cezaOdeButton.setBounds(440, 310, 120, 40);
		panel_4.add(cezaOdeButton);
	}
}
