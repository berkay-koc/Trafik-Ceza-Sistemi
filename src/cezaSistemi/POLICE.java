package cezaSistemi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;

public class POLICE {

	JFrame frmTrafikCezaSistemi;
	static JTable yenilencezaTable;
	static JTable cezaTipTable;
	static JLabel borcSorField;
	static JFormattedTextField isimField;
	static JFormattedTextField soyisimField;
	static JFormattedTextField tcknField;
	static JFormattedTextField plakaField;
	static JComboBox aractipiBox;
	static JComboBox cezatipiBox;
	static String kimlikNo;
	static JComboBox cezaOdeBox;
	static JFormattedTextField tcknSorguField;
	static JTextField textField;
	static JTextField textField_1;
	
	public static void police() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POLICE window = new POLICE();
					window.frmTrafikCezaSistemi.setVisible(true);
					Queries.cezaTablosuInsert();
					Queries.insertCezalarToComboBox();
					Queries.insertBorclarToComboBox_Police();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public POLICE() {
		initialize();
		
	}

	
	private void initialize() {
		
		frmTrafikCezaSistemi = new JFrame();
		frmTrafikCezaSistemi.setTitle("Trafik Ceza Sistemi");
		frmTrafikCezaSistemi.setBounds(100, 100, 658, 535);
		frmTrafikCezaSistemi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrafikCezaSistemi.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 642, 496);
		frmTrafikCezaSistemi.getContentPane().add(tabbedPane);
		
		JPanel cezaKayit = new JPanel();
		tabbedPane.addTab("Yeni Ceza Kayd\u0131", null, cezaKayit, null);
		cezaKayit.setLayout(null);
		
		isimField = new JFormattedTextField();
		isimField.setBounds(240, 57, 180, 20);
		cezaKayit.add(isimField);
		
		soyisimField = new JFormattedTextField();
		soyisimField.setBounds(240, 114, 180, 20);
		cezaKayit.add(soyisimField);
		
		tcknField = new JFormattedTextField();
		tcknField.setBounds(240, 176, 180, 20);
		cezaKayit.add(tcknField);
		
		plakaField = new JFormattedTextField();
		plakaField.setBounds(240, 224, 180, 20);
		cezaKayit.add(plakaField);
		
		aractipiBox = new JComboBox();
		aractipiBox.setModel(new DefaultComboBoxModel(new String[] {"Araba", "Motorsiklet", "Kamyon", "T\u0131r"}));
		aractipiBox.setBounds(240, 281, 180, 20);
		cezaKayit.add(aractipiBox);
		
		cezatipiBox = new JComboBox();
		cezatipiBox.setBounds(240, 337, 180, 20);
		cezaKayit.add(cezatipiBox);
		
		JButton cezakayitButton = new JButton("Ekle");
		cezakayitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Queries.yeniCezaEkle();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cezakayitButton.setBounds(292, 393, 89, 23);
		cezaKayit.add(cezakayitButton);
		
		JLabel isimLbl = new JLabel("\u0130sim");
		isimLbl.setHorizontalAlignment(SwingConstants.CENTER);
		isimLbl.setBounds(240, 32, 180, 14);
		cezaKayit.add(isimLbl);
		
		JLabel soyisimLbl = new JLabel("Soyisim");
		soyisimLbl.setHorizontalAlignment(SwingConstants.CENTER);
		soyisimLbl.setBounds(240, 89, 180, 14);
		cezaKayit.add(soyisimLbl);
		
		JLabel tcknLbl = new JLabel("TC Kimlik Numaras\u0131");
		tcknLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tcknLbl.setBounds(240, 151, 180, 14);
		cezaKayit.add(tcknLbl);
		
		JLabel plakaLbl = new JLabel("Plaka");
		plakaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		plakaLbl.setBounds(240, 199, 180, 14);
		cezaKayit.add(plakaLbl);
		
