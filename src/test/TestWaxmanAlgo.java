package test;

import java.util.ArrayList;

import Random.Waxman;
import automates.Alphabet;

public class TestWaxmanAlgo {

	public static void main(String[] args) {
		Alphabet a,b;
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		
		a=new Alphabet('a', false);
		b=new Alphabet('b', false);
		
		alphabets.add(a);
		alphabets.add(b);
		alphabets.add(Alphabet.epsilon_alph);
		
		Waxman waxman = new Waxman(4, alphabets);
		
		waxman.toStringMatrixWaxman();
		waxman.toStringMatrix();
		waxman.toStringValue();
	}
}
