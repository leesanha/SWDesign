package PD.user;
import PD.internship.*;
import java.util.ArrayList;
import PD.applicant.*;
import PD.apply.*;
    
public class User implements java.io.Serializable {
	private String id;
	private String passwd;
	private String name;
	private Boolean gender;//True-male False-female
	private String address;
	private String contact;//연락처
    private InterestedInternshipList interestedInternshipList;
	
	public User() {
	}
	public User(String id, String passwd, String name, Boolean gender, String address, String contact) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.contact = contact;
	}
	//set, get ID
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	//set, get Passwd
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd() {
		return passwd;
	}
	//set, get Name
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	//set, get Gender
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Boolean getGender() {
		return gender;
	}
	//set, get Address
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	//set, get contact
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContact() {
		return contact;
	}
    public InterestedInternshipList getInterestedInternshipList() {
		return null;
    }
	public ArrayList<Application> getApplications(){
		return null;
	}
    public Qualification getQualification() {
		return null;
	}
    public void addToInterestedInternshipList(Internship internship) {
		this.interestedInternshipList.addInternship(internship);
        
	}
    public ArrayList<Internship> getInternships(){
    	return null;
    }
    
    public void setQualification(Qualification q){
        ;
    }
    public Qualification getQualification(Qualification q){
         return null;
    }
    
    public void setStudentID(String studentId){
        ;
    }
    public String getStudentID(){
        return null;
    }
    
    public void addInterestedInternshipList(Internship internship) {
		;
	}
}
