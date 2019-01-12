package automates;

public class Transition {
	
	private Alphabet label;
	private State state;
	
	public Transition(State state) {
		label=Alphabet.epsilon_alph;
		this.state=state;
	}
	
	public Transition(Alphabet label, State state) {
		this.label=label;
		this.state=state;
	}

	public Alphabet getLabel() {
		return label;
	}

	public void setLabel(Alphabet label) {
		this.label = label;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public boolean equals(Transition transition) {
		boolean equals;
		
		equals=this.label.equals(transition.label) && this.state.equals(transition.state);
		
		return equals;
	}

	@Override
	public String toString() {
		return "Transition [label=" + label + ", state=" + state.getId_state() + "]";
	}
}