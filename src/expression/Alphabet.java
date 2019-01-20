package expression;

public class Alphabet {

	private char value;
	private boolean epsilon;
	
	public Alphabet(char value, boolean epsilon) {
		this.value=value;
		this.epsilon=epsilon;
	}
	
	public Alphabet(char value) {
		this.value=value;
		this.epsilon=false;
	}
	
	public Alphabet() {
		value='E';
		epsilon=true;
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

	public Tree getLeftAlphabet() {
		return null;
	}
	
	public Tree getRightAlphabet() {
		return null;
	}

	@Override
	public String toString() {
		return "Alphabet ("+value+")";
	}
}
