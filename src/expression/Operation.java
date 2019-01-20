package expression;

public abstract class Operation implements Tree {
	
	private Tree leftAlphabet;
	private Tree rightAlphabet;
	
	public Operation(Tree leftAlphabet, Tree rightAlphabet) {
		this.leftAlphabet=leftAlphabet;
		this.rightAlphabet=rightAlphabet;
	}
	
	public Tree getLeftAlphabet() {
		return leftAlphabet;
	}
	
	public Tree getRightAlphabet() {
		return rightAlphabet;
	}
	
	public void setLeftAlphabet(Tree leftAlphabet) {
		this.leftAlphabet=leftAlphabet;
	}
	
	public void setRightAlphabet(Tree rightAlphabet) {
		this.rightAlphabet=rightAlphabet;
	}

	@Override
	public String toString() {
		return "Operation (left='"+ leftAlphabet +"',right='"+rightAlphabet +"')";
	}	
}
