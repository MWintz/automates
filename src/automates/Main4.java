package automates;

public class Main4 {
	public static void main(String[] args) {
		Automate random_automate;
		
		random_automate=AutomateFactory.randomADeterminist(5, 2, "DEFAULT");
		System.out.println(random_automate);
	}
}