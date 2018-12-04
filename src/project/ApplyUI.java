package project;


import PD.apply.*;
import PD.applicant.*;
import PD.internship.*;

import PD.user.User;

public class ApplyUI {
	private Applicant user;
    private Internship internship;

	public User getUser() {
		return user;
	}
	public void setUser(Applicant user) {
		this.user = user;
	}

	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	
	public boolean apply(String filepath){
		boolean checkQual=false;
		Resume resume=new Resume();
		
		Qualification qual=user.getQualification();
		checkQual=this.internship.checkQualification(qual);// 해당 지원 조건을 확인한다.
		if(checkQual == false)
			return false;// 지원 조건이 만족 못하면 ㅠㅠ
		
		Application app=new Application();
		
		resume.setFilename(filepath);//파일 경로 등록
		
		app.setResume(resume);//resume를 application에 등록
		resume.setApplication(app);//application을 resume에 등록
		
		if(!app.saveFile())//파일 저장이 제대로 되지 않으면 
			return false;
        
		app.setInternship(this.internship);
        app.setApplicant(this.user);
		
        this.user.addApplications(app);//지원자에게 application을 추가하고
		this.internship.addApplications(app);//인턴쉽에도 application을 추가하였다.
		
		return true;
	}
}