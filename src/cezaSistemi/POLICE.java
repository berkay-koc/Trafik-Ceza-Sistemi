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

public class POLICE {

	private JFrame frame;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POLICE window = new POLICE();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public POLICE() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 658, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 642, 496);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Yeni Ceza Kayd\u0131", null, panel, null);
		panel.setLayout(null);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(240, 57, 180, 20);
		panel.add(formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(240, 176, 180, 20);
		panel.add(formattedTextField_1);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(240, 224, 180, 20);
		panel.add(formattedTextField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Araba", "Motorsiklet", "Kamyon", "T\u0131r"}));
		comboBox.setBounds(240, 281, 180, 20);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.setBounds(292, 393, 89, 23);
		panel.add(btnNewButton);
		
		JLabel isimLbl = new JLabel("\u0130sim");
		isimLbl.setHorizontalAlignment(SwingConstants.CENTER);
		isimLbl.setBounds(240, 32, 180, 14);
		panel.add(isimLbl);
		
		JFormattedTextField formattedTextField_1_1 = new JFormattedTextField();
		formattedTextField_1_1.setBounds(240, 114, 180, 20);
		panel.add(formattedTextField_1_1);
		
		JLabel lblSoyisim = new JLabel("Soyisim");
		lblSoyisim.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoyisim.setBounds(240, 89, 180, 14);
		panel.add(lblSoyisim);
		
		JLabel lblNewLabel_1 = new JLabel("TC Kimlik Numaras\u0131");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(240, 151, 180, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Plaka");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(240, 199, 180, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Ara\u00E7 Tipi");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(240, 256, 180, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Ceza Sebebi");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(240, 317, 180, 14);
		panel.add(lblNewLabel_1_1_1_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(240, 337, 180, 20);
		panel.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Sorgulama", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("TC Kimlik Numaras\u0131");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(240, 11, 180, 25);
		panel_1.add(lblNewLabel_3);
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_3.setBounds(240, 42, 180, 25);
		panel_1.add(formattedTextField_3);
		
		JButton btnNewButton_1 = new JButton("Ceza Sorgula");
		btnNewButton_1.setBounds(88, 84, 169, 25);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Bor\u00E7 Sorgula");
		btnNewButton_2.setBounds(405, 84, 177, 24);
		panel_1.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 120, 310, 337);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(405, 124, 177, 25);
		panel_1.add(textPane);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Ceza Tipleri Tablosu", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 637, 468);
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Borç ödeme", null, panel_4, null);
		panel_4.setLayout(null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(180, 80, 246, 22);
		panel_4.add(comboBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("\u00D6demek \u0130stedi\u011Finiz Ceza");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(180, 47, 246, 22);
		panel_4.add(lblNewLabel_2);
		
		JButton btnNewButton_3 = new JButton("\u00D6de");
		btnNewButton_3.setBounds(238, 113, 133, 22);
		panel_4.add(btnNewButton_3);
	}
}
