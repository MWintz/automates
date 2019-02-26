package export;

import java.util.ArrayList;

import automates.Alphabet;
import automates.Automate;

public abstract class Question {
	private String qtype;
	private String question;
	private boolean shortanswer;
	private String reminder;
	private String answer;
	private String feedback;
	private double penalty;
	private int hidden;
	private Automate auto = new Automate();
	private ArrayList<Alphabet> alpha = new ArrayList<Alphabet>();

	public Question() {
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public boolean isShortanswer() {
		return shortanswer;
	}

	public void setShortanswer(boolean shortanswer) {
		this.shortanswer = shortanswer;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	public Automate getAuto() {
		return auto;
	}

	public void setAuto(Automate auto) {
		this.auto = auto;
	}
	
	public ArrayList<Alphabet> getAlpha() {
		return alpha;
	}

	public void setAlpha(ArrayList<Alphabet> alpha) {
		this.alpha = alpha;
	}
}
