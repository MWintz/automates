package tree;

import automates.Automate;
import expression.Tree;

public class TestTreeBuilding {
	public static void main(String[] args) {
		TreeBuilder builder = new TreeBuilder("src/tree/input.txt");
		Tree tree = builder.buildTree();
		ThompsonVisitor algo_thompson=new ThompsonVisitor();
		tree.accept(algo_thompson);
		System.out.println(algo_thompson.getThompsonAutomate().transitionTableString());
	}
}
