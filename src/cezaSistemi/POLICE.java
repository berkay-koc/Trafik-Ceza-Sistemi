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
import java.awt.event.ActionEvent;

public class POLICE {

	private JFrame frmTrafikCezaSistemi;
	private JTable yenilencezaTable;
	private JTable cezaTipTable;

	
	public static void police() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POLICE window = new POLICE();
					window.frmTrafikCezaSistemi.setVisible(true);
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
		
		JFormattedTextField isimField = new JFormattedTextField();
		isimField.setBounds(240, 57, 180, 20);
		cezaKayit.add(isimField);
		
		JFormattedTextField soyisimField = new JFormattedTextField();
		soyisimField.setBounds(240, 114, 180, 20);
		cezaKayit.add(soyisimField);
		
		JFormattedTextField tcknField = new JFormattedTextField();
		tcknField.setBounds(240, 176, 180, 20);
		cezaKayit.add(tcknField);
		
		JFormattedTextField plakaField = new JFormattedTextField();
		plakaField.setBounds(240, 224, 180, 20);
		cezaKayit.add(plakaField);
		
		JComboBox aractipiBox = new JComboBox();
		aractipiBox.setModel(new DefaultComboBoxModel(new String[] {"Araba", "Motorsiklet", "Kamyon", "T\u0131r"}));
		aractipiBox.setBounds(240, 281, 180, 20);
		cezaKayit.add(aractipiBox);
		
		JButton cezakayitButton = new JButton("Ekle");
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
		
		JComboBox cezatipiBox = new JComboBox();
		cezatipiBox.setBounds(240, 337, 180, 20);
		cezaKayit.add(cezatipiBox);
		
		JPanel sorgu = new JPanel();
		tabbedPane.addTab("Sorgulama", null, sorgu, null);
		sorgu.setLayout(null);
		
		JLabel tcknSorguLbl = new JLabel("TC Kimlik Numaras\u0131");
		tcknSorguLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tcknSorguLbl.setBounds(240, 11, 180, 25);
		sorgu.add(tcknSorguLbl);
		
		JFormattedTextField tcknSorguField = new JFormattedTextField();
		tcknSorguField.setHorizontalAlignment(SwingConstants.CENTER);
		tcknSorguField.setBounds(240, 42, 180, 25);
		sorgu.add(tcknSorguField);
		
		JButton cezaSorButton = new JButton("Ceza Sorgula");
		cezaSorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cezaSorButton.setBounds(88, 84, 169, 25);
		sorgu.add(cezaSorButton);
		
		JButton borcSorButton = new JButton("Bor\u00E7 Sorgula");
		borcSorButton.setBounds(405, 84, 177, 24);
		sorgu.add(borcSorButton);
		
		JScrollPane yenilencezaScroll = new JScrollPane();
		yenilencezaScroll.setBounds(17, 120, 310, 337);
		sorgu.add(yenilencezaScroll);
		
		yenilencezaTable = new JTable();
		yenilencezaScroll.setViewportView(yenilencezaTable);
		
		JTextPane borcSorText = new JTextPane();
		borcSorText.setBounds(405, 124, 177, 25);
		sorgu.add(borcSorText);
		
		JPanel cezaTipleri = new JPanel();
		tabbedPane.addTab("Ceza Tipleri Tablosu", null, cezaTipleri, null);
		cezaTipleri.setLayout(null);
		
		JScrollPane cezaTipScroll = new JScrollPane();
		cezaTipScroll.setBounds(0, 0, 637, 468);
		cezaTipleri.add(cezaTipScroll);
		
		cezaTipTable = new JTable();
		cezaTipScroll.setViewportView(cezaTipTable);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Borç ödeme", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel cezaOdeLbl = new JLabel("\u00D6demek \u0130stedi\u011Finiz Ceza");
		cezaOdeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cezaOdeLbl.setBounds(180, 47, 246, 22);
		panel_4.add(cezaOdeLbl);
		
		JComboBox cezaOdeBox = new JComboBox();
		cezaOdeBox.setBounds(180, 80, 246, 22);
		panel_4.add(cezaOdeBox);
		
		JButton cezaOdeButton = new JButton("\u00D6de");
		cezaOdeButton.setBounds(238, 113, 133, 22);
		panel_4.add(cezaOdeButton);
	}
}
