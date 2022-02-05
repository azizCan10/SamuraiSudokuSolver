package yazlab2beta1.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yazlab2beta1.Main;
import yazlab2beta1.business.concretes.Solver;
import yazlab2beta1.business.concretes.Samurai;
import yazlab2beta1.entities.concretes.SamuraiUi;

public class FrmSamuraiSudoku extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSamuraiSudoku frame = new FrmSamuraiSudoku();
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
	
	SamuraiUi[][] board = new SamuraiUi[21][21]; 
	
	public FrmSamuraiSudoku() {
		setTitle("SAMURAY SUDOKU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -1, 1050, 1050);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(21, 21));
		
		Samurai samurai = new Samurai();
	    samurai.solve(Main.sudokuTahtasi);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				SamuraiUi samuray = new SamuraiUi(i, j);
				if(Solver.realSudoku[i][j] == 0) {
					samuray.setText("");
				}
				else {
					samuray.setText(String.valueOf(Solver.realSudoku[i][j]));
				}
				contentPane.add(samuray);
				board[i][j] = samuray;
			}
		}
	}

}
