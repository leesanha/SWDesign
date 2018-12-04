package project;


import java.util.Scanner;

import PD.internship.*;
import PD.user.*;

public class InternshipUI {
	private User user;//현재 들어온 user
	private Internship internship;
	private InternshipList internships=null;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	public void registration(Internship input) {
		//1)인턴쉽 만들 데이터가 저장된 Internship 객체 input을 파라미터로 받아오고
		//2) input을 IntershipList에 추가
		//여기서 
		this.internships=InternshipList.getInternshipList();
		this.internships.addInternship(input);
	}
}
