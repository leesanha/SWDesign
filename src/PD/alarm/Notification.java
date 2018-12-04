package PD.alarm;

import PD.applicant.*;
import PD.internship.*;

public class Notification {
	private String content;
	private Internship internship;
	private Applicant applicant;
	
	//set, get content
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return this.content;
	}
	//set, get internship
	public void setInternship(Internship internship) {
		this.internship = internship;
	}
	public Internship getInternship() {
		return this.internship;
	}
	//set, get applicant
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public Applicant getApplicant() {
		return this.applicant;
	}
}
