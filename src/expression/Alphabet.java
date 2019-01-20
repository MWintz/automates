package expression;

import tree.TreeVisitor;

public class Alphabet implements Tree{
	private char value;
	
	public Alphabet(char value) {
		this.value=value;
	}
	
	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
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

	@Override
	public <T> T accept(TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
