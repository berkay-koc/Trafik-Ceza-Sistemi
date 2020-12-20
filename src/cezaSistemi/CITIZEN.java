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

public class CITIZEN {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CITIZEN window = new CITIZEN();
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
	public CITIZEN() {
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Sorgulama", null, panel_1, null);
		
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(405, 124, 177, 25);
		panel_1.add(textPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		tabbedPane.addTab("Ceza Ödeme", null, panel_4, null);
		
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
