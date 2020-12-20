package cezaSistemi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class LOGIN {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGIN window = new LOGIN();
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
	public LOGIN() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 433, 388);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setToolTipText("");
		formattedTextField.setBounds(115, 140, 180, 20);
		frame.getContentPane().add(formattedTextField);
		
		JLabel lblNewLabel = new JLabel("TC K\u0130ML\u0130K NUMARASI");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(115, 115, 180, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Giri\u015F");
		btnNewButton.setBounds(157, 171, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
