package automates;

import java.util.*;

import turing.TuringTransition;

public class State {

	private String id_state;
	private boolean isFinal;
	private boolean isInitial;
	protected HashSet<Transition> transition;
	protected HashSet<TuringTransition> turing_transition;
	
	/**
	 * default construct
	 */
	
	public State() {
		this.id_state = "";
		this.isFinal = false;
		this.isInitial = false;
		this.transition = new HashSet<Transition>();
		this.turing_transition = new HashSet<TuringTransition>();
	}
	
	/**
	 * Construct by id_state and the boolean
	 * @param id_sate
	 * @param isFinal
	 * @param isInitial
	 */
	
	public State(String id_sate, boolean isFinal, boolean isInitial) {
		this.id_state=id_sate;
		this.isFinal=isFinal;
		this.isInitial=isInitial;
		transition=new HashSet<Transition>();
		this.turing_transition = new HashSet<TuringTransition>();
	}

	/**
	 * gives to us the id of this State
	 * @return String
	 */
	
	public String getId_state() {
		return id_state;
	}

	/**
	 * set the id of this State
	 * @param id_state
	 */
	
	public void setId_state(String id_state) {
		this.id_state = id_state;
	}

	/**
	 * Inform us if this state is final
	 * @return boolean
	 */
	
	public boolean isFinal() {
		return isFinal;
	}

	/**
	 * set final boolean
	 * @param isFinal
	 */
	
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	/**
	 * Inform us if this state is initial
	 * @return
	 */
	
	public boolean isInitial() {
		return isInitial;
	}
	
	/**
	 * set initial boolean
	 * @param isFinal
	 */

	public void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	/**
	 * get all transitions
	 * @return HashSet<Transition>
	 */
	
	public HashSet<Transition> getTransition() {
		return transition;
	}

	/**
	 * set all transitions
	 * @param transition
	 */
	
	public void setTransition(HashSet<Transition> transition){
		this.transition = transition;
	}
	
	public HashSet<TuringTransition> getTuring_transition() {
		return turing_transition;
	}
	
	public void setTuring_transition(HashSet<TuringTransition> turing_transition) {
		this.turing_transition = turing_transition;
	}
	
	/**
	 * add a transition to the group of transitions if this transitions didn't exist
	 * @param transition
	 * @throws ExistedTransitionException
	 */
	
	public void addTransition(Transition transition) throws ExistedTransitionException {
		if(!this.transition.contains(transition)) {
			this.transition.add(transition);
		}
		else {
			throw new ExistedTransitionException("La transistion : "+transition+" existe déjà dans l'état ("+id_state+")");
		}
	}
	
	public void addTuringTransition(TuringTransition transition) throws ExistedTransitionException {
		if(!this.turing_transition.contains(transition)) {
			this.turing_transition.add(transition);
		}
		else {
			throw new ExistedTransitionException("La transistion : "+transition+" existe déjà dans l'état ("+id_state+")");
		}
	}
	
	/**
	 * Delete a transition 
	 * @param transition
	 * @throws ExistedTransitionException
	 */
	
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
	
	public ArrayList<State> targetState(char symbol) {
		ArrayList<State> target_state;
		Transition tran;
		
		target_state=new ArrayList<State>();
		
		for(Iterator<Transition> it=transition.iterator(); it.hasNext(); ) {
			tran=it.next();
			
			if(tran.getLabel().getValue()==symbol) {
				target_state.add(tran.getState());
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

	public boolean isPuitsState(ArrayList<Alphabet> alphabet) {
		boolean isPuits = true;
			if(transition.size() == alphabet.size()) {
				for(Alphabet alpha : alphabet) {
					if(!existTransition(alpha, this)) {
						isPuits = false;
						break;
					}
				}
			}
			else
				isPuits = false;
		return isPuits;
	}
	
	public void complete(State puitsState, ArrayList<Alphabet> alphabet) {
		for(Alphabet alph : alphabet) {
			ArrayList<State> target = targetState(alph.getValue());
			if(target.isEmpty()) {
				Transition tran = new Transition(alph, puitsState);
				try {
					addTransition(tran);
				} catch (ExistedTransitionException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean equalsTransition(State state) {
		boolean equals=true;
			for(Iterator<Transition>it=transition.iterator(); it.hasNext(); ) {
				Transition tran=it.next();
				State target=tran.getState();
				
				ArrayList<State> s_tran=state.targetState(tran.getLabel().getValue());
				int i=0;
				boolean stop=false;
				while(i<s_tran.size() && !stop) {
					State s=s_tran.get(i);
					if(s.isFinal==target.isFinal && s.isInitial==target.isInitial)
						stop=true;
					i++;
				}
				equals=stop;
			}
		return equals;
	}
	
	public boolean equals(State state) {
		boolean equals;
		
		equals=this.equalsTransition(state) && this.isFinal==state.isFinal && this.isInitial==state.isInitial;

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