package automates;

import java.util.*;

public class State {

	private String id_state;
	private boolean isFinal;
	private boolean isInitial;
	protected HashSet<Transition> transition;
	
	public State() {
		this.id_state = "void";
		this.isFinal = false;
		this.isInitial = false;
		this.transition = null;
	}
	
	public State(String id_sate, boolean isFinal, boolean isInitial) {
		this.id_state=id_sate;
		this.isFinal=isFinal;
		this.isInitial=isInitial;
		transition=new HashSet<Transition>();
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

	public HashSet<Transition> getTransition() {
		return transition;
	}

	public void setTransition(HashSet<Transition> transition){
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
	
	public void deletEpsilonTransition(String id_State) {
		HashSet<Transition> newTransition=new HashSet<Transition>();
		for(Iterator<Transition>it=transition.iterator(); it.hasNext(); ) {
			Transition tran=it.next();
			if(!tran.getLabel().equals(Alphabet.epsilon_alph) || !tran.getState().getId_state().equals(id_State))
				newTransition.add(tran);			
		}
		setTransition(newTransition);
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
	
	public void copyTransition(State state) {
		for(Iterator<Transition>it=state.getTransition().iterator(); it.hasNext(); ) {
			Transition tran=it.next();
			this.transition.add(tran);
		}
	}
	
	public boolean equals(State state) {
		boolean equals;
		
		equals=this.transition.equals(state.transition) && this.isFinal==state.isFinal && this.isInitial==state.isInitial;

		return equals;
	}
	
	public String toString() {
		StringBuffer sb=new StringBuffer();
		 if(isFinal && isInitial)
			sb.append("\nState i+f("+id_state+") : {\n");
		 else if(isInitial)
			sb.append("\nState i("+id_state+") : {\n");
		 else if(isFinal)
			sb.append("\nState f("+id_state+") : {\n");
		 else
			sb.append("\nState ("+id_state+") : {\n");
		 
		 for(Iterator<Transition> it=transition.iterator(); it.hasNext(); ) {
			 sb.append("\t"+it.next().toString());
			 sb.append("\n");
		 }
		 
		 sb.append("}\n");
		 return sb.toString();
	}
}