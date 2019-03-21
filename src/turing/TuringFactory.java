package turing;

import automates.Alphabet;
import automates.Automate;
import automates.AutomateRepository;
import automates.ExistedTransitionException;
import automates.State;

public class TuringFactory {
	
	public static Automate reverse0_1() {
		Automate reverse_automate = new Automate();
		reverse_automate.setAlphabet(AutomateRepository.randomAlphabet(2, "BINARY"));
		Alphabet tuAlphabetNull = new Alphabet(TuringMachin.NULL, false);
		reverse_automate.getAlphabet().add(tuAlphabetNull);
		
		State e1 = new State("e1", false, true);
		State e2 = new State("e2", false, false);
		State e3 = new State("e3", true, false);
		
		TuringTransition tran1 = new TuringTransition(tuAlphabetNull, tuAlphabetNull, +1, e2);
		TuringTransition tran2 = new TuringTransition(AutomateRepository.ZERO, AutomateRepository.UN, +1, e2);
		TuringTransition tran3 = new TuringTransition(AutomateRepository.UN, AutomateRepository.ZERO, +1, e2);
		TuringTransition tran4 = new TuringTransition(tuAlphabetNull, tuAlphabetNull, +1, e3);
		
		try {
			e1.addTuringTransition(tran1);
			e2.addTuringTransition(tran2);
			e2.addTuringTransition(tran3);
			e2.addTuringTransition(tran4);
		} catch (ExistedTransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reverse_automate.getAutomate().put(e1.getId_state(), e1);
		reverse_automate.getAutomate().put(e2.getId_state(), e2);
		reverse_automate.getAutomate().put(e3.getId_state(), e3);
		
		return reverse_automate;
	}

	public static Automate add1() {
		Automate add_automate = new Automate();
		add_automate.setAlphabet(AutomateRepository.randomAlphabet(2, "BINARY"));
		Alphabet tuAlphabetNull = new Alphabet(TuringMachin.NULL, false);
		add_automate.getAlphabet().add(tuAlphabetNull);
		
		State e1 = new State("e1", false, true);
		State e2 = new State("e2", false, false);
		State e3 = new State("e3", false, false);
		State e4 = new State("e4", true, false);
		//e1
		TuringTransition tran1 = new TuringTransition(tuAlphabetNull, tuAlphabetNull, +1, e2);
		//e2
		TuringTransition tran2 = new TuringTransition(AutomateRepository.ZERO, AutomateRepository.ZERO, +1, e2);
		TuringTransition tran3 = new TuringTransition(AutomateRepository.UN, AutomateRepository.UN, +1, e2);
		TuringTransition tran4 = new TuringTransition(tuAlphabetNull, tuAlphabetNull, -1, e3);
		//e3
		TuringTransition tran5 = new TuringTransition(AutomateRepository.ZERO, AutomateRepository.UN, -1, e4);
		TuringTransition tran6 = new TuringTransition(AutomateRepository.UN, AutomateRepository.ZERO, -1, e3);
		TuringTransition tran7 = new TuringTransition(tuAlphabetNull, AutomateRepository.UN, -1, e4);
		
		try {
			e1.addTuringTransition(tran1);
			e2.addTuringTransition(tran2);
			e2.addTuringTransition(tran3);
			e2.addTuringTransition(tran4);
			e3.addTuringTransition(tran5);
			e3.addTuringTransition(tran6);
			e3.addTuringTransition(tran7);
		} catch (ExistedTransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add_automate.getAutomate().put(e1.getId_state(), e1);
		add_automate.getAutomate().put(e2.getId_state(), e2);
		add_automate.getAutomate().put(e3.getId_state(), e3);
		add_automate.getAutomate().put(e4.getId_state(), e4);
		
		return add_automate;
	}

	public static Automate sub1() {
		Automate sub_automate = new Automate();
		sub_automate.setAlphabet(AutomateRepository.randomAlphabet(2, "BINARY"));
		Alphabet tuAlphabetNull = new Alphabet(TuringMachin.NULL, false);
		sub_automate.getAlphabet().add(tuAlphabetNull);
		
		State e1 = new State("e1", false, true);
		State e2 = new State("e2", false, false);
		State e3 = new State("e3", false, false);
		State e4 = new State("e4", true, false);
		//e1
		TuringTransition tran1 = new TuringTransition(tuAlphabetNull, tuAlphabetNull, +1, e2);
		//e2
		TuringTransition tran2 = new TuringTransition(AutomateRepository.ZERO, AutomateRepository.ZERO, +1, e2);
		TuringTransition tran3 = new TuringTransition(AutomateRepository.UN, AutomateRepository.UN, +1, e2);
		TuringTransition tran4 = new TuringTransition(tuAlphabetNull, tuAlphabetNull, -1, e3);
		//e3
		TuringTransition tran5 = new TuringTransition(AutomateRepository.ZERO, AutomateRepository.UN, -1, e3);
		TuringTransition tran6 = new TuringTransition(AutomateRepository.UN, AutomateRepository.ZERO, -1, e4);
		TuringTransition tran7 = new TuringTransition(tuAlphabetNull, tuAlphabetNull, -1, e4);
		
		try {
			e1.addTuringTransition(tran1);
			e2.addTuringTransition(tran2);
			e2.addTuringTransition(tran3);
			e2.addTuringTransition(tran4);
			e3.addTuringTransition(tran5);
			e3.addTuringTransition(tran6);
			e3.addTuringTransition(tran7);
		} catch (ExistedTransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sub_automate.getAutomate().put(e1.getId_state(), e1);
		sub_automate.getAutomate().put(e2.getId_state(), e2);
		sub_automate.getAutomate().put(e3.getId_state(), e3);
		sub_automate.getAutomate().put(e4.getId_state(), e4);	
		return sub_automate;
	}

	public static Automate mult2() {
		Automate mult_automate = new Automate();
		mult_automate.setAlphabet(AutomateRepository.randomAlphabet(2, "BINARY"));
		Alphabet tuAlphabetNull = new Alphabet(TuringMachin.NULL, false);
		mult_automate.getAlphabet().add(tuAlphabetNull);
		
		State e1 = new State("e1", false, true);
		State e2 = new State("e2", false, false);
		State e3 = new State("e3", true, false);
		
		TuringTransition tran1 = new TuringTransition(tuAlphabetNull, tuAlphabetNull, +1, e2);
		TuringTransition tran2 = new TuringTransition(AutomateRepository.ZERO, AutomateRepository.ZERO, +1, e2);
		TuringTransition tran3 = new TuringTransition(AutomateRepository.UN, AutomateRepository.UN, +1, e2);
		TuringTransition tran4 = new TuringTransition(tuAlphabetNull, AutomateRepository.ZERO, +1, e3);
		
		try {
			e1.addTuringTransition(tran1);
			e2.addTuringTransition(tran2);
			e2.addTuringTransition(tran3);
			e2.addTuringTransition(tran4);
		} catch (ExistedTransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mult_automate.getAutomate().put(e1.getId_state(), e1);
		mult_automate.getAutomate().put(e2.getId_state(), e2);
		mult_automate.getAutomate().put(e3.getId_state(), e3);
		
		return mult_automate;
	}
}
