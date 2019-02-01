package automates;

import java.util.ArrayList;
import java.util.HashMap;

public class Main3 {
	public static void main(String[] args) {
		Alphabet a,b;
		State s0,s1,s2,s3;
		Transition t1,t2,t3,t4;
		Automate automate;
		
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		HashMap<String,State> states=new HashMap<String,State>();
		
		a=new Alphabet('a', false);
		b=new Alphabet('b', false);
		
		alphabets.add(a);
		alphabets.add(b);
		
		automate=new Automate(alphabets);
		
		s0=new State("0", false, true);
		s1=new State("1", true, false);
		s2=new State("2", false, false);
		s3=new State("3", false, false);
		
		//transition de 0
		t1=new Transition(a, s1);
		t2=new Transition(b, s3);
		//transition de 1
		t3=new Transition(b, s2);
		t4=new Transition(a, s3);

		try {
			s0.addTransition(t1);
			s0.addTransition(t2);
			
			s1.addTransition(t3);
			s1.addTransition(t4);
			
			s2.addTransition(t1);
			s2.addTransition(t2);
			
			s3.addTransition(t4);
			s3.addTransition(t2);
		} catch (ExistedTransitionException exp) {
			exp.printStackTrace();
		}
		states.put(s0.getId_state(), s0);
		states.put(s1.getId_state(), s1);
		states.put(s2.getId_state(), s2);
		states.put(s3.getId_state(), s3);
		
		automate.setAutomate(states);
		System.out.println(automate.transitionTableString());
		
		automate.minimisation();
		System.out.println(automate.transitionTableString());
	}
}