		JLabel aractipLbl = new JLabel("Ara\u00E7 Tipi");
		aractipLbl.setHorizontalAlignment(SwingConstants.CENTER);
		aractipLbl.setBounds(240, 256, 180, 14);
		cezaKayit.add(aractipLbl);
		
		JLabel cezatipLbl = new JLabel("Ceza Sebebi");
		cezatipLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cezatipLbl.setBounds(240, 317, 180, 14);
		cezaKayit.add(cezatipLbl);
		
		
		JPanel sorgu = new JPanel();
		tabbedPane.addTab("Sorgulama", null, sorgu, null);
		sorgu.setLayout(null);
		
		JLabel tcknSorguLbl = new JLabel("TC Kimlik Numaras\u0131");
		tcknSorguLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tcknSorguLbl.setBounds(240, 11, 180, 25);
		sorgu.add(tcknSorguLbl);
		
		tcknSorguField = new JFormattedTextField();
		tcknSorguField.setHorizontalAlignment(SwingConstants.CENTER);
		tcknSorguField.setBounds(240, 42, 180, 25);
		sorgu.add(tcknSorguField);
		
		JButton cezaSorButton = new JButton("Ceza Sorgula");
		cezaSorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kimlikNo = tcknSorguField.getText();
				yenilencezaTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Ceza ID", "�sim", "Soyisim", "Ceza Sebebi", "Ceza �creti", "Son �deme Tarihi", "Plaka"
						}
					));
				try {
					Queries.cezaSorgula_Police(kimlikNo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				}
			}
		});
		cezaSorButton.setBounds(121, 84, 169, 25);
		sorgu.add(cezaSorButton);
		
		JButton borcSorButton = new JButton("Bor\u00E7 Sorgula");
		borcSorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kimlikNo = tcknSorguField.getText();
				try {
					Queries.borcSorgula_Police();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		borcSorButton.setBounds(450, 84, 177, 24);
		sorgu.add(borcSorButton);
		
		JScrollPane yenilencezaScroll = new JScrollPane();
		yenilencezaScroll.setBounds(17, 120, 384, 337);
		sorgu.add(yenilencezaScroll);
		
		yenilencezaTable = new JTable();
		yenilencezaTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Ceza ID", "�sim", "Soyisim", "Ceza Sebebi", "Ceza �creti", "Son �deme Tarihi", "Plaka"
			}
		));
		yenilencezaScroll.setViewportView(yenilencezaTable);
		
		borcSorField = new JLabel("");
		borcSorField.setHorizontalAlignment(SwingConstants.CENTER);
		borcSorField.setBackground(Color.WHITE);
		borcSorField.setForeground(Color.BLACK);
		borcSorField.setBounds(492, 119, 98, 25);
		sorgu.add(borcSorField);
		
		JPanel cezaTipleri = new JPanel();
		tabbedPane.addTab("Ceza Tipleri Tablosu", null, cezaTipleri, null);
		cezaTipleri.setLayout(null);
		
		JScrollPane cezaTipScroll = new JScrollPane();
		cezaTipScroll.setBounds(0, 0, 637, 468);
		cezaTipleri.add(cezaTipScroll);
		
		cezaTipTable = new JTable();
		cezaTipTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ceza Numaras�", "Ceza �smi"
			}
			
		));
		cezaTipScroll.setViewportView(cezaTipTable);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Bor� �deme", null, panel_4, null);
		panel_4.setLayout(null);
		
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
					Queries.borcOde_Police();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cezaOdeButton.setBounds(238, 113, 133, 22);
		panel_4.add(cezaOdeButton);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Tayin", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Atama Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Queries.atamaYap();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(257, 146, 122, 23);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(218, 49, 193, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(218, 106, 193, 29);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Polis TCKN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(257, 22, 122, 27);
		panel.add(lblNewLabel);
		
		JLabel lblAtanlacakYer = new JLabel("Atan\u0131lacak Yer");
		lblAtanlacakYer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtanlacakYer.setBounds(257, 78, 122, 27);
		panel.add(lblAtanlacakYer);
	}
}
