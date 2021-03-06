package automates;

import java.util.*;

public class Automate {
	
	private ArrayList<Alphabet> alphabet;
	private HashMap<String,State> automate;
	private boolean determinist;
	
	/**
	 * Construct of an Automate
	 */
	public Automate() {
		alphabet=new ArrayList<Alphabet>();
		automate=new HashMap<String,State>();
		determinist = this.testDeterminist();
	}
	
	/**
	 * construct an automate with an initial alphabet
	 * @param alphabet
	 */
	
	public Automate(ArrayList<Alphabet> alphabet) {
		this.alphabet=alphabet;
	}
	
	/**
	 * get alphabet of the automate
	 * @return alphabet
	 */
	
	public ArrayList<Alphabet> getAlphabet() {
		return alphabet;
	}

	/**
	 * set the alphabet of the automate
	 * @param alphabet
	 */
	
	public void setAlphabet(ArrayList<Alphabet> alphabet) {
		this.alphabet = alphabet;
	}
	
	/**
	 * add an alphabet if the alphabet didn't exist
	 * @param alpha
	 */
	
	public void addAlphabet(Alphabet alpha) {
		int i=0;
		boolean find=false;
		while(i<alphabet.size() && ! find) {
			Alphabet tmp_alpha=alphabet.get(i);
			if(tmp_alpha.getValue()==alpha.getValue()){
				find=true;
			}
			i++;
		}
		if(!find)
			this.alphabet.add(alpha);
	}

	/**
	 * get all states of the automate
	 * @return automate
	 */
	
	public HashMap<String, State> getAutomate() {
		return automate;
	}

	/**
	 * set the states of the automate
	 * @param automate
	 */
	
	public void setAutomate(HashMap<String, State> automate) {
		this.automate = automate;
	}
	
	/**
	 * return a String description of the state of the automate 
	 * if it's a determinist automate or not
	 * @return String
	 */
	
	public String isDeterminist() {
		return determinist ? "Determinist" : "Not determinist";
	}

	/**
	 * set the boolean how indicate to us if an automate is determinist or not 
	 * @param determinist
	 */
	
	public void setDeterminist(boolean determinist) {
		this.determinist = determinist;
	}
	
	/**
	 * this function allow us to know if an automate is determinist or not
	 */
	public boolean testDeterminist() {
		boolean test=true;
		
		if(!this.automate.isEmpty()) {
			if(getInitial_Final_State("initial").size()!=1)
				test=false;
			else
				for(State states : automate.values())
					for(Alphabet alph : alphabet)
						if(states.targetState(alph.getValue()).size()>1)
							test=false;
		}
		
		return test;
	}

	/**
	 * gives to us a list of the initial or the final state
	 * @param type
	 * @return ArrayList<State>
	 */
	
	public ArrayList<State> getInitial_Final_State(String type) {
		ArrayList<State> initial_final_state=new ArrayList<State>();
		
		for(State states : automate.values()) {
			if(states.isInitial() && type.equals("initial")) {
				initial_final_state.add(states);
			}
			else if(states.isFinal() && type.equals("final")) {
				initial_final_state.add(states);
			}
		}	
		return initial_final_state;
	}
	
	/**
	 * this is the algorithm of word recognition
	 * @param word
	 * @return boolean
	 */
	
