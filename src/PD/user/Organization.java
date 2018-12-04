package PD.user;

import java.util.Date;
import java.util.ArrayList;

import PD.user.*;
import PD.internship.*;

public class Organization extends User implements java.io.Serializable {
	private String nation;
	private String location;
	private int employeeScale ;
	private int saleAmount ;
	private Date establishDate ;
	private String representative;
	private String webaddress;
    private ArrayList<Internship> internships;
    
	//set, get Nation
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNation() {
		return nation;
	}
	//set, get Location
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		return location;
	}
	//set, get EmployeeScale
	public void setEmployeeScale(int employeeScale) {
		this.employeeScale = employeeScale;
	}
	public int getEmployeeScale() {
		return employeeScale;
	}
	//set, get SaleAmount
	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}
	public int getSaleAmount() {
		return saleAmount;
	}
	//set, get EstablishDate
	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
	public Date getEstablishDate() {
		return establishDate;
	}
	//set, get Representative
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getRepresentative() {
		return representative;
	}
	//set, get Webaddress
	public void setWebaddress(String webaddress) {
		this.webaddress = webaddress;
	}
	public String getWebaddress() {
		return webaddress;
	}
    //add, get internship
    public void addInternships(Internship internship){
        if(this.internships == null){
            this.internships = new ArrayList<Internship>();
        }
        internships.add(internship);
    }
    
    @Override
    public ArrayList<Internship> getInternships(){
        return this.internships;
    }
}