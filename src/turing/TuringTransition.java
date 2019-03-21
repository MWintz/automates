package turing;

import automates.Alphabet;
import automates.State;
import automates.Transition;

public class TuringTransition extends Transition{
	private Alphabet write;
	private int move;
	
	public TuringTransition(Alphabet read, Alphabet write, int move, State state) {
		super(read, state);
		
		this.write = write;
		this.move = move;
	}
	
	public int getMove() {
		return move;
	}
	
	public void setMove(int move) {
		this.move = move;
	}
	
	public Alphabet getWrite() {
		return write;
	}
	
	public void setWrite(Alphabet write) {
		this.write = write;
	}
}
