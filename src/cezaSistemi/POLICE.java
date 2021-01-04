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
import java.awt.SystemColor;

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
		frmTrafikCezaSistemi.setBounds(100, 100, 1050, 700);
		frmTrafikCezaSistemi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrafikCezaSistemi.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1034, 661);
		frmTrafikCezaSistemi.getContentPane().add(tabbedPane);
		
		JPanel cezaKayit = new JPanel();
		cezaKayit.setBackground(new Color(255, 204, 153));
		tabbedPane.addTab("Yeni Ceza Kayd\u0131", null, cezaKayit, null);
		cezaKayit.setLayout(null);
		
		isimField = new JFormattedTextField();
		isimField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isimField.setBounds(440, 71, 200, 30);
		cezaKayit.add(isimField);
		
		soyisimField = new JFormattedTextField();
		soyisimField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		soyisimField.setBounds(440, 156, 200, 30);
		cezaKayit.add(soyisimField);
		
		tcknField = new JFormattedTextField();
		tcknField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tcknField.setBounds(440, 242, 200, 30);
		cezaKayit.add(tcknField);
		
		plakaField = new JFormattedTextField();
		plakaField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		plakaField.setBounds(440, 326, 200, 30);
		cezaKayit.add(plakaField);
		
		aractipiBox = new JComboBox();
		aractipiBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		aractipiBox.setModel(new DefaultComboBoxModel(new String[] {"Otomobil", "Motorsiklet", "Kamyon", "Kamyonet", "T\u0131r", "Minib\u00FCs", "Otob\u00FCs", "Trakt\u00F6r", "\u0130\u015F Makinesi", "Karavan"}));
		aractipiBox.setBounds(440, 408, 200, 30);
		cezaKayit.add(aractipiBox);
		
		cezatipiBox = new JComboBox();
		cezatipiBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cezatipiBox.setBounds(440, 494, 200, 30);
		cezaKayit.add(cezatipiBox);
		
		JButton cezakayitButton = new JButton("Ekle");
		cezakayitButton.setForeground(new Color(255, 255, 255));
		cezakayitButton.setBackground(new Color(255, 102, 102));
		cezakayitButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cezakayitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Queries.yeniCezaEkle();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		cezakayitButton.setBounds(475, 552, 130, 30);
		cezaKayit.add(cezakayitButton);
		
		JLabel isimLbl = new JLabel("\u0130sim");
		isimLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		isimLbl.setHorizontalAlignment(SwingConstants.CENTER);
		isimLbl.setBounds(440, 40, 200, 20);
		cezaKayit.add(isimLbl);
		
		JLabel soyisimLbl = new JLabel("Soyisim");
		soyisimLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		soyisimLbl.setHorizontalAlignment(SwingConstants.CENTER);
		soyisimLbl.setBounds(440, 125, 200, 20);
		cezaKayit.add(soyisimLbl);
		
		JLabel tcknLbl = new JLabel("TC Kimlik Numaras\u0131");
		tcknLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tcknLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tcknLbl.setBounds(440, 211, 200, 20);
		cezaKayit.add(tcknLbl);
		
		JLabel plakaLbl = new JLabel("Plaka");
		plakaLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		plakaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		plakaLbl.setBounds(440, 295, 200, 20);
		cezaKayit.add(plakaLbl);
		
		JLabel aractipLbl = new JLabel("Ara\u00E7 Tipi");
		aractipLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		aractipLbl.setHorizontalAlignment(SwingConstants.CENTER);
		aractipLbl.setBounds(440, 377, 200, 20);
		cezaKayit.add(aractipLbl);
		
		JLabel cezatipLbl = new JLabel("Ceza Sebebi");
		cezatipLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cezatipLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cezatipLbl.setBounds(440, 463, 200, 20);
		cezaKayit.add(cezatipLbl);
		
		
		JPanel sorgu = new JPanel();
		sorgu.setBackground(new Color(204, 255, 204));
		tabbedPane.addTab("Sorgulama", null, sorgu, null);
		sorgu.setLayout(null);
		
		JLabel tcknSorguLbl = new JLabel("TC Kimlik Numaras\u0131");
		tcknSorguLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tcknSorguLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tcknSorguLbl.setBounds(10, 8, 200, 30);
		sorgu.add(tcknSorguLbl);
		
		tcknSorguField = new JFormattedTextField();
		tcknSorguField.setHorizontalAlignment(SwingConstants.CENTER);
		tcknSorguField.setBounds(10, 49, 200, 30);
		sorgu.add(tcknSorguField);
		
		JButton cezaSorButton = new JButton("Ceza Sorgula");
		cezaSorButton.setBackground(new Color(204, 255, 255));
		cezaSorButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
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
					e1.printStackTrace();
				
				}
			}
		});
		cezaSorButton.setBounds(425, 48, 130, 30);
		sorgu.add(cezaSorButton);
		
		JButton borcSorButton = new JButton("Bor\u00E7 Sorgula");
		borcSorButton.setBackground(new Color(204, 255, 255));
		borcSorButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		borcSorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kimlikNo = tcknSorguField.getText();
				try {
					Queries.borcSorgula_Police();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		borcSorButton.setBounds(819, 8, 200, 30);
		sorgu.add(borcSorButton);
		
		JScrollPane yenilencezaScroll = new JScrollPane();
		yenilencezaScroll.setBounds(10, 90, 1009, 532);
		sorgu.add(yenilencezaScroll);
		
		yenilencezaTable = new JTable();
		yenilencezaTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yenilencezaTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ceza ID", "\u0130sim", "Soyisim", "Ceza Sebebi", "Ceza \u00DCcreti", "Kesilme Tarihi", "Son \u00D6deme Tarihi", "Plaka"
			}
		));
		yenilencezaTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		yenilencezaTable.getColumnModel().getColumn(0).setMinWidth(1);
		yenilencezaTable.getColumnModel().getColumn(0).setMaxWidth(100);
		yenilencezaTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		yenilencezaTable.getColumnModel().getColumn(1).setMinWidth(1);
		yenilencezaTable.getColumnModel().getColumn(1).setMaxWidth(100);
		yenilencezaTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		yenilencezaTable.getColumnModel().getColumn(2).setMinWidth(1);
		yenilencezaTable.getColumnModel().getColumn(2).setMaxWidth(100);
		yenilencezaTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		yenilencezaTable.getColumnModel().getColumn(3).setMinWidth(1);
		yenilencezaTable.getColumnModel().getColumn(3).setMaxWidth(1000);
		yenilencezaTable.getColumnModel().getColumn(4).setPreferredWidth(65);
		yenilencezaTable.getColumnModel().getColumn(4).setMinWidth(1);
		yenilencezaTable.getColumnModel().getColumn(4).setMaxWidth(65);
		yenilencezaTable.getColumnModel().getColumn(5).setMinWidth(1);
		yenilencezaTable.getColumnModel().getColumn(5).setMaxWidth(75);
		yenilencezaTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		yenilencezaTable.getColumnModel().getColumn(6).setMinWidth(1);
		yenilencezaTable.getColumnModel().getColumn(6).setMaxWidth(100);
		yenilencezaTable.getColumnModel().getColumn(7).setMinWidth(1);
		yenilencezaTable.getColumnModel().getColumn(7).setMaxWidth(100);
		yenilencezaScroll.setViewportView(yenilencezaTable);
		
		borcSorField = new JLabel("");
		borcSorField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		borcSorField.setHorizontalAlignment(SwingConstants.CENTER);
		borcSorField.setBackground(Color.WHITE);
		borcSorField.setForeground(Color.BLACK);
		borcSorField.setBounds(819, 47, 200, 30);
		sorgu.add(borcSorField);
		
		JPanel cezaTipleri = new JPanel();
		cezaTipleri.setBackground(new Color(102, 153, 153));
		tabbedPane.addTab("Ceza Tipleri Tablosu", null, cezaTipleri, null);
		cezaTipleri.setLayout(null);
		
		JScrollPane cezaTipScroll = new JScrollPane();
		cezaTipScroll.setBounds(10, 11, 1009, 538);
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
		cezaNoField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cezaNoField.setBounds(169, 576, 130, 30);
		cezaTipleri.add(cezaNoField);
		cezaNoField.setColumns(10);
		
		yeniFiyatField = new JTextField();
		yeniFiyatField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yeniFiyatField.setColumns(10);
		yeniFiyatField.setBounds(490, 576, 130, 30);
		cezaTipleri.add(yeniFiyatField);
		
		JLabel lblNewLabel_1 = new JLabel("Ceza Numaras\u0131:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(54, 576, 105, 30);
		cezaTipleri.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Yeni Fiyat:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(403, 576, 90, 30);
		cezaTipleri.add(lblNewLabel_1_1);
		
		JButton zamButton = new JButton("Fiyat De\u011Fi\u015Ftir");
		zamButton.setBackground(new Color(51, 153, 255));
		zamButton.setForeground(new Color(255, 255, 255));
		zamButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
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
					e1.printStackTrace();
				}
			}
		});
		zamButton.setBounds(750, 576, 200, 30);
		cezaTipleri.add(zamButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(153, 204, 255));
		tabbedPane.addTab("Borç ödeme", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel cezaOdeLbl = new JLabel("\u00D6demek \u0130stedi\u011Finiz Ceza");
		cezaOdeLbl.setForeground(new Color(255, 255, 255));
		cezaOdeLbl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cezaOdeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cezaOdeLbl.setBounds(400, 200, 200, 40);
		panel_4.add(cezaOdeLbl);
		
		cezaOdeBox = new JComboBox();
		cezaOdeBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cezaOdeBox.setBounds(400, 250, 200, 40);
		panel_4.add(cezaOdeBox);
		
		JButton cezaOdeButton = new JButton("\u00D6de");
		cezaOdeButton.setBackground(new Color(51, 102, 51));
		cezaOdeButton.setForeground(new Color(255, 255, 255));
		cezaOdeButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cezaOdeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Queries.borcOde_Police();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		cezaOdeButton.setBounds(440, 310, 120, 40);
		panel_4.add(cezaOdeButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		tabbedPane.addTab("Tayin", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Atama Yap");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 204, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Queries.atamaYap();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(446, 375, 130, 40);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(410, 220, 200, 40);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setBounds(410, 324, 200, 40);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Polis TCKN:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(410, 174, 200, 35);
		panel.add(lblNewLabel);
		
		JLabel lblAtanlacakYer = new JLabel("Atan\u0131lacak Yer:");
		lblAtanlacakYer.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAtanlacakYer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtanlacakYer.setBounds(410, 278, 200, 35);
		panel.add(lblAtanlacakYer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 153, 153));
		tabbedPane.addTab("Diðer Sorgulamalar", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 497, 560);
		panel_1.add(scrollPane);
		
		kotuPolisTable = new JTable();
		kotuPolisTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		kotuPolisTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Kötü Polis TCKN"
			}
		));
		scrollPane.setViewportView(kotuPolisTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(517, 62, 502, 560);
		panel_1.add(scrollPane_1);
		
		minCezaTable = new JTable();
		minCezaTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		minCezaTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ýsim ve Soyisim", "Ceza Sayýsý"
			}
		));
		scrollPane_1.setViewportView(minCezaTable);
		
		JButton btnKotuPolisSorgula = new JButton("K\u00F6t\u00FC Polis Sorgula");
		btnKotuPolisSorgula.setForeground(new Color(255, 255, 255));
		btnKotuPolisSorgula.setBackground(new Color(255, 153, 0));
		btnKotuPolisSorgula.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnKotuPolisSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kotuPolisTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Kötü Polis TCKN"
						}
					));
				try {
					
					Queries.kotuPolis();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnKotuPolisSorgula.setBounds(10, 11, 270, 40);
		panel_1.add(btnKotuPolisSorgula);
		
		JButton btnMinCezas = new JButton("3'ten Fazla Cezas\u0131 Olanlar\u0131 Sorgula");
		btnMinCezas.setForeground(new Color(255, 255, 255));
		btnMinCezas.setBackground(new Color(255, 153, 0));
		btnMinCezas.setFont(new Font("Times New Roman", Font.BOLD, 14));
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
					e1.printStackTrace();
				}
			}
		});
		btnMinCezas.setBounds(749, 11, 270, 40);
		panel_1.add(btnMinCezas);
	}
}
