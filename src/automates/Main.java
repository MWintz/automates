package automates;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Alphabet a,b,epsilon;
		State s1,s2,s3,s4,s5,s6;
		Transition t1,t2,t3,t4,t5,t6,t7;
		Automate automate;
		String word;
		
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		HashMap<String,State> states=new HashMap<String,State>();
		
		a=new Alphabet('a', false);
		b=new Alphabet('b', false);
		epsilon=new Alphabet();
		
		word="abaaba";
		
		alphabets.add(a);
		alphabets.add(b);
		alphabets.add(epsilon);
		
		automate=new Automate(alphabets);
		
		s1=new State("1", false, false);
		s2=new State("2", false, false);
		s3=new State("3", false, false);
		s4=new State("4", false, false);
		s5=new State("5", true, true);
		s6=new State("6", false, false);
		
		//transition de s1
		t1=new Transition(a, s2);
		//transition de s2
		t2=new Transition(epsilon,s6);
		//transition de s3
		t3=new Transition(epsilon,s6);
		//transition de s4
		t4=new Transition(b,s3);
		//transition de s5
		t5=new Transition(epsilon,s1);
		t6=new Transition(epsilon,s4);
		//transition de s6
		t7=new Transition(epsilon,s5);
		
		try {
			s1.addTransition(t1);
			
			s2.addTransition(t2);
			
			s3.addTransition(t3);
			
			s4.addTransition(t4);
			
			s5.addTransition(t5);
			s5.addTransition(t6);
			
			s6.addTransition(t7);
		} catch (ExistedTransitionException e) {
			e.printStackTrace();
		}
		
		states.put(s1.getId_state(), s1);
		states.put(s2.getId_state(), s2);
		states.put(s3.getId_state(), s3);
		states.put(s4.getId_state(), s4);
		states.put(s5.getId_state(), s5);
		states.put(s6.getId_state(), s6);
		
		automate.setAutomate(states);
		automate.transitionTable();
		
		System.out.println(automate.transitionTableString());
		System.out.println("the word :("+word+")-> has been "+automate.wordRecognition(word));
	}
}