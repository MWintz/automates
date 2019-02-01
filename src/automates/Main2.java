package automates;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2 {
	public static void main(String[] args) {
		Alphabet a,b;
		State s0,s1,s2,s3,s4;
		Transition t1,t2,t3,t4,t5,t6;
		Automate automate;
		
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		HashMap<String,State> states=new HashMap<String,State>();
		
		a=new Alphabet('a', false);
		b=new Alphabet('b', false);
		
		alphabets.add(a);
		alphabets.add(b);
		
		automate=new Automate(alphabets);
		
		s0=new State("0", false, true);
		s1=new State("1", false, true);
		s2=new State("2", true, false);
		s3=new State("3", false, false);
		s4=new State("4", true, false);
		//transition de s0
		t1=new Transition(a, s0);
		t2=new Transition(b, s0);
		t3=new Transition(a, s1);
		//transition de s1
		t4=new Transition(a, s2);
		t5=new Transition(b, s3);
		//transition de s3
		t6=new Transition(b, s4);

		
		try {
			s0.addTransition(t1);
			s0.addTransition(t2);
			s0.addTransition(t3);
			
			s1.addTransition(t4);
			s1.addTransition(t5);
			
			s3.addTransition(t6);
			
		} catch (ExistedTransitionException e) {
			e.printStackTrace();
		}
		states.put(s0.getId_state(), s0);
		states.put(s1.getId_state(), s1);
		states.put(s2.getId_state(), s2);
		states.put(s3.getId_state(), s3);
		states.put(s4.getId_state(), s4);
		
		
		automate.setAutomate(states);
		System.out.println(automate.transitionTableString());
		System.out.println("this automate are "+automate.isDeterminist());
		
		automate.determinize();
		System.out.println(automate.transitionTableString());
		System.out.println("this automate are "+automate.isDeterminist());
	}
}
