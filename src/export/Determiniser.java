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
		auto.determinize();
		answer += "<TR><TD></TD>\n";
		for (int i = 0;i<auto.getAlphabet().size();i++) {
			answer += "<TD>"+auto.getAlphabet().get(i)+"</TD>\n";
		}
		answer += "</TR>\n";
		String[][] table = auto.transitionTable();

		for (int j=1; j<auto.getAutomate().size()+1;j++) {
			answer += "<TR><TD>"+table[j][0]+"</TD>\n";
			for (int k=1; k<auto.getAlphabet().size()+1; k++) {
				if (table[j][k]=="Â°") {
					answer += "<td>{1:SHORTANSWER:=x}</td>\n";
				}
				else {
					answer += "<td>{1:SHORTANSWER:="+table[j][k]+"}</td>\n";
				}
			}
			answer += "</TR>\n";
		}
		
		/*Analyser l'automate determinise pour pouvoir creer les regles sur chaque case du tableau*/
		this.setAnswer(answer);
		this.setFeedback("");
		this.setPenalty(0.3333333);
		this.setHidden(0);
	}
}
