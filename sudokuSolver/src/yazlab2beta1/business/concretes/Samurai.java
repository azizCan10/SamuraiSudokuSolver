package yazlab2beta1.business.concretes;

public class Samurai {

    public void solve(int[][] puzzle) {

		Solver dl = new Solver(puzzle);

		dl.solve(this);
	}
    
}