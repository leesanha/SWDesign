package PD.apply;

import PD.applicant.*;

public class Resume implements java.io.Serializable {
	private String filename;
	private Application application;
	//set, get Filename
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilename() {
		return filename;
	}
	//set, get Application
	public void setApplication(Application application) {
		this.application = application;
	}
	public Application getApplication() {
		return application;
	}
	
}