	public String wordRecognition(String word) {
		boolean result,stop;
		State current_state,target_state;
		char current_symbol;
		int word_index,i;
		
		//initialization
		result=false;
		stop=false;
		word=word+"$";
		ArrayList<State> as=getInitial_Final_State("initial");
		i=0; 
		
		while(i<as.size() && !result) {
			
			current_state=as.get(i);
			word_index=0;
			//treatment
			do {
				current_symbol=word.charAt(word_index);
				if(current_symbol=='$') {
					if(current_state.isFinal()) {
						result=true;
					}
					else {
						result=false;
					}
					stop=true;
				}
				else {
					ArrayList<State>tar_array=current_state.targetState(current_symbol);
					if(!tar_array.isEmpty()) {
						target_state=tar_array.get(0);
						if(target_state != null) {
							current_state=target_state;
							word_index=word_index+1;
						}
						else {
							result=false;
							stop=true;
						}
					}
					else {
						result=false;
						stop=true;
					}
				}
				
			}while(!stop);
			i++;
		}
		
		return result ? "accepted" : "not accepted";
	}
	/**
	 * this function return to us the incoming epsilon transitions of a state
	 */
	public ArrayList<State> incomingEpsilonTransitions(State state){
		ArrayList<State> incoming_state=new ArrayList<State>();
		for(State states : automate.values()) {
			if(states.existTransition(Alphabet.epsilon_alph, state))
				incoming_state.add(states);
		}	
		return incoming_state;
	}
	/**
	 * this function return to us all the incoming transitions of a state
	 */
	public ArrayList<State> incomingTransitions(State state){
		ArrayList<State> incoming_state=new ArrayList<State>();
		for(State states : automate.values()) {
			for(Iterator<Alphabet>it=alphabet.iterator(); it.hasNext(); ) {
				Alphabet al=it.next();
				if(states.existTransition(al, state))
					incoming_state.add(states);
			}
		}	
		return incoming_state;
	}
	
	/**
	 * this function allow us to get the state how have the max incoming transitions
	 * @return State
	 */
	
	public State getMaxIncomingTransitionState() {
		State maxS = null;
		int maxT = 0;
			for(State state : this.automate.values()) {
				if(incomingTransitions(state).size() > maxT) {
					maxT = incomingTransitions(state).size();
					maxS = state;
				}
			}
		return maxS;
	}
	
	/**
	 * gives us an alphabet by his value
	 * @param value
	 * @return Alphabet
	 */
	
	public Alphabet getAlphabetByChar(char value) {
		Alphabet alph_res = null;
		for(Alphabet alph : this.alphabet) {
			if(alph.getValue() == value) {
				alph_res = alph;
				break;
			}
		}
		return alph_res;
	}
	
	/**
	 * this is the algorithm of completion
	 * it calculate the puits state if the automate don't have one it will create one
	 */
	
	public void completion() {
		State puitsState = null;
		boolean foundPuitsState = false;
		for(State state : automate.values()) {
			if(state.isPuitsState(alphabet)) {
				puitsState = state;
				foundPuitsState = true;
				break;
			}
		}
		
		if(!foundPuitsState) {
			puitsState = new State("()", false, false);
			for(Alphabet alph : alphabet) {
				Transition tran = new Transition(alph, puitsState);
				try {
					puitsState.addTransition(tran);
				} catch (ExistedTransitionException e) {
					e.printStackTrace();
				}
			}
			automate.put(puitsState.getId_state(), puitsState);
		}
		
		for(State state : automate.values())
			if(!state.equals(puitsState))
				state.complete(puitsState, alphabet);
	}
	
	/**
	 * this function is used to synchronize a PLC "remove epsilon transition"
	 */
	public void synchronization() {
		ArrayList<State> incomingEpsilonState=new ArrayList<State>();
		HashMap<String,State> new_automate=new HashMap<String,State>();
		ArrayList<String> deletedStates=new ArrayList<String>();
		int number_tran=0;
		boolean delet=false;
		
		for(State states : automate.values()) {
			incomingEpsilonState=incomingEpsilonTransitions(states);
			number_tran=incomingTransitions(states).size();
			if(incomingEpsilonState!=null) {
				for(State incomingState : incomingEpsilonState) {
					if(!deletedStates.contains(incomingState.getId_state())) {
						incomingState.copyTransition(states);
						incomingState.deletEpsilonTransition(states.getId_state());
						if(states.isFinal())
							incomingState.setFinal(true);
						if(incomingState.isInitial()) {
							states.setInitial(true);
							delet=true;
						}
					}
				}
			}
			if(incomingEpsilonState.size()!=number_tran)
				new_automate.put(states.getId_state(), states);
			else {
				if(states.isInitial() && !delet)
					new_automate.put(states.getId_state(), states);
				else {
					delet=false;
					deletedStates.add(states.getId_state());
				}
			}
		}
		this.alphabet.remove(Alphabet.epsilon_alph);
		setAutomate(new_automate);
		this.determinist=testDeterminist();
	}
	
