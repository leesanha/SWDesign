package PD.post;

import PD.user.*;
public class Post implements java.io.Serializable {
	private String title;
	private String contents;
	private Manager manager;
	
	//set, get Title
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	//set, get contents
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getContents() {
		return contents;
	}
	//set, get Manager
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public Manager getManager() {
		return manager;
	}
}
