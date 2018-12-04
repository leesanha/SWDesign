package PD.apply;

import java.io.FileOutputStream;
import java.nio.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.io.*;
import PD.applicant.*;
import PD.apply.*;
import PD.internship.*;

// 객체 저장용 serializable 
public class Application implements java.io.Serializable {
	// field
    private Date applyDate;
	private boolean result=false;
	private Applicant applicant;
	private Internship internship;
    // 객체
	private Resume resume;
	
    
    
	//set, get ApplyDate
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	//set, get Result
	public void setResult(boolean result) {
		this.result = result;
	}
	public boolean isResult() {
		return this.result;
	}
	//set, get Applicant
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	//set, get Internship
    public Internship getInternship() {
		return this.internship;
	}
	public void setInternship(Internship internship) {
		this.internship = internship;
	}
    //set, get resume
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	public Resume getResume() {
		return resume;
	}
	public boolean saveFile() {
        try{

            String dest  = "application"+Integer.toString(this.internship.getId())+this.applicant.getStudentID()+".txt";
            copyFile(this.resume.getFilename(),dest);
	        return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    	
    public void copyFile(String source, String dest) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(dest);

        int data = 0;
        while((data=fis.read())!=-1) {
            fos.write(data);
        }

        fis.close();
        fos.close();

    }
    
   
    @Override
	public String toString() {
		return "Application [applyDate=" + applyDate + ", " +  ", applicant=" + applicant.getName()
				+ ", internship=" + internship.getName() + "]" ;
	}
}
