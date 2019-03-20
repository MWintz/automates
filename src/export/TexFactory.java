package export;

import java.util.ArrayList;

import automates.Alphabet;
import automates.Automate;
import automates.State;
import automates.Transition;

public class TexFactory {
	
	private final static String epsilon = "{$\\varepsilon$}";
	
	public static String positionedNodes(Automate automate) {
		String node = "";
			switch (automate.getAutomate().size()) {
			case 2:
				node += twoNodes(automate);
				break;
			case 3:
				node += threeNodes(automate);
				break;
			case 4:
				node += fourNodes(automate);
				break;
			case 5:
				node += fiveNodes(automate);
				break;
			default:
				//TODO default
				break;
			}
		return node;
	}
	
	private static String twoNodes(Automate automate) {
		String two = "";
		ArrayList<String> position = new ArrayList<String>();
			State initial = automate.getInitial_Final_State("initial").get(0);
			two += "\t\\node" + getNodeFomat(initial, false, null);
			for(State s : automate.getAutomate().values()) {
				if(!s.equals(initial)) {
					position.add(initial.getId_state()+":"+s.getId_state()+":left");
					two += "\t\\node" + getNodeFomat(s, true, "right of = " + initial.getId_state());
				}
			}
			two += "\n\t\\path[->]\n";
			for(State state : automate.getAutomate().values()) {
				two += pathFormat(state, position);
				two += "\n";
			}
			two += ";\n";
		return two;
	}
	
	private static String threeNodes(Automate automate) {
		String tree = "";
		boolean right = false;
			State initial = automate.getInitial_Final_State("initial").get(0);
			tree += "\t\\node" + getNodeFomat(initial, false, null);
			
			for(State state : automate.getAutomate().values()) {
				if(!state.equals(initial)) {
					if(automate.incomingTransitions(state).contains(initial) && ! right) {
						right = true;
						tree += "\t\\node" + getNodeFomat(state, true, "right of = " + initial.getId_state());
					}
					else if (automate.incomingTransitions(state).contains(initial) && right) {
						tree += "\t\\node" + getNodeFomat(state, true, "below of = " + initial.getId_state());
					}
				}
			}
			tree += "\n\\path[->]\n";
			
			
			tree += ";\n";
		return tree;
	}
	
	private static String fourNodes(Automate automate) {
		String four = "";
			State initial = automate.getInitial_Final_State("initial").get(0);
			four += "\t\\node" + getNodeFomat(initial, false, null);
			
			four += "\n\\path[->]\n";
			
			four += ";\n";
		return four;
	}
	
	private static String fiveNodes(Automate automate) {
		String five = "";
			State middel = automate.getMaxIncomingTransitionState();
			five += "\t\\node" + getNodeFomat(middel, false, null);
			
			five += "\n\\path[->]\n";
			
			five += ";\n";
		return five;
	}
	
	private static String getNodeFomat(State state, boolean position, String pos) {
		String format ="";
			format += "[state" + (state.isInitial() ? ", initial" : "") + 
								 (state.isFinal() ? ", accepting" : "") +
								 "]" + "(" + state.getId_state() + ")" +
								 (position ? ("[" + pos + "]") : "")+
								 "{" + state.getId_state() + "};\n";
		return format;
	}
	
	private static String pathFormat(State state, ArrayList<String> pos) {
		String path = "\t(" + state.getId_state() + ") ";
			for(Transition tran : state.getTransition()) {
				Alphabet alph = tran.getLabel();
				State target = tran.getState();
				String tranalph = alph.isEpsilon() ? epsilon : ""+alph.getValue();
				if(!target.equals(state)) {
					for(String po : pos) {
						String[] p = po.split(":");
						if(p[0].equals(state.getId_state()) && p[1].equals(target.getId_state()))
							path += "edge [bend "+ p[2] +"] node {" + tranalph +"} ("+ p[1] +")";
						else if(p[1].equals(state.getId_state()) && p[0].equals(target.getId_state())) {
							path += "edge [bend "+ p[2] +"] node {" + tranalph +"} ("+ p[0] +")";
						}
					}
				}
				else {
					if(state.isInitial())
						path += "edge [loop above] node {" + tranalph +"} ()\n";
					else
						path += "edge [loop below] node {" + tranalph +"} ()\n";
				}
			}
		return path;
	}
}