package export;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import automates.Alphabet;
import automates.Automate;

public class Recognition extends Question{
	
	public Recognition(int num, Automate auto) {
		this.setQtype("Recognition");
		this.setShortanswer(false);
		this.setQuestion("Les mots suivants sont-ils reconnus par l'automate donné ?");
		this.setReminder("On pourra écrire Vrai, 1, ou True pour Vrai, Faux, 0 ou False pour Faux (attention aux majuscules)");
		String answer = " <br><br><table BORDER=\"1\">\n";
		for (int i = 0; i < num; i++) {
			String word = generateRandomWord(auto, 10);
			answer += "<tr><td>"+word+"</td><td>{1:SHORTANSWER:";
			if(auto.wordRecognition(word)=="accepted") {
				answer += "=Vrai~=True~=1~Faux~False~0}</td></tr>";
			}
			else {
				answer += "=Faux~=False~=0~Vrai~True~1}</td></tr>";
			}
		}
		this.setAnswer(answer);
		this.setFeedback("");
		this.setPenalty(0.3333333);
		this.setHidden(0);
	}
	
	public String generateRandomWord(Automate auto, int size) {
		String word = "";
		for (int i = 0; i < size; i++) {
			ArrayList<Alphabet> alpha = auto.getAlphabet();
			alpha.add(Alphabet.epsilon_alph);
			int rand = ThreadLocalRandom.current().nextInt(0, alpha.size()); 
			for (int j = 0; j < alpha.size(); j++) {
				if (rand == j) {
					word += alpha.get(j);
				}
			}
		}
		return word;
	}
}
