package tree;

import automates.Automate;
import automates.ExistedTransitionException;
import automates.State;
import automates.Transition;
import expression.Alphabet;
import expression.Concatenation;
import expression.Etoile;
import expression.Union;

/**
 * @author ishakayad96@outlook.fr
 */

public class ThompsonVisitor implements TreeVisitor<Void>{
	private Automate thompson_automate=new  Automate();
	private int nb_state=0;
	String id_state;
	private automates.Alphabet tran_alph,lef_alph,righ_alph;
	
	public Automate getThompsonAutomate() {
		return thompson_automate;
	}
	
	private String get_idState() {
		id_state="q"+nb_state;
		nb_state++;
		return id_state;
	}
	
	@Override
	public Void visit(Alphabet node) {
		tran_alph=new automates.Alphabet(node.getValue(), false);
		thompson_automate.getAlphabet().add(tran_alph);
		return null;
	}

	@Override
	public Void visit(Etoile node) {
		node.getLeftAlphabet().accept(this);
		
		State si,sf,s1,s2;
		Transition tran1,tran2;
		try {
			si=new State(get_idState(), false, true);
			sf=new State(get_idState(), true, false);
			if(thompson_automate.getAutomate().isEmpty()) {
				s1=new State(get_idState(), false, false);
				s2=new State(get_idState(), false, false);
				
				tran1=new Transition(s1);
				tran2=new Transition(sf);
				
				si.addTransition(tran2);
				si.addTransition(tran1);
				s2.addTransition(tran2);
				s2.addTransition(tran1);
				Transition tran_spe=new Transition(tran_alph, s2);
					s1.addTransition(tran_spe);
			
				thompson_automate.getAutomate().put(s1.getId_state(), s1);
				thompson_automate.getAutomate().put(s2.getId_state(), s2);
			}
			else {
				 s1=thompson_automate.getInitial_Final_State("initial").get(0);
				 s2=thompson_automate.getInitial_Final_State("final").get(0);
				 s1.setInitial(false);
				 s2.setFinal(false);
				 
				 tran1=new Transition(s1);
				 tran2=new Transition(sf);
				 
				 si.addTransition(tran2);
				 si.addTransition(tran1);
				 s2.addTransition(tran2);
				 s2.addTransition(tran1);
			}
			thompson_automate.getAutomate().put(si.getId_state(), si);
			thompson_automate.getAutomate().put(sf.getId_state(), sf);
		} catch (ExistedTransitionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Void visit(Union node) {
		node.getLeftAlphabet().accept(this);
		node.getRightAlphabet().accept(this);
		return null;
	}

	@Override
	public Void visit(Concatenation node) {
		node.getLeftAlphabet().accept(this);
		lef_alph=this.tran_alph;
		node.getRightAlphabet().accept(this);
		righ_alph=this.tran_alph;
		
		State si,sf,s1;
		Transition tran1,tran2;
		try {
			si=new State(get_idState(), false, true);
			sf=new State(get_idState(), true, false);
			if(thompson_automate.getAutomate().isEmpty()) {
				s1=new State(get_idState(), false, false);
				
				tran1=new Transition(lef_alph, s1);
				tran2=new Transition(righ_alph, sf);
				
				si.addTransition(tran1);
				s1.addTransition(tran2);
			
				thompson_automate.getAutomate().put(si.getId_state(), si);
				thompson_automate.getAutomate().put(s1.getId_state(), s1);
				thompson_automate.getAutomate().put(sf.getId_state(), sf);
			}
		} catch (ExistedTransitionException e) {
			e.printStackTrace();
		}
		return null;
	}
}