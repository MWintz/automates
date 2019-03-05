package export;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import automates.Alphabet;

public class Recognition extends Question{
	
	public Recognition(ArrayList<Alphabet> alphabet, int num) {
		this.setQtype("Recognition");
		this.setAlpha(alphabet);
		this.setShortanswer(false);
		this.setQuestion("Les mots suivants sont-ils reconnus par l'automate donne ?");
		this.setReminder("On pourra ecrire Vrai, 1, ou True pour Vrai, Faux, 0 ou False pour Faux (attention aux majuscules)");
		String answer = " <br><br><table BORDER=\"1\">\n";
		for (int i = 0; i < num; i++) {
			//TODO Find a way to modify the size of the word without modifying the code
			String word = generateRandomWord(10);
			answer += "<tr><td>"+word+"</td><td>{1:SHORTANSWER:";
			if(this.getAuto().wordRecognition(word)=="accepted") {
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
	
	/**
	 * This method creates a word of given size or less
	 * */
	public String generateRandomWord(int size) {
		String word = "";
		ArrayList<Alphabet> alpha = this.getAlpha();
		for (int i = 0; i < size; i++) {
			int rand = ThreadLocalRandom.current().nextInt(0, alpha.size()+1); 
			for (int j = 0; j < alpha.size(); j++) {
				if (rand == j) {
					word += alpha.get(j);
				}
			}
		}
		return word;
	}
}
