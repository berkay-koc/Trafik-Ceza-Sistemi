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
	static JTable kotuPolisTable;
	static JTable minCezaTable;
	static JTextField cezaNoField;
	static JTextField yeniFiyatField;
	
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
		aractipiBox.setModel(new DefaultComboBoxModel(new String[] {"Araba", "Motorsiklet", "Kamyon", "Kamyonet", "T\u0131r", "Minib\u00FCs", "Otob\u00FCs", "Trakt\u00F6r", "\u0130\u015F Makinesi", "Karavan"}));
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
								"Ceza ID", "\u0130sim", "Soyisim", "Ceza Sebebi", "Ceza \u00DCcreti", "Kesilme Tarihi", "Son \u00D6deme Tarihi", "Plaka"
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
					"Ceza ID", "\u0130sim", "Soyisim", "Ceza Sebebi", "Ceza \u00DCcreti", "Kesilme Tarihi", "Son \u00D6deme Tarihi", "Plaka"
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
		cezaTipScroll.setBounds(0, 0, 637, 378);
		cezaTipleri.add(cezaTipScroll);
		
		cezaTipTable = new JTable();
		cezaTipTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ceza Numarasý", "Ceza Ýsmi", "Taban Ücret"
			}
			
		));
		cezaTipScroll.setViewportView(cezaTipTable);
		
		cezaNoField = new JTextField();
		cezaNoField.setBounds(10, 437, 120, 20);
		cezaTipleri.add(cezaNoField);
		cezaNoField.setColumns(10);
		
		yeniFiyatField = new JTextField();
		yeniFiyatField.setColumns(10);
		yeniFiyatField.setBounds(140, 437, 120, 20);
		cezaTipleri.add(yeniFiyatField);
		
		JLabel lblNewLabel_1 = new JLabel("Ceza Numaras\u0131");
		lblNewLabel_1.setBounds(10, 412, 120, 14);
		cezaTipleri.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Yeni Fiyat");
		lblNewLabel_1_1.setBounds(140, 412, 120, 14);
		cezaTipleri.add(lblNewLabel_1_1);
		
		JButton zamButton = new JButton("Fiyat De\u011Fi\u015Ftir");
		zamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cezaTipTable.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Ceza Numarasý", "Ceza Ýsmi", "Taban Ücret"
							}
							
						));
					Queries.zamYap();
					Queries.cezaTablosuInsert();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		zamButton.setBounds(270, 436, 105, 21);
		cezaTipleri.add(zamButton);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Borç ödeme", null, panel_4, null);
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
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Diðer Sorgulamalar", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 280, 396);
		panel_1.add(scrollPane);
		
		kotuPolisTable = new JTable();
		kotuPolisTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ýsim ve Soyisim"
			}
		));
		scrollPane.setViewportView(kotuPolisTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(347, 61, 280, 396);
		panel_1.add(scrollPane_1);
		
		minCezaTable = new JTable();
		minCezaTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ýsim ve Soyisim", "Ceza Sayýsý"
			}
		));
		scrollPane_1.setViewportView(minCezaTable);
		
		JButton btnKotuPolisSorgula = new JButton("K\u00F6t\u00FC Polis Sorgula");
		btnKotuPolisSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kotuPolisTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Ýsim ve Soyisim"
						}
					));
				try {
					
					Queries.kotuPolis();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnKotuPolisSorgula.setBounds(57, 26, 177, 24);
		panel_1.add(btnKotuPolisSorgula);
		
		JButton btnMinCezas = new JButton("Min. 4 Cezas\u0131 Olanlar\u0131 Sorgula");
		btnMinCezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minCezaTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Ýsim ve Soyisim", "Ceza Sayýsý"
						}
					));
				try {
					Queries.minCezaSorgula();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMinCezas.setBounds(399, 26, 177, 24);
		panel_1.add(btnMinCezas);
	}
}
