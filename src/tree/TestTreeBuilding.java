package tree;

import expression.Tree;

public class TestTreeBuilding {
	public static void main(String[] args) {
		TreeBuilder builder = new TreeBuilder("src/tree/input.txt");
		Tree tree = builder.buildTree();
	}
}
