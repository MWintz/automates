package automates;

import java.util.ArrayList;
import java.util.Iterator;

public class State {

	private String id_state;
	private boolean isFinal;
	private boolean isInitial;
	private ArrayList<Transition> transition;//
	
	public State(String id_sate, boolean isFinal, boolean isInitial) {
		this.id_state=id_sate;
		this.isFinal=isFinal;
		this.isInitial=isInitial;
		transition=new ArrayList<Transition>();
	}

	public String getId_state() {
		return id_state;
	}

	public void setId_state(String id_state) {
		this.id_state = id_state;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isInitial() {
		return isInitial;
	}

	public void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	public ArrayList<Transition> getTransition() {
		return transition;
	}

	public void setTransition(ArrayList<Transition> transition) throws ExistedTransitionException{
		this.transition = transition;
	}
	
	public void addTransition(Transition transition) throws ExistedTransitionException {
		if(!this.transition.contains(transition)) {
			this.transition.add(transition);
		}
		else {
			throw new ExistedTransitionException("La transistion : "+transition+" existe déjà dans l'état ("+id_state+")");
		}
	}
	
	public void deletTransition(Transition transition) throws ExistedTransitionException {
		if(this.transition.contains(transition)) {
			this.transition.remove(transition);
		}
		else {
			throw new ExistedTransitionException("La transistion : "+transition+" n'existe pas dans l'état ("+id_state+")");
		}
	}
	
	public boolean existTransition(Alphabet symbol, State target_state) {
		boolean exist;
		Transition tran0,tran1;
		
		exist=false;
		tran1=new Transition(symbol, target_state);
		
		for(Iterator<Transition> it=transition.iterator(); it.hasNext(); ) {
			tran0=it.next();
			
			if(tran0.equals(tran1)) {
				exist=true;
			}
		}
		
		return exist;
	}
	
	public State targetState(char symbol) {
		State target_state;
		Transition tran;
		
		target_state=null;
		
		for(Iterator<Transition> it=transition.iterator(); it.hasNext(); ) {
			tran=it.next();
			
			if(tran.getLabel().getValue()==symbol) {
				target_state=tran.getState();
			}
		}
		
		return target_state;
	}
	
	public boolean equals(State state) {
		boolean equals;
		
		equals=this.transition.equals(state.transition) && this.isFinal==state.isFinal && this.isInitial==state.isInitial;

		return equals;
	}
	
	public String toString() {
		 StringBuffer sb=new StringBuffer("\nState ("+id_state+") : {\n");
		 
		 for(Iterator<Transition> it=transition.iterator(); it.hasNext(); ) {
			 sb.append("\t"+it.next().toString());
			 sb.append("\n");
		 }
		 
		 sb.append("}\n");
		 return sb.toString();
	}
}