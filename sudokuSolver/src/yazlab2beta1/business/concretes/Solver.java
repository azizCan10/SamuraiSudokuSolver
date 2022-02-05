package yazlab2beta1.business.concretes;

import yazlab2beta1.entities.abstracts.Numbers;
import yazlab2beta1.entities.concretes.Column;

public class Solver extends Numbers {

	public static Samurai samurai;
	public static boolean stop;
	public static int[] stats;
	public static int index;
	public static Column h;
	public static Node[] o;
	public static int realSudoku[][] = new int[PUZZLE_SIDE][PUZZLE_SIDE];
	
	public static final int[][] SAMURAI_SQUARE = 
	    {{0}, {0},    {0},  {},    {1}, {1}, {1},
	     {0}, {0},    {0},  {},    {1}, {1}, {1},
	     {0}, {0}, {0, 2}, {2}, {1, 2}, {1}, {1},
	      {},  {},    {2}, {2},    {2},  {},  {},
	     {3}, {3}, {2, 3}, {2}, {2, 4}, {4}, {4},
	     {3}, {3},    {3}, {},     {4}, {4}, {4},
	     {3}, {3},    {3}, {},     {4}, {4}, {4}};

	public static final int[][] SUDOKU_ROW =
	    {{0, 1, 2, 3, 4, 5, 6, 7, 8},
	     {0, 1, 2, 3, 4, 5, 6, 7, 8},
	     {-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
	     {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
	     {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8}};

	public static final int[][] SUDOKU_COLUMN =
	    {{0, 1, 2, 3, 4, 5, 6, 7, 8},
	     {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
	     {-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8},
	     {0, 1, 2, 3, 4, 5, 6, 7, 8},
	     {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8}};


	public Solver(int[][] p) {

	    h = new Column(null, 0);
	    Column[] m = new Column[COLUMN_SIZE];

	    for (int i = 0; i < COLUMN_SIZE; i++)
		m[i] = new Column(h, 0);

	    Node[] l = new Node[PUZZLE_SIZE];
	    int i = 0;
	    
	    for (int r = 0; r < PUZZLE_SIDE; r++) {
	    	for (int c = 0; c < PUZZLE_SIDE; c++) {
				for (int d = 0; d < SUDOKU_SIDE; d++) {
		
					int k = 1 + (r * PUZZLE_SIDE * SUDOKU_SIDE) +
					    (c * SUDOKU_SIDE) + d;
		
					int s = (c / 3) + ((r / 3) * 7);
		
					if (SAMURAI_SQUARE[s].length > 0) {
		
					    Node n = new Node(m[(r * PUZZLE_SIDE) + c], k);
		
					    for (int j = 0; j < SAMURAI_SQUARE[s].length; j++)
					    {
		
						int pz = SAMURAI_SQUARE[s][j];
		
						int pr = SUDOKU_ROW[pz][r];
						int pc = SUDOKU_COLUMN[pz][c];
	
						n.add(new Node(m[PUZZLE_SIZE +
								 (pz * SUDOKU_SIZE) +
								 (pr * SUDOKU_SIDE) + d], k));

						n.add(new Node(m[PUZZLE_SIZE + 405 +
								 (pz * SUDOKU_SIZE) +
								 (pc * SUDOKU_SIDE) + d], k));
					    }

					    n.add(new Node(m[PUZZLE_SIZE + 405 + 405 +
							     (s * SUDOKU_SIDE) + d], k));

					    if (p[c][r] == (d + 1))
						l[i++] = n;
					}
			    }
			}
	    }

	    for (Column c = (Column) h.r; c != h; c = (Column) c.r) {
	    	if (c.s == 0) {
	    		c.cover();
	    	}
	    }

	    o = new Node[PUZZLE_SIZE];

	    for (int j = 0; j < i; j++) {
			l[j].remove();
			o[index++] = l[j];
	    }

	    stats = new int[PUZZLE_SIZE];
	}

	public void report(int[] o) {

	    for (int i = 0; i < o.length; i++) {
			int v = o[i];
	
			int d = v % SUDOKU_SIDE;
			int c = (v / SUDOKU_SIDE) % PUZZLE_SIDE;
			int r = (v / (PUZZLE_SIDE * SUDOKU_SIDE)) % PUZZLE_SIDE;
	
			realSudoku[c][r] = d + 1;
	    }

	}

	public void solve(Samurai s) {
	    samurai = s;
	    search(index);
	}

	public void search(int k)
	{
		
	    if (stop) {
	    	return;
	    }

	    if (h.r == h) {
			int[] a = new int[k];

			for (int i = 0; i < k; i++) {
				a[i] = o[i].n - 1;
			}
			
			report(a);
			stop = true;
	    }

	    else {
			Column c = null;
			int s = Integer.MAX_VALUE;

			stats[k]++;

			for (Column j = (Column) h.r; j != h; j = (Column) j.r) {
				if (s > j.s) {
					c = j;
					s = j.s;
			    }
			}

			c.cover();

			for (Node r = c.d; r != c; r = r.d) {

			    if (stop) {
			    	break;
			    }

			    o[k] = r;

			    for (Node j = r.r; j != r; j = j.r) {
			    	j.c.cover();
			    }

			    search(k + 1);
	
			    for (Node j = r.l; j != r; j = j.l) {
			    	j.c.uncover();
			    }
			}
	
			c.uncover();
	    }
	}

}
