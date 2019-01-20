package expression;

import tree.TreeVisitor;

public class Etoile extends Operation{

	public Etoile(Tree leftAlphabet) {
		super(leftAlphabet, null);
	}

	@Override
	public String toString() {
		return getLeftAlphabet().toString() + "*";
	}
	
	@Override
	public <T> T accept(TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
