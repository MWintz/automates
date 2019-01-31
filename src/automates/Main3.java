package automates;

import java.util.ArrayList;
import java.util.HashMap;

public class Main3 {
	public static void main(String[] args) {
		Alphabet zero,un;
		State a,b,c,d,e,f;
		Transition t1,t2,t3,t4,t5,t6,t7;
		Automate automate;
		
		ArrayList<Alphabet> alphabets=new ArrayList<Alphabet>();
		HashMap<String,State> states=new HashMap<String,State>();
		
		zero=new Alphabet('0', false);
		un=new Alphabet('1', false);
		
		alphabets.add(zero);
		alphabets.add(un);
		
		automate=new Automate(alphabets);
		
		a=new State("a", false, true);
		b=new State("b", false, false);
		c=new State("c", true, false);
		d=new State("d", true, false);
		e=new State("e", true, false);
		f=new State("f", false, false);
		
		//transition de a
		t1=new Transition(zero, b);
		t2=new Transition(un, c);
		//transition de b
		t3=new Transition(zero, a);
		t4=new Transition(un, d);
		//transition de c
		t5=new Transition(zero, e);
		t6=new Transition(un, f);
		//transition de f
		t7=new Transition(zero, f);

		try {
			a.addTransition(t1);
			a.addTransition(t2);
			
			b.addTransition(t3);
			b.addTransition(t4);
			
			c.addTransition(t5);
			c.addTransition(t6);
			
			d.addTransition(t5);
			d.addTransition(t6);
			
			e.addTransition(t5);
			e.addTransition(t6);

			f.addTransition(t6);
			f.addTransition(t7);
		} catch (ExistedTransitionException exp) {
			exp.printStackTrace();
		}
		states.put(a.getId_state(), a);
		states.put(b.getId_state(), b);
		states.put(c.getId_state(), c);
		states.put(d.getId_state(), d);
		states.put(e.getId_state(), e);
		states.put(f.getId_state(), f);
		
		automate.setAutomate(states);
		System.out.println(automate.transitionTableString());
		
		automate=automate.minimisation();
		System.out.println(automate.transitionTableString());
	}
}