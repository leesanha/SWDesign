package PD.applicant;

import java.util.ArrayList;


import PD.applicant.*;

public class Qualification implements java.io.Serializable {
	private int semester;
	private float grades;
	private float grades4p5;
	private Applicant applicant;
	private ArrayList<Major> majors;
	private ArrayList<LanguageAblity> languageAblity; 
	//생성자
	public Qualification() {
		
	}
	public Qualification(Major major) {
		if(this.majors == null)
			this.majors = new ArrayList<Major>();
		this.majors.add(major);
	}
	//set, get Semester
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getSemeter() {
		return semester;
	}
	//set, get grades
	public void setGrades(float grades) {
		this.grades = grades;
		grades4p5 = (Math.round((this.grades/(float)(4.3)) * 1000)/(float)1000.0) * (float)(4.5);
	}
	public float getGrades() {
		return grades;
	}
	public float getGrades4p5() {
		return grades4p5;
	}
	//set, get applicant
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	//add, get Major
	public void addMajor(Major major){
		if(this.majors == null)
			this.majors = new ArrayList<Major>();
		
		this.majors.add(major);
	}
	public ArrayList<Major> getMajors(){
		return this.majors;
	}
	//add, get LanguageAbility
	public void addLanguageAblity(LanguageAblity ablity){
		if(this.languageAblity == null){
			this.languageAblity = new ArrayList<LanguageAblity>();
        }
		this.languageAblity.add(ablity);
	}
	public ArrayList<LanguageAblity> getLanguageAblity(){
		return this.languageAblity;
	}
}
