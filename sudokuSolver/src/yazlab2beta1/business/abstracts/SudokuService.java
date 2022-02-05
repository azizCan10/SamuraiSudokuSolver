package yazlab2beta1.business.abstracts;

public interface SudokuService extends Runnable {

	boolean isItOk(int[][] board, int number, int row, int column);
	boolean solveBoard(int[][] board);
	void dosyayaHamleleriYaz();
	
}
