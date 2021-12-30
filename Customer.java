package ncc1023_hw5;

import java.util.List;
import java.util.ArrayList;

public class Customer extends AbstractBook {
	private int id;
	private String fName, lName, email, phoneNum;
	
	public Customer() {
		
	}
	
	public Customer(int id, String fName, String lName, String email, String phoneNum) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.phoneNum = phoneNum;
		
	}
	
	//getters and setters
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFName() {
		return this.fName;
	}
	
	public void setFName(String fName) {
		this.fName = fName;
	}
	
	public String getLName() {
		return this.lName;
	}
	
	public void setLName(String lName) {
		this.lName = lName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNo() {
		return this.phoneNum;
	}
	
	public void setPhoneNo(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public Book getBookFromID(int id) {
		for(Book b : books) {
			if(b.getId() == id) {
				return b;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + ", First Name: " + this.getFName() + ", Last Name: " + 
				this.getLName() + ", Email: " + this.getEmail() + ", Phone No.: " + this.getPhoneNo();
	}
}
