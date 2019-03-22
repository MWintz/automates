package export;

import automates.Automate;

public class Determiniser extends Question {
	
	public Determiniser(Automate auto) {
		this.setAuto(auto);
		this.setQtype("Determiniser");
		this.setShortanswer(false);
		this.setQuestion("Inscrire dans le tableau les transitions entre les etats de l'automate determinise");
		this.setReminder("Tous les caracteres sont en minuscules, on inscrira x dans les cases qui ne contiennent pas de transition");
		String answer = " <br><br><table BORDER=\"1\">\n";
		//TODO Work on the answer
		this.getAuto().determinize();
		answer += "<TR><TD></TD>\n";
		for (int i = 0;i<this.getAuto().getAlphabet().size();i++) {
			answer += "<TD>"+this.getAuto().getAlphabet().get(i)+"</TD>\n";
		}
		answer += "</TR>\n";
		String[][] table = this.getAuto().transitionTable();
		System.out.println(table);
		for (int j=1; j<this.getAuto().getAutomate().size()+1;j++) {
			answer += "<TR><TD>"+table[j][0]+"</TD>\n";
			for (int k=1; k<this.getAuto().getAlphabet().size()+1; k++) {
				if (table[j][k]=="°") {
					answer += "<td>{1:SHORTANSWER:=x}</td>\n";
				}
				else {
					answer += "<td>{1:SHORTANSWER:="+table[j][k]+"}</td>\n";
				}
			}
			answer += "</TR>\n";
			System.out.println("ok");
		}
		
		/*Analyser l'automate determinise pour pouvoir creer les regles sur chaque case du tableau*/
		this.setAnswer(answer);
		this.setFeedback("");
		this.setPenalty(0.3333333);
		this.setHidden(0);
	}
	
	
}
