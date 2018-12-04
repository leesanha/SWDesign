package PD.applicant;

import PD.applicant.*;

public class Major implements java.io.Serializable {
	private String name;
	private Qualification qualification;
	
	//set, get Name
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	//set, get Qualification
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public Qualification getQualification() {
		return qualification;
	}
}
