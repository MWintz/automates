package Random;

import java.util.ArrayList;

import automates.Alphabet;

public class Waxman {
	
	private int size;
	private float alpha;
	private int[][] matrix;
	private Alphabet[][] value;
	private int[][] matrixWaxman;
	
	private final static float beta = 2.2f;
	
	public Waxman(int size, ArrayList<Alphabet> alphabets) {
		this.size = size;
		matrix = new int[size][size];
		value = new Alphabet[size][size];
		matrixWaxman = new int[size][size];
		chooseAlpha();
		init();
		waxmanRandomMatrix();
		waxman(alphabets);
	}
	
	private void chooseAlpha() {
		if(size <= 3) {
			alpha = 1.0f;
		}
		else if( size <= 8)
			alpha = (float)size/20;
		else
			alpha = 0.15f;
	}
	
	private void init() {
		for(int s = 0; s < size; s++)
			for(int t = 0; t < size; t++) {
				matrix[s][t] = 0;
				value[s][t] = new Alphabet('-', false);
				matrixWaxman[s][t] = -1;
			}
	}
	
	private void waxmanRandomMatrix() {
		for (int s = 0; s < size; s++) {
			int x = Random.randomInt(size, false);
			int y = Random.randomInt(size, false);
			
			while(matrixWaxman[x][y] != -1) {
				x = Random.randomInt(size, false);
				y = Random.randomInt(size, false);
			}
			matrixWaxman[x][y] = s;
		}
	}
	
	private void loop(int s, ArrayList<Alphabet> alphabet) {
		if(Random.randomBool()) {
			Alphabet alph = alphabet.get(Random.randomInt(alphabet.size(), false));
			if(!alph.equals(Alphabet.epsilon_alph)) {
				matrix[s][s] = 1;
				value[s][s] = alph;
			}
		}
	}
	
	public Point getPosition(int s) {
		Point p = null;
			for(int x = 0; x < size; x++) {
				for(int y = 0; y < size; y++) {
					if(matrixWaxman[x][y] == s)
						p = new Point(x, y);
				}
			}
		return p;
	}
	
	public double getMaxDist() {
		double max = 0d;
		double dist;
		Point p1,p2;
			for(int s = 0; s < size; s++) {
				p1 = getPosition(s);
				for(int t = 0; t < size; t++) {
					if(t != s) {
						p2 = getPosition(t);
						dist = p1.distance(p2);
						if(dist > max)
							max = dist;	
					}
				}
			}
		return max;
	}
	
	private void waxman(ArrayList<Alphabet> alphabet) {
		double d, p, T;
		double L = getMaxDist();
		Point p1,p2;
		for(int s = 0; s < size; s++) {
			loop(s, alphabet);
			p1 = getPosition(s);
			for(int t = 0; t < size; t++) {
				if(t != s) {
					p2 = getPosition(t);
					d = p1.distance(p2);
					p = beta * Math.exp(-d / (alpha * L));
					T = Random.randomDouble(0.0, 1.0);
					if(T < p) {
						matrix[s][t] = 1;
						value[s][t] = alphabet.get(Random.randomInt(alphabet.size(), false));
					}
				}
			}
		}
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public int getSize() {
		return size;
	}
	
	public Alphabet[][] getValue() {
		return value;
	}
	
	/*debug test*/
	public void toStringMatrix() {
		for(int s = 0; s < size; s++) {
			for(int t = 0; t < size; t++) {
				System.out.print(matrix[s][t]+" ");
			}
			System.out.println();
		}
	}
	
	public void toStringValue() {
		for(int s = 0; s < size; s++) {
			for(int t = 0; t < size; t++) {
				System.out.print(value[s][t]+" ");
			}
			System.out.println();
		}
	}
	
	public void toStringMatrixWaxman() {
		for(int s = 0; s < size; s++) {
			for(int t = 0; t < size; t++) {
				System.out.print(matrixWaxman[s][t]+" ");
			}
			System.out.println();
		}
	}
}
