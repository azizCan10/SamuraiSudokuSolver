package yazlab2beta1.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yazlab2beta1.Main;

import javax.swing.JLabel;
import java.awt.Font;

public class FrmResults extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmResults frame = new FrmResults();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmResults() {
		setTitle("SAMURAY SUDOKU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1050, -1, 870, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl5Thread = new JLabel("5 thread ile " + Main.timeThread5 + " ms'de çözülmüþtür.");
		lbl5Thread.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbl5Thread.setBounds(75, 186, 712, 145);
		contentPane.add(lbl5Thread);
		
		JLabel lbl10Thread = new JLabel("10 thread ile " + Main.timeThread10 + " ms'de çözülmüþtür.");
		lbl10Thread.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbl10Thread.setBounds(75, 436, 712, 145);
		contentPane.add(lbl10Thread);
	}
}
