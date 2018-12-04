package PD.user;

import java.util.ArrayList;

import PD.user.*;
import PD.post.*;
import PD.internship.*;

public class Manager extends User implements java.io.Serializable {
	private ArrayList<Post> posts;
	private ArrayList<Internship> internships;
	//set, get posts
	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
	public ArrayList<Post> getPosts() {
		return posts;
	}
	//set, get Internships
	public void setInternships(ArrayList<Internship> internships) {
		this.internships = internships;
	}
	public ArrayList<Internship> getInternships() {
		return internships;
	}
}