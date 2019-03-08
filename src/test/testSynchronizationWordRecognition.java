package test;

import java.util.ArrayList;
import java.util.HashMap;

import automates.Alphabet;
import automates.Automate;
import automates.ExistedTransitionException;
import automates.State;
import automates.Transition;

public class testSynchronizationWordRecognition {

	public static void main(String[] args) {
		Alphabet a,b,c,epsilon;
		State s1,s2,s3;
		Transition t1,t2,t3,t4,t5;
		Automate automate;
		String word;
		
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		HashMap<String,State> states=new HashMap<String,State>();
		
		a=new Alphabet('a', false);
		b=new Alphabet('b', false);
		c=new Alphabet('c', false);
		epsilon=Alphabet.epsilon_alph;
		
		word="abcd";
		
		alphabets.add(a);
		alphabets.add(b);
		alphabets.add(c);
		alphabets.add(epsilon);
		
		automate=new Automate(alphabets);
		
		s1=new State("1", false, true);
		s2=new State("2", false, false);
		s3=new State("3", true, false);
		//transition de s1
		t1=new Transition(a, s1);
		t2=new Transition(s2);
		//transition de s2
		t3=new Transition(b,s2);
		t4=new Transition(s3);
		//transition de s3
		t5=new Transition(c,s3);

		
		try {
			s1.addTransition(t1);
			s1.addTransition(t2);
			
			s2.addTransition(t3);
			s2.addTransition(t4);
			
			s3.addTransition(t5);
			
		} catch (ExistedTransitionException e) {
			e.printStackTrace();
		}
		
		states.put(s1.getId_state(), s1);
		states.put(s2.getId_state(), s2);
		states.put(s3.getId_state(), s3);
		
		automate.setAutomate(states);
		
		System.out.println(automate.transitionTableString());
		automate.synchronization();
		System.out.println(automate.transitionTableString());
		System.out.println("the word :("+word+")-> has been "+automate.wordRecognition(word));
	}
}