	/**
	 * This function how allow us to determinize an automate
	*/
	public void determinize() {
		HashMap<String, State> finish = new HashMap<String, State>();
		ArrayList<State> init = getInitial_Final_State("initial");
		
		HashSet<SuperState> created = new HashSet<SuperState>();
		ArrayList<SuperState> toTreat = new ArrayList<SuperState>();
		
		SuperState entry = new SuperState(false, true);
						
		for (State state : init)
			entry.addState(state);
		toTreat.add(entry);

		while (!toTreat.isEmpty()) {
			SuperState ss = toTreat.get(0);
			for (Alphabet alpha : this.alphabet) {
				SuperState newss = new SuperState("", false, false);

				for (Transition transi : ss.getTransition())
					if(transi.getLabel().equals(alpha))
						newss.addState(transi.getState());
				if (newss.containsFinal())
					newss.setFinal(true);
				if (newss.containsInitial())
					newss.setInitial(true);
				
				boolean test = false;
				for (SuperState testState : created)
					if (testState.equals(newss))
						test = true;
				if (test == false && newss.getId_state()!="") {
					created.add(newss);
					test = false;
					for (SuperState testState2 : toTreat)
						if (testState2.equals(newss)) 
							test = true;
				}
				if (test == false && newss.getId_state()!="") {
					toTreat.add(newss);
				}
				
			}
			created.add(ss);
			toTreat.remove(ss);
		}
		for (SuperState term : created) 
			finish.put(term.getId_state(), term.toState());

		this.setAutomate(finish);
		this.organiseAutomate();
		this.determinist=true;
	}

