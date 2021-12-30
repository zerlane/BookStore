package ncc1023_hw5;

import java.util.List;

public class Book {
	private String title, author, publisher;
	private int id, noOfPages, noOfCopies;
	
	public Book() {
		
	}
	
	public Book(int id, String title, String author, String publisher, int noOfPages, int noOfCopies) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.noOfPages = noOfPages;
		this.noOfCopies = noOfCopies;
	}
	
	//getters and setters
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return this.publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getNoOfPages() {
		return this.noOfPages;
	}
	
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	
	public int getNoOfCopies() {
		return this.noOfCopies;
	}
	
	public void setCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	
	@Override
	public String toString() {
		return "ID: " + this.getId() + ", Title: " + this.getTitle() + ", Author: " + this.getAuthor() 
			+  ", No. of Pages: " + this.getNoOfPages() + ", No. of Copies: " + this.getNoOfCopies();
	}
}
