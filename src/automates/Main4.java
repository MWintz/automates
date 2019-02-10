package automates;

public class Main4 {
	public static void main(String[] args) {
		Automate random_automate;
		
		random_automate=AutomateFactory.randomASynchrone(4, 2, "DEFAULT");
		System.out.println(random_automate.transitionTableString());
		random_automate.synchronization();
		System.out.println(random_automate.transitionTableString());
	}
}