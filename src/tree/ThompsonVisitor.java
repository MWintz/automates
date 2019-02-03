package tree;

import java.util.Stack;

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

public class ThompsonVisitor implements TreeVisitor<Automate>{
	private int nb_state=1;
	private String id_state;
	private Stack<automates.Alphabet> alph_lef=new Stack<automates.Alphabet>();
	private automates.Alphabet tran_alph,alph_rig;
	
	
	private String get_idState() {
		id_state="q"+nb_state;
		nb_state++;
		return id_state;
	}
	
	@Override
	public Automate visit(Alphabet node) {
		tran_alph=new automates.Alphabet(node.getValue(), false);
		return null;
	}

	@Override
	public Automate visit(Etoile node) {
		Automate left_automate=node.getLeftAlphabet().accept(this);
		State si,sf,s1,s2;
		Transition tran1,tran2,tran_spe;
		try {	
			if(left_automate==null) {
					left_automate=new Automate();
					left_automate.addAlphabet(tran_alph);
					left_automate.addAlphabet(automates.Alphabet.epsilon_alph);
					
					si=new State(get_idState(), false, true);
					s1=new State(get_idState(), false, false);
					s2=new State(get_idState(), false, false);
					sf=new State(get_idState(), true, false);
					
					tran1=new Transition(s1);
					tran2=new Transition(sf);
					tran_spe=new Transition(tran_alph, s2);
					
					si.addTransition(tran2);
					si.addTransition(tran1);
					s2.addTransition(tran2);
					s2.addTransition(tran1);
					s1.addTransition(tran_spe);
					
					left_automate.getAutomate().put(si.getId_state(), si);
					left_automate.getAutomate().put(s1.getId_state(), s1);
					left_automate.getAutomate().put(s2.getId_state(), s2);
					left_automate.getAutomate().put(sf.getId_state(), sf);
				}
				else {
					left_automate.addAlphabet(automates.Alphabet.epsilon_alph);
					si=new State(get_idState(), false, true);
					sf=new State(get_idState(), true, false);
					State state_final_left=left_automate.getInitial_Final_State("final").get(0);
					State state_initial_left=left_automate.getInitial_Final_State("initial").get(0);
					
					state_final_left.setFinal(false);
					state_initial_left.setInitial(false);
					
					tran1=new Transition(state_initial_left);
					tran2=new Transition(sf);
					
					state_final_left.addTransition(tran1);
					state_final_left.addTransition(tran2);
					si.addTransition(tran1);
					si.addTransition(tran2);
					
					left_automate.getAutomate().put(si.getId_state(), si);
					left_automate.getAutomate().put(sf.getId_state(), sf);
				}
		} catch (ExistedTransitionException e) {
			e.printStackTrace();
		}
		return left_automate;
	}

	@Override
	public Automate visit(Union node) {
		Automate left_automate=node.getLeftAlphabet().accept(this);
		Automate right_automate=node.getRightAlphabet().accept(this);
		
		return null;
	}

	@Override
	public Automate visit(Concatenation node) {
		Automate left_automate=node.getLeftAlphabet().accept(this);
		if(left_automate==null) {
			alph_lef.push(tran_alph);
		}
		Automate right_automate=node.getRightAlphabet().accept(this);
		if(right_automate==null) {
			alph_rig=tran_alph;
		}
		try {	
			if(left_automate==null && right_automate==null) {
				left_automate=new Automate();
				automates.Alphabet alph=alph_lef.pop();
				left_automate.addAlphabet(alph);
				left_automate.addAlphabet(alph_rig);
				
				State si=new State(get_idState(), false, true);
				State s1=new State(get_idState(), false, false);
				State sf=new State(get_idState(), true, false);
				
				Transition tran1=new Transition(alph, s1);
				Transition tran2=new Transition(alph_rig, sf);
				
				si.addTransition(tran1);
				s1.addTransition(tran2);
				left_automate.getAutomate().put(si.getId_state(), si);
				left_automate.getAutomate().put(s1.getId_state(), s1);
				left_automate.getAutomate().put(sf.getId_state(), sf);
			}
			else if(left_automate!=null && right_automate==null) {
				left_automate.addAlphabet(alph_rig);
				State sf=left_automate.getInitial_Final_State("final").get(0);
				State new_sf=new State(get_idState(), true, false);
				
				Transition tran1=new Transition(alph_rig, new_sf);
				
				sf.setFinal(false);
				sf.addTransition(tran1);
				left_automate.getAutomate().put(new_sf.getId_state(), new_sf);
				
			}
			else if(left_automate==null && right_automate!=null) {
				automates.Alphabet alph=alph_lef.pop();
				right_automate.addAlphabet(alph);
				State si=right_automate.getInitial_Final_State("initial").get(0);
				State new_si=new State(get_idState(), false, true);
				
				Transition tran1=new Transition(alph, si);
				
				si.setInitial(false);
				new_si.addTransition(tran1);
				right_automate.getAutomate().put(new_si.getId_state(), new_si);
				left_automate=right_automate;
			}
			else {
				State si=right_automate.getInitial_Final_State("initial").get(0);
				
				for(State state:left_automate.getAutomate().values()) {
					if(state.isFinal()) {
						state.setFinal(false);
						state.setTransition(si.getTransition());
					}
				}
				for(State state:right_automate.getAutomate().values()) {
					if(!state.equals(si))
						left_automate.getAutomate().put(state.getId_state(), state);
				}
				for(automates.Alphabet alpha:right_automate.getAlphabet())
					left_automate.addAlphabet(alpha);
			}
		} catch(ExistedTransitionException e) {
			e.printStackTrace();
		}
		return left_automate;
	}
}