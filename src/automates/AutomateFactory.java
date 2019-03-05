package automates;

import java.util.ArrayList;
import java.util.HashMap;

import Random.Random;
import Random.Waxman;

public class AutomateFactory {
	//private static Logger logger = LoggerUtility.getLogger(AutomateFactory.class);
	
	public static Alphabet creatEpsilon(char value, boolean epsilon) {
		return new Alphabet(value, epsilon);
	}
	
	public static State creatState(String id_state, boolean isFinal, boolean isInitial) {
		return new State(id_state, isFinal, isInitial);
	}
	
	public static Automate randomADeterminist(int nb_state, int nbAlphabet, String typeAlphabet) {
		Automate auto_rand=new Automate();
			auto_rand = randomDefault(nb_state, nbAlphabet, typeAlphabet);
			if(auto_rand.testDeterminist()) {
				int j,k,i;
				Transition tran1,tran2;
				State s1,s2,s3;
				
				i = Random.randomInt(nb_state, false);
				String id_state = "q"+i;
				s1 = auto_rand.getAutomate().get(id_state);
				do {
					j = Random.randomInt(nb_state, false);
					k = Random.randomInt(nb_state, false);
					
					String id_target1 = "q"+j;
					String id_target2 = "q"+k;
					
					Alphabet alph = auto_rand.getAlphabet().get(Random.randomInt(auto_rand.getAlphabet().size(), false));
					
					s2 = auto_rand.getAutomate().get(id_target1);
					s3 = auto_rand.getAutomate().get(id_target2);
					
					tran1 = new Transition(alph, s2);
					tran2 = new Transition(alph, s3);			
				}while(k == j || (s1.getTransition().contains(tran1) && s1.getTransition().contains(tran2)));
				if(!s1.getTransition().contains(tran1)) {
					try {
						s1.addTransition(tran1);
					} catch (ExistedTransitionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(!s1.getTransition().contains(tran2)) {
					try {
						s1.addTransition(tran2);
					} catch (ExistedTransitionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(!s1.isInitial())
					s1.setInitial(true);
				else if(!s2.isInitial())
					s2.setInitial(true);
				else
					s3.setInitial(true);
			}
		return auto_rand;
	}
	
	public static Automate randomASynchrone(int nb_state, int nbAlphabet, String typeAlphabet) {
		Automate auto_rand=new Automate();
		ArrayList<Alphabet> alphabets = AutomateRepository.randomAlphabet(nbAlphabet-1, typeAlphabet);
		alphabets.add(Alphabet.epsilon_alph);
		auto_rand.setAlphabet(alphabets);
		Waxman waxman = new Waxman(nb_state, alphabets);
		
		auto_rand.setAutomate(convertAutomate(waxman.getMatrix(), waxman.getValue(), waxman.getSize()));
		return auto_rand;
	}
	
	public static Automate randomAMinimal(int nb_state, int nbAlphabet, String typeAlphabet) {
		Automate auto_rand=new Automate();

		return auto_rand;
	}
	
	public static Automate randomDefault(int nb_state, int nbAlphabet, String typeAlphabet) {
		Automate auto_rand=new Automate();
		ArrayList<Alphabet> alphabets = AutomateRepository.randomAlphabet(nbAlphabet, typeAlphabet);
		auto_rand.setAlphabet(alphabets);
		Waxman waxman = new Waxman(nb_state, alphabets);
		
		auto_rand.setAutomate(convertAutomate(waxman.getMatrix(), waxman.getValue(), waxman.getSize()));
		return auto_rand;
	}
	
	public static Automate randomAutomate(int nb_state, int nbAlphabet, String typeAlphabet,String type) {
		Automate auto_rand;
		switch(type) {
			case "ADeterminist":
				auto_rand=randomADeterminist(nb_state, nbAlphabet, typeAlphabet);
			case "Asynchrone":
				auto_rand=randomASynchrone(nb_state, nbAlphabet, typeAlphabet);
			case"Aminimal":
				auto_rand=randomAMinimal(nb_state, nbAlphabet, typeAlphabet);
			default:
				auto_rand=randomDefault(nb_state, nbAlphabet, typeAlphabet);
		}
		return auto_rand;
	}
	
	public static HashMap<String, State> convertAutomate(int[][] matrix, Alphabet[][] value, int size) {
		HashMap<String, State> states = new HashMap<String, State>();
			for(int s = 0; s < size; s++) {
				String id_state = "q"+s;
				State state;
				if(s==0)
					state = new State(id_state, false, true);
				else if(s == size-1)
					state = new State(id_state, true, false);
				else
					state = new State(id_state, false, false);
				states.put(id_state, state);
			}
			
			for(int s = 0; s < size; s++) {
				String id_state1 = "q"+s;
				for(int t = 0; t < size; t++) {
					String id_state2 = "q"+t;
					if(matrix[s][t] == 1) {
						State target = states.get(id_state2);
						Transition tran = new Transition(value[s][t], target);
						try {
							states.get(id_state1).addTransition(tran);
						} catch (ExistedTransitionException e) {
							e.printStackTrace();
						}
					}
				}
			}
		return states;
	}
}
