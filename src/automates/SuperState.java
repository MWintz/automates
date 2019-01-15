package automates;

//import java.util.ArrayList;

public class SuperState extends State{

	public SuperState(String id_sate, boolean isFinal, boolean isInitial) {
		super(id_sate, isFinal, isInitial);
	}
	
	public SuperState (State state) {
		this.setId_state(state.getId_state());
		this.setFinal(state.isFinal());
		this.setInitial(state.isInitial());
		this.setTransition(state.getTransition());
	}
	
	public void addState (State state) {
		if (state.isFinal()) {
			this.setFinal(true);
		}
		if (state.isInitial()) {
			this.setInitial(true);
		}
		this.setId_state(this.getId_state()+"+"+state.getId_state());
		this.transition.addAll(state.getTransition());
	}

	public State toState() {
		State state = new State();
		state.setId_state(this.getId_state());
		state.setFinal(this.isFinal());
		state.setInitial(this.isInitial());
		return state;
	}
}
