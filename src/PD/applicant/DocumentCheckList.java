package PD.applicant;

import PD.applicant.*;
public class DocumentCheckList implements java.io.Serializable {
	private boolean weeklyReport;
	private boolean finalReport;
	private boolean passport;
	private boolean departCertification;
	private boolean companyEvaluation;
	private boolean applicationInformation;
	private Applicant applicant;
	
	//set, get weeklyRport
	public void setWeeklyReport(boolean weeklyReport) {
		this.weeklyReport = weeklyReport;
	}
	public boolean isWeeklyReport() {
		return weeklyReport;
	}
	//set, get FinalReport
	public void setFinalReport(boolean finalReport) {
		this.finalReport = finalReport;
	}
	public boolean isFinalReport() {
		return finalReport;
	}
	//set, get Passport
	public void setPassport(boolean passport) {
		this.passport = passport;
	}
	public boolean isPassport() {
		return passport;
	}
	//set, get DepartCertification
	public void setDepartCertification(boolean departCertification) {
		this.departCertification = departCertification;
	}
	public boolean isDepartCertification() {
		return departCertification;
	}
	//set, get CompanyEvaluation
	public void setCompanyEvaluation(boolean companyEvaluation) {
		this.companyEvaluation = companyEvaluation;
	}
	public boolean isCompanyEvaluation() {
		return companyEvaluation;
	}
	//set, get ApplicationInformation
	public void setApplicationInformation(boolean applicationInformation) {
		this.applicationInformation = applicationInformation;
	}
	public boolean isApplicationInformation() {
		return applicationInformation;
	}
	//set, get Applicant
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	
	@Override
	public String toString() {
		return "DocumentCheckList [weeklyReport=" + weeklyReport + ", finalReport=" + finalReport + ", passport="
				+ passport + ", departCertification=" + departCertification + ", companyEvaluation=" + companyEvaluation
				+ ", applicationInformation=" + applicationInformation + ", applicant=" + applicant + "]";
	}
	
	
	
}
