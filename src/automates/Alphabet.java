package automates;

public class Alphabet {
	
	private char value;
	private boolean epsilon;
	public static Alphabet epsilon_alph = new Alphabet();
	
	private Alphabet() {
		value='E';
		epsilon=true;
	}
	
	public Alphabet(char value, boolean epsilon) {
		this.value=value;
		this.epsilon=epsilon;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public boolean isEpsilon() {
		return epsilon;
	}

	public void setEpsilon(boolean epsilon) {
		this.epsilon = epsilon;
	}
	
	public boolean equals(Alphabet alphabet) {
		boolean equals;
		
		equals=alphabet.epsilon==this.epsilon && alphabet.value==this.value;

		return equals;
	}

	@Override
	public String toString() {
		return ""+value+"";
	}
}
