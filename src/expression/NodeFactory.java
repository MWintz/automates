package expression;

public class NodeFactory {
	
	public static Alphabet creatAlphabet(char value) {
		return new Alphabet(value);
	}
	
	public static Operation creatOperation(char type, Tree leftAlphabet, Tree rightAlphabet) {
		switch (type) {
		case '+':
			return new Union(leftAlphabet, rightAlphabet);
		case '.':
			return new Concatenation(leftAlphabet, rightAlphabet);
		case '*':
			return new Etoile(leftAlphabet);
		default:
			return null;
		}	
	}
}
