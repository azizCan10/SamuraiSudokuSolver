package yazlab2beta1.entities.concretes;

import javax.swing.JLabel;

public class SamuraiUi extends JLabel {
	
	private int row;
	private int col;
	
	public SamuraiUi(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
}
