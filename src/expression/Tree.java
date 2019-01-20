package expression;

import tree.TreeVisitor;

public interface Tree {
	public Tree getLeftAlphabet();
	
	public Tree getRightAlphabet();
	
	<T> T accept(TreeVisitor<T> visitor);
}
