package tree;

import expression.Alphabet;
import expression.Concatenation;
import expression.Union;
import expression.Etoile;

public interface TreeVisitor<T> {	
	T visit(Alphabet node);
	
	T visit(Etoile node);
	
	T visit(Union node);
	
	T visit(Concatenation node);
}