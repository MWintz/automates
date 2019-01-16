package automates;

import java.util.HashSet;

public class SuperState extends State{
	HashSet<State> list = new HashSet<State>();
	
	public SuperState(String id_sate, boolean isFinal, boolean isInitial) {
		super(id_sate, isFinal, isInitial);
	}
	
	public SuperState (State state) {
		this.setId_state(state.getId_state());
		this.setFinal(false);
		this.setInitial(false);
		this.setTransition(state.getTransition());
		list.add(state);
	}
	
	public SuperState(boolean b, boolean c) {
		
	}

	public void addState (State state) {
		this.setId_state(this.getId_state()+","+state.getId_state());
		this.transition.addAll(state.getTransition());
		this.list.add(state);
	}

	public State toState() {
		State state = new State();
		state.setId_state(this.getId_state());
		state.setFinal(this.isFinal());
		state.setInitial(this.isInitial());
		state.setTransition(this.getTransition());
		return state;
	}
	
	public boolean contains (State state) {
		boolean test = false;
		for (State testState : this.list) {
			if (testState.equals(state)) {
				test = true;
			}
		}
		return test;
	}
	
	public boolean containsFinal() {
		boolean test = false;
		for (State testState : this.getList()) {
			if (testState.isFinal()) {
				test = true;
			}
		}
		return test;
	}
	
	public boolean containsInitial() {
		boolean test = false;
		for (State testState : this.getList()) {
			if (testState.isInitial()) {
				test = true;
			}
		}
		return test;
	}

	public HashSet<State> getList() {
		return list;
	}

	public void setList(HashSet<State> list) {
		this.list = list;
	}
	
	
}
