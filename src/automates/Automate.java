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
	//not yet
	boolean equals(Automate a) {
		boolean equals=true;
		
		if(this.automate.size()==a.automate.size()) {
			
		}
		else
			equals=false;
		
		return equals;
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