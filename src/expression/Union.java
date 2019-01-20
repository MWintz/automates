package expression;

import tree.TreeVisitor;

public class Union extends Operation{

	public Union(Tree leftAlphabet, Tree rightAlphabet) {
		super(leftAlphabet, rightAlphabet);
	}

	@Override
	public String toString() {
		return getLeftAlphabet().toString() + "+" + getRightAlphabet().toString();
	}
	
	@Override
	public <T> T accept(TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
