package export;

import automates.Automate;

public class Determiniser extends Question {

	
	public Determiniser(Automate auto) {
		this.setQtype("Determiniser");
		this.setShortanswer(false);
		this.setQuestion("Inscrire dans le tableau les transitions entre les états de l'automate déterminisé");
		this.setReminder("Tous les caractères sont en minuscules, on inscrira x dans les cases qui ne contiennent pas de transition");
		String answer = " <br><br><table BORDER=\"1\">\n";
		//TODO Work on the answer
		/*Analyser l'automate determinise pour pouvoir creer les regles sur chaque case du tableau*/
		this.setAnswer(answer);
		this.setFeedback("");
		this.setPenalty(0.3333333);
		this.setHidden(0);
	}
}
