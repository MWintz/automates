package automates;

import java.util.*;

public class Automate {
	
	private ArrayList<Alphabet> alphabet;
	private HashMap<String,State> automate;
	
	public Automate() {
		alphabet=new ArrayList<Alphabet>();
		automate=new HashMap<String,State>();
	}
	
	public  Automate(ArrayList<Alphabet> alphabet) {
		this.alphabet=alphabet;
	}
	
	public ArrayList<Alphabet> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<Alphabet> alphabet) {
		this.alphabet = alphabet;
	}

	public HashMap<String, State> getAutomate() {
		return automate;
	}

	public void setAutomate(HashMap<String, State> automate) {
		this.automate = automate;
	}
	
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
	
	public String wordRecognition(String word) {
		boolean result,stop;
		State current_state,target_state;
		char current_symbol;
		int word_index;
		
		//initialization
		result=false;
		stop=false;
		word_index=0;
		word=word+"$";
		ArrayList<State> as=getInitial_Final_State("initial");
		
		for(int i=0; i<as.size(); i++) {
		
			current_state=as.get(i);
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
					target_state=current_state.targetState(current_symbol);
					
					if(target_state != null) {
						current_state=target_state;
						word_index=word_index+1;
					}
					else {
						result=false;
						stop=true;
					}
				}
				
			}while(!stop);
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
	 * this function is used to synchronize a PLC "remove epsilon transition"
	 */
	public void synchronization() {
		ArrayList<State> incomingEpsilonState=new ArrayList<State>();
		HashMap<String,State> new_automate=new HashMap<String,State>();
		int number_tran=0;
		boolean delet=false;
		
		for(State states : automate.values()) {
			incomingEpsilonState=incomingEpsilonTransitions(states);
			number_tran=incomingTransitions(states).size();
			if(incomingEpsilonState!=null) {
				for(State incomingState : incomingEpsilonState) {
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
			if(incomingEpsilonState.size()!=number_tran)
				new_automate.put(states.getId_state(), states);
			else {
				if(states.isInitial() && !delet)
					new_automate.put(states.getId_state(), states);
				else
					delet=false;
			}
		}
		this.alphabet.remove(Alphabet.epsilon_alph);
		setAutomate(new_automate);
	}
	//not yet
	public boolean equals(Automate a) {
		boolean equals=true;
		
		if(this.automate.size()==a.automate.size()) {
			
		}
		else
			equals=false;
		
		return equals;
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
				State target=states.targetState(table_tran[0][i].charAt(0));
				if(target!=null)
					table_tran[j][i]=target.getId_state();
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
					sb.append("\tX");
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
		
		for(State states : automate.values()) {
			sb.append(states.toString());
		}
		return sb.toString();
	}
}