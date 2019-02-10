package tree;

import automates.Automate;
import expression.Tree;

public class TestTreeBuilding {
	public static void main(String[] args) {
		TreeBuilder builder = new TreeBuilder("src/tree/input.txt");
		Tree tree = builder.buildTree();
		ThompsonVisitor algo_thompson=new ThompsonVisitor();
		
		Automate result_automate=tree.accept(algo_thompson);;
		System.out.println(result_automate.transitionTableString());
		result_automate.synchronization();
		System.out.println(result_automate.transitionTableString());
		
		System.out.println("this automate are "+result_automate.isDeterminist());

		//String word="if|a|then|bc";
		String word="abc";
		System.out.println("the word :("+word+")-> has been "+result_automate.wordRecognition(word));
	}
}