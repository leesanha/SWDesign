package PD.internship;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import PD.user.*;

public final class InternshipList implements java.io.Serializable{
	private static final InternshipList INSTANCE = new InternshipList();//singleton 객체
	private ArrayList<Internship> internships = null;
	private ArrayList<Internship> searchedList;
	
	//set, get Internship
	private InternshipList() {//생성자
	}
    
	public static InternshipList getInternshipList() {//객체 반환
		return INSTANCE;
	}
    
    public void setArray(ArrayList<Internship> internshipss){
        this.internships = internshipss;
    }
    public ArrayList<Internship> getList(){
        return this.internships;
    }

	public void addInternship(Internship internship) {//만약 기관이 인턴쉽을 새로 등록하면 리스트에 추가한다.
		internships.add(internship);
	}

	public ArrayList<Internship> search(String[] searchOption) {//검색 조건을 통해 검색하면 검색된 목록을 반환
		Internship temp;//검색 결과를 저장할 변수
		searchedList = new ArrayList<Internship>();//검색된 목록을 저장할 List
		// Iterator<Internship> it = internships.iterator();//모든 인턴쉽이 저장된 internships에서 검색한다. 
		//searchOption[0]은 nation, [1]은 job, [2]는 organization
		if(searchOption[0].equals("x")) {
			if(searchOption[1].equals("x")) {
				if(searchOption[2].equals("x")) {//3개다 검색안하면 null return
					return null;
				}
                for(Internship is : internships){//[2]
                    if(is.getOrganization().getName().equals(searchOption[2])){
                        searchedList.add(is);
                    }
                }
			}
			else {
				if(searchOption[2].equals("x")) {//[1]
                    for(Internship is : internships){
                        if(is.getJob().equals(searchOption[1])){
                            searchedList.add(is);
                        }
                    }
				}
				else {//[1][2]
                    for(Internship is : internships){
                        if(is.getJob().equals(searchOption[1]) && is.getOrganization().getName().equals(searchOption[2])){
                            searchedList.add(is);
                        }
                    }
				}
			}
		}
		else {
			if(searchOption[1].equals("x")) {
				if(searchOption[2].equals("x")) {//[0]
                    for(Internship is : internships){
                        if(is.getNation().equals(searchOption[0])){
                            searchedList.add(is);
                        }
                    }
				}
				else {//[0][2]
                    for(Internship is : internships){
                        if(is.getNation().equals(searchOption[0]) && is.getOrganization().getName().equals(searchOption[2])){
                            searchedList.add(is);
                        }
                    }
				}
			}
			else {
				if(searchOption[2].equals("x")) {//[0][1]
                    for(Internship is : internships){
                        if(is.getNation().equals(searchOption[0]) && is.getJob().equals(searchOption[1])){
                            searchedList.add(is);
                        }
                    }
				}
				else {//[0][1][2]
                    System.out.println("검색 목록: " + searchOption[0] + " " + searchOption[1] + " "+ searchOption[2] + " ");
                    for(Internship is : internships){
                        // System.out.println(is.getOrganization().getName());
                        // if(is.getNation().equals(searchOption[0]))
                        //     System.out.println("국가 찾음");
                        // if(is.getJob().equals(searchOption[1]))
                        //     System.out.println("업무 찾음");
                        // if(is.getOrganization().getName().equals(searchOption[2]))
                        //     System.out.println("기관 찾음");
                        if(is.getNation().equals(searchOption[0]) && is.getJob().equals(searchOption[1]) && is.getOrganization().getName().equals(searchOption[2])){
                            searchedList.add(is);
                        }
                    }
				}
			}
		}
		return searchedList;
	}
    public Internship searchById(int id){
        for(Internship is : internships){
            if(is.getId() == id){
                return is;
            }
        }
        return null;
    }
}