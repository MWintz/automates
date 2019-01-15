package automates;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Alphabet a,b,c,epsilon;
		State s1,s2,s3;
		Transition t1,t2,t3,t4,t5;
		Automate automate,automate1;
		String word;
		
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		HashMap<String,State> states=new HashMap<String,State>();
		HashMap<String,State> states1=new HashMap<String,State>();
		
		a=new Alphabet('a', false);
		b=new Alphabet('b', false);
		c=new Alphabet('c', false);
		epsilon=Alphabet.epsilon_alph;
		
		word="abc";
		
		alphabets.add(a);
		alphabets.add(b);
		alphabets.add(c);
		alphabets.add(epsilon);
		
		automate=new Automate(alphabets);
		automate1=new Automate(alphabets);
		
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
		
		s1.setId_state("10");
		states1.put(s1.getId_state(), s1);
		states1.put(s2.getId_state(), s2);
		states1.put(s3.getId_state(), s3);
		
		automate.setAutomate(states);
		automate1.setAutomate(states1);
		
		System.out.println(automate.transitionTableString());
		automate.synchronization();
		System.out.println(automate.transitionTableString());
		System.out.println("the word :("+word+")-> has been "+automate.wordRecognition(word));
		System.out.println(automate1.transitionTableString());
		System.out.println("the tow automates are: "+automate.equals(automate1, "OUI"));
	}
}