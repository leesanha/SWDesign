/*addToapplicaiton()
 * addToInterestedInternship()
 * requestQualification()
 * checkQualification()
 * checkDocument()
 * hasInterestedInternship()
 * */
// pagckage
package PD.applicant;

// import
import PD.user.*;
import PD.applicant.*;
import PD.apply.*;
import PD.internship.*;
import PD.alarm.*;

import java.util.ArrayList;

// 지원자 class, 객체 저장을 위한 serializable
public class Applicant extends User implements java.io.Serializable {
	
    // field
    private String studentID;
	private DocumentCheckList documentCheckList;
	private Qualification qualification;
	private ArrayList<Application> applications=null;
	private InterestedInternshipList interestedInternshipList;
	private ArrayList<Notification> notifications=null;

    // 기본 생성자
	public Applicant() {
		super();
	}
    
	//set, get StudentID
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentID() {
		return this.studentID; 
	}
    
	//set, get DocumentCheckList
	public void setDocumentCheckList(DocumentCheckList documentCheckList) {
		this.documentCheckList = documentCheckList;
	}
	public DocumentCheckList getDocumentCheckList() {
		return this.documentCheckList;
	}
    
	//set,get Qualification
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
    // override(applicant class를 위한)
    @Override
	public Qualification getQualification() {
		return this.qualification;
	}
    
	//add, get Application
	public void addApplications(Application application) {
		if(this.applications == null) 
			this.applications = new ArrayList<Application>();
		this.applications.add(application);
	}
    
    // 지원서 가져오기 override(applicant class를 위한) 
    @Override
	public ArrayList<Application> getApplications(){
		return this.applications;
	}
    
    // 관심인턴쉽 목록 추가하기 override(applicant class를 위한) 
    @Override
	//add, get interestedInternshipList
	public void addInterestedInternshipList(Internship internship) {
		if(this.hasInterestedInterestedInternshipList()) {
			interestedInternshipList.addInternship(internship);
		}
		else {
			this.interestedInternshipList = new InterestedInternshipList();
			interestedInternshipList.addInternship(internship);
		}
	}
    
    // 지원서 가져오기 override(applicant class를 위한) 
    @Override
	public InterestedInternshipList getInterestedInternshipList() {
		return this.interestedInternshipList;
	}
    
	//add, get notification
	public void addNotifications(Notification notification) {
		if(this.notifications == null) {
			this.notifications = new ArrayList<Notification>();
			notifications.add(notification);
		}
		else notifications.add(notification);
	}
	public ArrayList<Notification> getNotifications(){
		return this.notifications;
	}
	
    // Application 추가하기
	public void addToApplication(Application application) {
		if(this.applications==null)
            this.applications=new ArrayList<Application>();
        this.applications.add(application);
	}
	
    // 관심인턴쉽 목록 추가하기 함수(applicant class를 위한)
    @Override
	public void addToInterestedInternshipList(Internship internship) {
		this.interestedInternshipList.addInternship(internship);
        
	}
	
    // 자격요건 체크
	public boolean checkQualification() {
		boolean bool = true;
		
		return bool;
	}
	
    // 문서 관리
	public boolean checkDocumentCheckList() {
		boolean bool = true;
		
		return bool;
	}
	
    // 관심인턴쉽 목록 가지고있는지 체크 함수
	public boolean hasInterestedInterestedInternshipList() {
		if(this.interestedInternshipList == null) return false;
		else return true;
	}
}
