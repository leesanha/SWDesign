package PD.internship;

import java.util.ArrayList;

import PD.apply.*;
import PD.alarm.*;

public class InterestedInternshipList implements java.io.Serializable {
	private Application application;
	private ArrayList<Internship> internships;
	private Timer timer;
	
	//set, get application
	public void setApplication(Application application) {
		this.application = application;
	}
	public Application getApplication() {
		return this.application;
	}
	//set, get Internships
	public void addInternship(Internship internship) {
		if(this.internships == null) {
			internships = new ArrayList<Internship>();
			internships.add(internship);
		}
		else internships.add(internship);
	}
	public ArrayList<Internship> getInternships(){
		return this.internships;
	}
	//set, get Timer
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public Timer getTimer() {
		return this.timer;
	}
}
