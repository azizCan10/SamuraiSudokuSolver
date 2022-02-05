package yazlab2beta1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import yazlab2beta1.business.abstracts.SudokuService;
import yazlab2beta1.business.concretes.Samurai;
import yazlab2beta1.business.concretes.Sudoku5Manager;
import yazlab2beta1.business.concretes.SudokuManager;
import yazlab2beta1.business.concretes.SudokuManager2;
import yazlab2beta1.ui.FrmResults;
import yazlab2beta1.ui.FrmSamuraiSudoku;

public class Main {
	
	public static int[][] sudokuTahtasi = new int[21][21];
	public static int[][] board1 = new int[9][9];
	public static int[][] board2 = new int[9][9];
	public static int[][] board3 = new int[9][9];
	public static int[][] board4 = new int[9][9];
	public static int[][] board5 = new int[9][9];
	public static int timeThread5 = 0;
	public static int timeThread10 = 0;

	public static void main(String[] args) throws InterruptedException {
		
		dosyadanOku();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board1[i][j] = sudokuTahtasi[i][j];
			}
		}
		
		int sudoku2col = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 12; j < 21; j++) {
				board2[i][sudoku2col++] = sudokuTahtasi[i][j];
			}
			sudoku2col = 0;
		}
		
		int sudoku3row = 0;
		for (int i = 12; i < 21; i++) {
			for (int j = 0; j < 9; j++) {
				board3[sudoku3row][j] = sudokuTahtasi[i][j];
			}
			sudoku3row++;
		}
		
		int sudoku4row = 0;
		int sudoku4col = 0;
		for (int i = 12; i < 21; i++) {
			for (int j = 12; j < 21; j++) {
				board4[sudoku4row][sudoku4col++] = sudokuTahtasi[i][j];
			}
			sudoku4row++;
			sudoku4col = 0;
		}
		
		int sudoku5row = 0;
		int sudoku5col = 0;
		for (int i = 6; i < 15; i++) {
			for (int j = 6; j < 15; j++) {
				board5[sudoku5row][sudoku5col++] = sudokuTahtasi[i][j];
			}
			sudoku5row++;
			sudoku5col = 0;
		}
		
		SudokuService sudoku1Service1 = new SudokuManager(copyTahta(board1));
		Thread thread1 = new Thread(sudoku1Service1, "Thread1");
		
		SudokuService sudoku1Service2 = new SudokuManager(copyTahta(board2));
		Thread thread2 = new Thread(sudoku1Service2, "Thread2");
	
		SudokuService sudoku1Service3 = new SudokuManager(copyTahta(board3));
		Thread thread3 = new Thread(sudoku1Service3, "Thread3");
			
		SudokuService sudoku1Service4 = new SudokuManager(copyTahta(board4));
		Thread thread4 = new Thread(sudoku1Service4, "Thread4");
		
		SudokuService sudoku1Service5 = new Sudoku5Manager(copyTahta(board5));
		Thread thread5 = new Thread(sudoku1Service5, "Thread5");
		
		
		
		SudokuService sudoku2Service1 = new SudokuManager2(copyTahta(board1));
		Thread thread6 = new Thread(sudoku2Service1, "Thread6");
		
		SudokuService sudoku2Service2 = new SudokuManager2(copyTahta(board2));
		Thread thread7 = new Thread(sudoku2Service2, "Thread7");
		
		SudokuService sudoku2Service3 = new SudokuManager2(copyTahta(board3));
		Thread thread8 = new Thread(sudoku2Service3, "Thread8");
		
		SudokuService sudoku2Service4 = new SudokuManager2(copyTahta(board4));
		Thread thread9 = new Thread(sudoku2Service4, "Thread9");
		
		SudokuService sudoku2Service5 = new Sudoku5Manager(copyTahta(board5));
		Thread thread10 = new Thread(sudoku2Service5, "Thread10");
		
		
		SudokuService sudoku3Service1 = new SudokuManager(copyTahta(board1));
		Thread thread11 = new Thread(sudoku3Service1, "Thread6");
		
		SudokuService sudoku3Service2 = new SudokuManager(copyTahta(board2));
		Thread thread12 = new Thread(sudoku3Service2, "Thread7");
		
		SudokuService sudoku3Service3 = new SudokuManager(copyTahta(board3));
		Thread thread13 = new Thread(sudoku3Service3, "Thread8");
		
		SudokuService sudoku3Service4 = new SudokuManager(copyTahta(board4));
		Thread thread14 = new Thread(sudoku3Service4, "Thread9");
		
		SudokuService sudoku3Service5 = new Sudoku5Manager(copyTahta(board5));
		Thread thread15 = new Thread(sudoku3Service5, "Thread10");
		
		thread1.start();
		Thread.sleep(20);
		
		thread2.start(); 
		Thread.sleep(20);
		
		thread3.start();
		Thread.sleep(20);
		
		thread4.start();
		Thread.sleep(20);
		
		thread5.start();
		Thread.sleep(20);
		
		
		thread6.start();
		Thread.sleep(20);
		thread11.start();
		Thread.sleep(20);
		
		thread7.start();
		Thread.sleep(20);
		thread12.start();
		Thread.sleep(20);
		
		thread8.start();
		Thread.sleep(20);
		thread13.start();
		Thread.sleep(20);
		
		thread9.start();
		Thread.sleep(20);
		thread14.start();
		Thread.sleep(20);
		
		thread10.start();
		Thread.sleep(20);
		thread15.start();
		
		
		sudoku1Service1.dosyayaHamleleriYaz();
		sudoku1Service2.dosyayaHamleleriYaz();
		sudoku1Service3.dosyayaHamleleriYaz();
		sudoku1Service4.dosyayaHamleleriYaz();
		sudoku1Service5.dosyayaHamleleriYaz();
		sudoku2Service1.dosyayaHamleleriYaz();
		sudoku2Service2.dosyayaHamleleriYaz();
		sudoku2Service3.dosyayaHamleleriYaz();
		sudoku2Service4.dosyayaHamleleriYaz();
		sudoku2Service5.dosyayaHamleleriYaz();
		sudoku3Service1.dosyayaHamleleriYaz();
		sudoku3Service2.dosyayaHamleleriYaz();
		sudoku3Service3.dosyayaHamleleriYaz();
		sudoku3Service4.dosyayaHamleleriYaz();
		sudoku3Service5.dosyayaHamleleriYaz();

	    new FrmSamuraiSudoku().setVisible(true);
	    new FrmResults().setVisible(true);
	}
	
	public static int[][] copyTahta(int[][] tahta) {
        int[][] kopyaTahta = new int[9][9];

        int i = 0, j = 0;
        for (int[] ints : tahta) {
            for (int deger : ints) {
                kopyaTahta[i][j] = deger;
                j++;
            }
            i++;
            j = 0;
        }
        return kopyaTahta;
    }
	
	public static void dosyadanOku() {
		
		    try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\JAN\\Desktop\\sudoku.txt"))) {
		        int j = 0;
		        for (Object satir : stream.toArray()) {
		            if ((j >= 0 && j < 6) || (j >= 15 && j < 21)) {
		            	sudokuTahtasi[j][9] = 0;
	            		sudokuTahtasi[j][10] = 0;
	            		sudokuTahtasi[j][11] = 0;
	            		
		            	for (int i = 0; i < satir.toString().length(); i++) {
		            		if (i < 9) {
		            			if (satir.toString().charAt(i) == '*') {
				                    sudokuTahtasi[j][i] = 0;
				                } else {
				                    sudokuTahtasi[j][i] = Integer.parseInt("" + satir.toString().charAt(i));
				                }
							}
		            		else {
		            			if (satir.toString().charAt(i) == '*') {
				                    sudokuTahtasi[j][i+3] = 0;
				                } else {
				                    sudokuTahtasi[j][i+3] = Integer.parseInt("" + satir.toString().charAt(i));
				                }
							}
			            }
					}
		            
		            if (j >= 9 && j < 12) {
		            	sudokuTahtasi[j][0] = 0;
	            		sudokuTahtasi[j][1] = 0;
	            		sudokuTahtasi[j][2] = 0;
	            		sudokuTahtasi[j][3] = 0;
	            		sudokuTahtasi[j][4] = 0;
	            		sudokuTahtasi[j][5] = 0;
	            		sudokuTahtasi[j][15] = 0;
	            		sudokuTahtasi[j][16] = 0;
	            		sudokuTahtasi[j][17] = 0;
	            		sudokuTahtasi[j][18] = 0;
	            		sudokuTahtasi[j][19] = 0;
	            		sudokuTahtasi[j][20] = 0;
	            		
		            	for (int i = 0; i < satir.toString().length(); i++) {
		            		if (satir.toString().charAt(i) == '*') {
			                    sudokuTahtasi[j][i+6] = 0;
			                } else {
			                    sudokuTahtasi[j][i+6] = Integer.parseInt("" + satir.toString().charAt(i));
			                }
			            }
					}
		            
		            if((j >= 6 && j < 9) || (j >= 12 && j < 15)) {
		            	for (int i = 0; i < satir.toString().length(); i++) {
		            		if (satir.toString().charAt(i) == '*') {
			                    sudokuTahtasi[j][i] = 0;
			                } else {
			                    sudokuTahtasi[j][i] = Integer.parseInt("" + satir.toString().charAt(i));
			                }
			            }
					}
		            
		            j++;
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
}
