package automates;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2 {
	public static void main(String[] args) {
		Alphabet a,b,c,d;
		State s1,s2,s3,s4,s5;
		Transition t1,t2,t3,t4,t5;
		Automate automate;
		String word;
		
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		HashMap<String,State> states=new HashMap<String,State>();
		
		a=new Alphabet('a', false);
		b=new Alphabet('b', false);
		c=new Alphabet('c', false);
		d=new Alphabet('d', false);
		
		alphabets.add(a);
		alphabets.add(b);
		alphabets.add(c);
		alphabets.add(d);
		
		word="ad";
		
		automate=new Automate(alphabets);
		
		s1=new State("1", true, true);
		s2=new State("2", false, false);
		s3=new State("3", false, true);
		s4=new State("4", false, false);
		s5=new State("5", true, false);
		//transition de s1
		t1=new Transition(a, s2);
		t2=new Transition(b, s4);
		//transition de s2+s4
		t3=new Transition(c, s5);
		t4=new Transition(d, s5);
		//transition de s3
		t5=new Transition(a, s3);

		
		try {
			s1.addTransition(t1);
			s1.addTransition(t2);
			
			s2.addTransition(t3);
			s2.addTransition(t4);
			
			s3.addTransition(t5);
			s3.addTransition(t2);
			
			s4.addTransition(t3);
			s4.addTransition(t4);
			
		} catch (ExistedTransitionException e) {
			e.printStackTrace();
		}
		states.put(s1.getId_state(), s1);
		states.put(s2.getId_state(), s2);
		states.put(s3.getId_state(), s3);
		states.put(s4.getId_state(), s4);
		states.put(s5.getId_state(), s5);
		
		automate.setAutomate(states);
		System.out.println(automate.transitionTableString());
		
		automate=automate.determinize();
		System.out.println(automate.transitionTableString());
		System.out.println("the word :("+word+")-> has been "+automate.wordRecognition(word));
	}
}
