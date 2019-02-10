package automates;

import java.util.ArrayList;
import java.util.HashMap;

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
		//TODO random Adeteminist automate
		return auto_rand;
	}
	
	public static Automate randomASynchrone(int nb_state, int nbAlphabet, String typeAlphabet) {
		Automate auto_rand=new Automate();
		ArrayList<Alphabet> alphabet=AutomateRepository.randomAlphabet(nbAlphabet, typeAlphabet);
		HashMap<String, State> states=new HashMap<String, State>();
		
		alphabet.add(Alphabet.epsilon_alph);
		auto_rand.setAlphabet(alphabet);
		
		for(int i=1; i<=nb_state; i++) {
			String id_state="q"+i;
			State s;
			
			if(i==1) {
				s=new State(id_state, false, true);
			}
			else if(i==nb_state) {
				s=new State(id_state, true, false);
			}
			else {
				s=new State(id_state, false, false);
			}
			states.put(s.getId_state(), s);
		}
		auto_rand.setAutomate(states);
		
		int rand_nb_tran=AutomateRepository.randomInt(nb_state+nbAlphabet, nb_state*nbAlphabet);
		for(int i=1; i<rand_nb_tran; i++) {
			boolean isETran=AutomateRepository.randomBool();
			String id_state="q"+AutomateRepository.randomInt(1, auto_rand.getAutomate().size());
			Transition tran=AutomateRepository.randomTransition(auto_rand, isETran);
			try {
				auto_rand.getAutomate().get(id_state).addTransition(tran);
			} catch (ExistedTransitionException e) {
				e.printStackTrace();
			}
		}
		
		return auto_rand;
	}
	
	public static Automate randomAMinimal(int nb_state, int nbAlphabet, String typeAlphabet) {
		Automate auto_rand=new Automate();
		//TODO random Aminimal automate
		return auto_rand;
	}
	
	public static Automate randomDefault(int nb_state, int nbAlphabet, String typeAlphabet) {
		Automate auto_rand=new Automate();
		//TODO random default type automate
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
}
