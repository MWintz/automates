package automates;

public class AutomateFactory {
	//private static Logger logger = LoggerUtility.getLogger(AutomateFactory.class);
	
	public static Alphabet creatEpsilon(char value, boolean epsilon) {
		return new Alphabet(value, epsilon);
	}
	
	public static State creatState(String id_state, boolean isFinal, boolean isInitial) {
		return new State(id_state, isFinal, isInitial);
	}
	
	public static Automate randomADeterminist(int nb_state) {
		Automate auto_rand=new Automate();
		//TODO random Adeteminist automate
		return auto_rand;
	}
	
	public static Automate randomASynchrone(int nb_state) {
		Automate auto_rand=new Automate();
		//TODO random Asynchrone automate
		return auto_rand;
	}
	
	public static Automate randomAMinimal(int nb_state) {
		Automate auto_rand=new Automate();
		//TODO random Aminimal automate
		return auto_rand;
	}
	
	public static Automate randomDefault(int nb_state) {
		Automate auto_rand=new Automate();
		//TODO random default type automate
		return auto_rand;
	}
	
	public static Automate randomAutomate(int nb_state, String type) {
		Automate auto_rand;
		switch(type) {
			case "ADeterminist":
				auto_rand=randomADeterminist(nb_state);
			case "Asynchrone":
				auto_rand=randomASynchrone(nb_state);
			case"Aminimal":
				auto_rand=randomAMinimal(nb_state);
			default:
				auto_rand=randomDefault(nb_state);
		}
		return auto_rand;
	}
}
