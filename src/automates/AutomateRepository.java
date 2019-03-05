package automates;

import java.util.ArrayList;

import Random.Random;

public class AutomateRepository {
	
	private static Alphabet A=new Alphabet('a', false);
	private static Alphabet B=new Alphabet('b', false);
	private static Alphabet C=new Alphabet('c', false);
	private static Alphabet D=new Alphabet('d', false);
	private static Alphabet E=new Alphabet('e', false);
	
	private static Alphabet ZERO=new Alphabet('0', false);
	private static Alphabet UN=new Alphabet('1', false);
	
	private static Alphabet ACOO=new Alphabet('{', false);
	private static Alphabet ACOF=new Alphabet('}', false);
	private static Alphabet VIR=new Alphabet(',', false);
	private static Alphabet OR=new Alphabet('|', false);
	private static Alphabet AND=new Alphabet('&', false);
	
	private static Alphabet[] alphaChar={A, B, C, D, E};
	private static Alphabet[] alphaBinary={ZERO, UN};
	private static Alphabet[] alphaInfo={ACOO, ACOF, VIR, OR, AND};
	
	public static ArrayList<Alphabet> randomAlphabet(int nbAlphabet, String type){
		ArrayList<Alphabet> alphabet=new ArrayList<Alphabet>();
		int index;
		Alphabet alph;
			while(alphabet.size()<nbAlphabet) {
				switch (type) {
					case "BINARY":
						index=Random.randomInt1(2);
						alph=alphaBinary[index];
						if(!alphabet.contains(alph))
							alphabet.add(alph);
					break;
					case "INFO":
						index=Random.randomInt1(5);
						alph=alphaInfo[index];
						if(!alphabet.contains(alph))
							alphabet.add(alph);
					break;
					default:
						index=Random.randomInt1(5);
						alph=alphaChar[index];
						if(!alphabet.contains(alph))
							alphabet.add(alph);
					break;
				}
			}
		return alphabet;
	}
}