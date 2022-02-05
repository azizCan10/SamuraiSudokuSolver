package yazlab2beta1.business.concretes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yazlab2beta1.Main;
import yazlab2beta1.business.abstracts.SudokuService;
import yazlab2beta1.entities.concretes.Hamle;

public class Sudoku5Manager implements SudokuService {

	private static final int GRID_SIZE = 9;
	private List<Hamle> hamleler; 
	private BufferedWriter bw = null;
    private FileWriter fw = null;
	
	private int[][] sudokuTahtasi;

	public Sudoku5Manager(int[][] sudokuTahtasi) {
		this.sudokuTahtasi = sudokuTahtasi;
		hamleler = new ArrayList<>();
	}
	
	@Override
	public void run() {
		long start, end;
		start = System.currentTimeMillis();
	    
	    if (solveBoard(sudokuTahtasi)) {
	    	end = System.currentTimeMillis();
	    }
	    else {
	    	System.out.println("Unsolvable board :(");
	    }

	    System.out.println();
	}

	public boolean isNumberInRow(int[][] board, int number, int row) {
		
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		
		return false;
	}

	public boolean isNumberInColumn(int[][] board, int number, int column) {

		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		
		return false;
	}

	public boolean isNumberInBox(int[][] board, int number, int row, int column) {

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
	
	//1. sudoku için	
	public boolean isRowInOther(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row + 6][i] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isColumnInOther(int[][] board, int number, int column) {

		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column + 6] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isValidPlacement2(int[][] board, int number, int row, int column) {
		return !isColumnInOther(board, number, column) && !isRowInOther(board, number, row);
	}
	
	//2. sudoku için
	public boolean isRowInOther2(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row + 6][i] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isColumnInOther2(int[][] board, int number, int column) {

		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column - 6] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isValidPlacement3(int[][] board, int number, int row, int column) {
		return !isColumnInOther2(Main.board2, number, column) && !isRowInOther2(Main.board2, number, row);
	}
	
	//3. sudoku için
	public boolean isRowInOther3(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row - 6][i] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isColumnInOther3(int[][] board, int number, int column) {

		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column + 6] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isValidPlacement4(int[][] board, int number, int row, int column) {
		return !isColumnInOther3(Main.board3, number, column) && !isRowInOther3(Main.board3, number, row);
	}
	
	//4. sudoku için
	public boolean isRowInOther4(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row - 6][i] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isColumnInOther4(int[][] board, int number, int column) {

		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column - 6] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isValidPlacement5(int[][] board, int number, int row, int column) {
		return !isColumnInOther4(Main.board4, number, column) && !isRowInOther4(Main.board4, number, row);
	}

	public boolean isValidPlacement(int[][] board, int number, int row, int column) {
		return !isNumberInRow(board, number, row) &&
				!isNumberInColumn(board, number, column) &&
				!isNumberInBox(board, number, row, column);
	}

	@Override
	public boolean solveBoard(int[][] board) {
		
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				
				if (row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == 0) {
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, column) && isValidPlacement2(Main.board1, numberToTry, row, column)) {
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
				
				if (row >= 0 && row <3 && column >= 6 && column < 9 && board[row][column] == 0) {
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, column) && isValidPlacement3(Main.board2, numberToTry, row, column)) {
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
				
				if (row >= 6 && row <9 && column >= 0 && column < 3 && board[row][column] == 0) {
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, column) && isValidPlacement4(Main.board3, numberToTry, row, column)) {
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
				
				if (row >= 6 && row <9 && column >= 6 && column < 9 && board[row][column] == 0) {
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, column) && isValidPlacement5(Main.board4, numberToTry, row, column)) {
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
				
				if (board[row][column] == 0) {
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, column)) {
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
	public boolean isItOk(int[][] board, int number, int row, int column) {
		// TODO Auto-generated method stub
		return false;
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
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
	}

}
