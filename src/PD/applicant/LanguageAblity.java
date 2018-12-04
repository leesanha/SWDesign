package PD.applicant;

import PD.applicant.*;
public class LanguageAblity implements java.io.Serializable {
	private String certificate;
	private int score;
	private Qualification qualification;
	
	//set, get Certificate
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getCertificate() {
		return certificate;
	}
	//set, get score
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	//set, get Qualification
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public Qualification getQualification() {
		return qualification;
	}
}
