package yazlab2beta1.business.concretes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yazlab2beta1.Main;
import yazlab2beta1.business.abstracts.SudokuService;
import yazlab2beta1.entities.concretes.Hamle;

public class SudokuManager2 implements SudokuService {

	private static final int GRID_SIZE = 9;
	private int[][] sudokuTahtasi;
	private List<Hamle> hamleler;
	private BufferedWriter bw = null;
    private FileWriter fw = null;

	public SudokuManager2(int[][] sudokuTahtasi) {
		this.sudokuTahtasi = sudokuTahtasi;
		hamleler = new ArrayList<>();
	}
	
	@Override
	public void run() {
		long start, end;
		start = System.currentTimeMillis();

	    if (solveBoard(sudokuTahtasi)) {
	    	end = System.currentTimeMillis();
	    	Main.timeThread10 += end - start;
	    }
	    else {
	    	System.out.println("Unsolvable board :(");
	    }

	    System.out.println();		
	}

	@Override
	public boolean isItOk(int[][] board, int number, int row, int column) {
		//satýr kontrolü
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		
		
		//sütun kontrolü
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		
		
		//kare kontrolü 3x3
		int localBoxRow = row - row % 3;
		int localBoxColumn = column - column % 3;
    
		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}
		
		return false;		
	}

	@Override
	public boolean solveBoard(int[][] board) {
		for (int row = GRID_SIZE - 1; row >= 0; row--) {
			for (int column = GRID_SIZE - 1; column >= 0; column--) {
				
				if (board[row][column] == 0) {
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (!isItOk(board, numberToTry, row, column)) {
							board[row][column] = numberToTry;
							hamleler.add(new Hamle(row, column, numberToTry));
							
							if (solveBoard(board)) {
								return true;
							}
							else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public void dosyayaHamleleriYaz() {
		try {
            fw = new FileWriter("hamleler.txt");
            bw = new BufferedWriter(fw);
            
            int h = 0;
            for (Hamle hamle : hamleler) {
                if (h % 10 == 0) {
                    bw.newLine();
                }
                bw.write(hamle + "\t");
                h++;
            }
            bw.newLine();
        } 
        catch (Exception e) {}
		
		try {
            if (bw != null)//dosyayý kapatýr yazdýktan sonra
                bw.close();
            if (fw != null)//dosyayý kapatýr yazdýktan sonra
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
	}

}
