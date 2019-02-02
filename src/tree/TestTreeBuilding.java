package tree;

import automates.Automate;
import expression.Tree;

public class TestTreeBuilding {
	public static void main(String[] args) {
		TreeBuilder builder = new TreeBuilder("src/tree/input.txt");
		Tree tree = builder.buildTree();
		ThompsonVisitor algo_thompson=new ThompsonVisitor();
		tree.accept(algo_thompson);
		
		Automate result_automate=algo_thompson.getThompsonAutomate();
		System.out.println(result_automate.transitionTableString());
		result_automate.synchronization();
		System.out.println(result_automate.transitionTableString());
		
		System.out.println("this automate are "+result_automate.isDeterminist());
		
		String word="ababababababababababab";
		System.out.println("the word :("+word+")-> has been "+result_automate.wordRecognition(word));
	}
}