	private void organiseAutomate() {
		for(State states : automate.values()) {
			String id_state = null;
			HashSet<Transition>transition=new HashSet<Transition>();
			for(int i=0; i<alphabet.size(); i++) {
				ArrayList<State> target=states.targetState(alphabet.get(i).getValue());
				if(!target.isEmpty()) {
					for(int j=0; j<target.size(); j++)
						if(j==0)
							id_state=target.get(j).getId_state();
						else
							id_state+=","+target.get(j).getId_state();;
					State ss=automate.get(id_state);
					Transition tran=new Transition(alphabet.get(i), ss);
					transition.add(tran);
				}
			}
			states.setTransition(transition);
		}
	}
	/**
	 * this function allows us to calculate the initial states towards final and aims towards that
	 */
	public void switchInitialToFinal() {
		for (State states:automate.values()) {
			if(!states.isFinal() || !states.isInitial()) {
				if(states.isFinal()) {
					states.setFinal(false);
					states.setInitial(true);	
				}
				else if(states.isInitial()) {
					states.setFinal(true);
					states.setInitial(false);
				}
			}
		}
	}
	/**
	 * this function allows us to calculate the transpose of an automate
	 */
	public void transpose() {
		HashMap<String, State>auto_tmp=new HashMap<String, State>();
		
		switchInitialToFinal();
		for(State states:automate.values()) {
			State s_tmp=new State(states.getId_state(), states.isFinal(), states.isInitial());
			auto_tmp.put(s_tmp.getId_state(), s_tmp);
		}
		for(State states:automate.values()) {
			for(Iterator<Transition>it=states.getTransition().iterator(); it.hasNext(); ) {
				Transition tran = it.next();
				State tar_state=tran.getState();
				Alphabet alph=tran.getLabel();
				Transition new_Tran=new Transition(alph, auto_tmp.get(states.getId_state()));
				try {
					auto_tmp.get(tar_state.getId_state()).addTransition(new_Tran);
				} catch (ExistedTransitionException e) {
					e.printStackTrace();
				}
			}
		}
		setAutomate(auto_tmp);
	}
	/**
	 *  this function allow us to calculate the automate minimal of this automate
	 *  it's use the Algorithme of Brzozowski 
	 */
	public void minimisation() {
		this.transpose();
		this.determinize();
		this.transpose();
		this.determinize();
	}
	/**
	 * this function check if an alphabet are accepted by an automate represented by his alphabet
	 * @param alphabet
	 * @param alph
	 * @return true if alphabet contains alph false in the other case
	 */
	public boolean containsAlphabet(ArrayList<Alphabet> alphabet, Alphabet alph) {
		boolean contains=false;
		int i=0;
			while(!contains && i<alphabet.size()) {
				if(alphabet.get(i).equals(alph))
					contains=true;
				i++;
			}
		return contains;
	}
	/**
	 * this function allow us to know if the alphabet of two automates are the same
	 * @param a automate
	 * @return boolean true if this automate have the same alphabet with a false in other case
	 */
	public boolean equalsAlphabet(Automate a) {
		ArrayList<Alphabet> a_alpha=a.getAlphabet();
		boolean equalAlphabet=a_alpha.size()==alphabet.size();
		int i=0;
			while(equalAlphabet && i<alphabet.size()) {
				Alphabet alph=alphabet.get(i);
				if(!containsAlphabet(a_alpha, alph)) {
					equalAlphabet=false;
				}
				i++;
			}
		return equalAlphabet;
	}
	/**
	 * this function allow us to know if tow automates are equals
	 * @param a
	 * @return equals true if this automate is equals to a
	 */
	public String equals(Automate a) {
		boolean equals=equalsAlphabet(a);
			if(equals) {
				equals=a.getAutomate().size()==automate.size();
				
				for(State this_s:automate.values()) {
					boolean check=false;
					for(State a_s:a.getAutomate().values()) {
						if(this_s.equals(a_s))
							check=true;
					}
					equals=check;
				}
			}
		return equals ? "euqals" : "not equals";
	}
	/**
	 * this function shows us the transition table of an PLC
	 */
	public String[][] transitionTable() {
		String table_tran[][]=new String[automate.size()+1][alphabet.size()+1];
		int j;

		for(int i=1; i<alphabet.size()+1; i++)
			table_tran[0][i]=alphabet.get(i-1).toString();
		j=1;
		for(State states : automate.values()) {
			table_tran[j][0]=states.getId_state();
			for(int i=1; i<alphabet.size()+1; i++) {
				ArrayList<State> target=states.targetState(table_tran[0][i].charAt(0));
				if(target!=null) {
					for (int k=0; k<target.size(); k++) {
						if(k==0)
							table_tran[j][i]=target.get(k).getId_state();	
						else {
							table_tran[j][i]+=",";
							table_tran[j][i]+=target.get(k).getId_state();
						}
					}
				}
			}
			j++;
		}
		return table_tran;
	}
	/**
	 * this function print the table of transitions
	 * @return String
	 */
	public String transitionTableString() {
		StringBuffer sb=new StringBuffer("Automate : alphabet={" + alphabet + "}\n");
		String table_tran[][]=transitionTable();
		
		for(int i=0; i<automate.size()+1; i++) {
			for(int j=0; j<alphabet.size()+1; j++) {
				if(i==0 && j==0)
					sb.append("\t");
				else if(table_tran[i][j]==null)
					sb.append("\t°");
				else
					sb.append("\t"+table_tran[i][j]);
			}
			sb.append("\n");
		}	
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer("Automate : alphabet={" + alphabet + "}\n");
		
		for(State states : automate.values())
			sb.append(states.toString());
		return sb.toString();
	}
}