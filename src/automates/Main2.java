package automates;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2 {
	public static void main(String[] args) {
		Alphabet a,b;
		State s0,s1,s2;
		Transition t1,t2,t3,t4,t5,t6;
		Automate automate;
		String word;
		
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		HashMap<String,State> states=new HashMap<String,State>();
		
		a=new Alphabet('a', false);
		b=new Alphabet('b', false);
		
		alphabets.add(a);
		alphabets.add(b);
		
		word="abb";
		
		automate=new Automate(alphabets);
		
		s0=new State("0", false, true);
		s1=new State("1", false, true);
		s2=new State("2", true, true);
		//transition de s0
		t1=new Transition(a, s1);
		//transition de s1
		t2=new Transition(b, s1);
		t3=new Transition(b, s2);
		//transition de s2
		t4=new Transition(b, s1);
		t5=new Transition(a, s2);
		t6=new Transition(b, s2);

		
		try {
			s0.addTransition(t1);
			
			s1.addTransition(t2);
			s1.addTransition(t3);
			
			s2.addTransition(t4);
			s2.addTransition(t5);
			s2.addTransition(t6);
			
		} catch (ExistedTransitionException e) {
			e.printStackTrace();
		}
		
		states.put(s0.getId_state(), s0);
		states.put(s1.getId_state(), s1);
		states.put(s2.getId_state(), s2);
		
		automate.setAutomate(states);
		System.out.println(automate.transitionTableString());
		
		automate=automate.determinize();
		System.out.println(automate.transitionTableString());
		System.out.println("the word :("+word+")-> has been "+automate.wordRecognition(word));
	}
}
