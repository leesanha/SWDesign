package PD.alarm;

import java.util.Date;

import PD.internship.*;

public class Timer {
	private Date time;
	private InterestedInternshipList interestedInternshipList;
	
	//set, get time;
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getTime() {
		return this.time;
	}
	//set, get interestedInternshipList
	public void setInterestedInternshipList(InterestedInternshipList interestedInternshipList) {
		this.interestedInternshipList = interestedInternshipList;
	}
	public InterestedInternshipList getInterestedInternshipList() {
		return this.interestedInternshipList;
	}
	//기능 method
	public boolean deadlineCheck() {
		boolean bool = true;
		
		return bool; 
	}
}
