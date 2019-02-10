package automates;

import java.util.ArrayList;

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
						index=randomInt1(2);
						alph=alphaBinary[index];
						if(!alphabet.contains(alph))
							alphabet.add(alph);
					break;
					case "INFO":
						index=randomInt1(5);
						alph=alphaInfo[index];
						if(!alphabet.contains(alph))
							alphabet.add(alph);
					break;
					default:
						index=randomInt1(5);
						alph=alphaChar[index];
						if(!alphabet.contains(alph))
							alphabet.add(alph);
					break;
				}
			}
		return alphabet;
	}
	
	public static Transition randomTransition(Automate automate, boolean Etrans) {
		Transition tran;
		String id_target="q"+randomInt(1, automate.getAutomate().size());
		State target=automate.getAutomate().get(id_target);
		
		if(Etrans)
			tran=new Transition(target);
		else {
			Alphabet alpha=automate.getAlphabet().get(randomInt1(automate.getAlphabet().size()));
			tran=new Transition(alpha, target);
		}
		return tran;
	}
	
	public static int randomInt( int min ,int max) {	
		return (int)( Math.random()*( max - min + 1 ) ) + min;
	}
	
	public static int randomInt1(int max) {
		return (int)(Math.random() * max);
	}
	
	public static int randomInt(int max, boolean included) {
		if(included)
			return (int)(Math.random() * (max + 1));
		else
			return (int)(Math.random() * max);
	}
	
	public static boolean randomBool() {
		return (Math.random()<0.5);
	}
	
	public static boolean randomBool(int n) {
		return (Math.random()<(float)n/10);
	}
}