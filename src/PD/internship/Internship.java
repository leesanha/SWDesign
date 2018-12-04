package PD.internship;

import java.util.Date;
import java.util.ArrayList;

import PD.user.*;
import PD.apply.*;
import PD.alarm.*;
import PD.applicant.*;

public class Internship implements java.io.Serializable {
    private int id; 
    private String name;
	private boolean managerAdmit;
	private String job;
    private String nation;
	private float salary;
	private String[] workplace;
	private String workinghour;
	private Date deadline;
	private int recruitmentNumber;
	private String contact;
	private boolean visa;
	private Organization organization;
	private ArrayList<Application> applications;
    private InterestedInternshipList interestedInternshipList;
    private ArrayList<Notification> notifications;
    
    public boolean checkQualification(Qualification qual) {
		boolean bool = true;
		if(qual.getGrades4p5()<(float)3.0)
		{
			System.out.println("해당 학점이 기준에 비해 부족합니다. \n");
			return false;
		}
		if(qual.getSemeter()<4)
		{
			System.out.println("해당 학기가 기준에 비해 부족합니다. \n");
			return false;
		}
		return bool;
	}
    public void addNotifications(Notification notification) {
		if(this.notifications == null)
			this.notifications = new ArrayList<Notification>();
		this.notifications.add(notification);
	}
    
    public void addApplications(Application application) {
		if(this.applications == null) {
			this.applications = new ArrayList<Application>();
		}
		this.applications.add(application);
	}
    public void notificate() {
		
	}
    public ArrayList<Notification> getNotifications() {
		return notifications;
	}
    //set, get nation;
    public void setNation(String nation){
        this.nation = nation;
    }
    public String getNation(){
        return this.nation;
    }
    public void setManagerAdmit(boolean managerAdmit) {
		this.managerAdmit = managerAdmit;
	}
    //set, get name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    
    //set, get ManagerAdmit
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
	public boolean isManagerAdmit() {
		return managerAdmit;
	}
	//set, get Job
	public void setJob(String job) {
		this.job = job;
	}
	public String getJob() {
		return this.job;
	}
	//set, get Salary
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public float getSalary() {
		return salary;
	}
	//set, get Workplace
	public void setWorkplace(String[] workplace) {
		this.workplace = workplace;
	}
	public String[] getWorkplace() {
		return workplace;
	}
	//set, get Workinghour
	public void setWorkinghour(String workinghour) {
		this.workinghour = workinghour;
	}
	public String getWorkinghour() {
		return workinghour;
	}
	//set, get Deadlinecd
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getDeadline() {
		return deadline;
	}
	//set, get RecruitmentNumber
	public void setRecruitmentNumber(int recruitmentNumber) {
		this.recruitmentNumber = recruitmentNumber;
	}
	public int getRecruitmentNumber() {
		return recruitmentNumber;
	}
	//set, get Contact
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContact() {
		return contact;
	}
	//set, get visa
	public void setVisa(boolean visa) {
		this.visa = visa;
	}
	public boolean isVisa() {
		return visa;
	}
	//set, get organization
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Organization getOrganization() {
		return organization;
	}
	//add, get applications
	
	public ArrayList<Application> getApplications() {
		return applications;
	}
	//set, get InterestedInternshipList
	public void setInterestedInternshipList(InterestedInternshipList interestedInternshipList) {
		this.interestedInternshipList = interestedInternshipList;
	}
	public InterestedInternshipList getInterestedInternshipList() {
		return interestedInternshipList;
	}
	
	/*
	public boolean requestToApply(Application application) {
		boolean bool = true;
		
		return bool;
	}
	*/
	
    @Override
	public String toString() {
		return "Internship [id=" + id + ", name=" + name + ", job=" + job + ", deadline=" + deadline + ", organization=" + this.organization.getName() + "]";
	}
}
