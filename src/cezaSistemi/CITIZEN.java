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

public class CITIZEN {

	private JFrame frmTrafikCezaSistemi;
	static JTable cezaSorTable;
	static JComboBox cezaOdeBox;
	static JTextPane borcSorText;
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public CITIZEN() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrafikCezaSistemi = new JFrame();
		frmTrafikCezaSistemi.setTitle("Trafik Ceza Sistemi");
		frmTrafikCezaSistemi.setBounds(100, 100, 658, 535);
		frmTrafikCezaSistemi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrafikCezaSistemi.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 642, 496);
		frmTrafikCezaSistemi.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Sorgulama", null, panel_1, null);
		
		JButton cezaSorButton = new JButton("Ceza Sorgula");
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
		cezaSorButton.setBounds(230, 63, 169, 25);
		panel_1.add(cezaSorButton);
		
		JButton borcSorButton = new JButton("Borç Sorgula");
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
		borcSorButton.setBounds(450, 28, 177, 24);
		panel_1.add(borcSorButton);
		
		JScrollPane cezaSorScroll = new JScrollPane();
		cezaSorScroll.setBounds(10, 99, 617, 358);
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
		borcSorText.setEditable(false);
		borcSorText.setBounds(450, 63, 177, 25);
		panel_1.add(borcSorText);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		tabbedPane.addTab("Ceza Ödeme", null, panel_4, null);
		
		JLabel cezaOdeLbl = new JLabel("\u00D6demek \u0130stedi\u011Finiz Ceza");
		cezaOdeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cezaOdeLbl.setBounds(180, 47, 246, 22);
		panel_4.add(cezaOdeLbl);
		
		cezaOdeBox = new JComboBox();
		cezaOdeBox.setBounds(180, 80, 246, 22);
		panel_4.add(cezaOdeBox);
		
		JButton cezaOdeButton = new JButton("\u00D6de");
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
		cezaOdeButton.setBounds(238, 113, 133, 22);
		panel_4.add(cezaOdeButton);
	}
}
