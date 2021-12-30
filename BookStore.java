package ncc1023_hw5;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class BookStore extends AbstractBook {
	private List<Customer> customers;
	
	public BookStore() {
		super();
		customers = new ArrayList<Customer>();
	}
	
	
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void deleteCustomer(Customer customer) {
		customers.remove(customer);
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		customers = this.customers;
	}
	
	//search books by title
	public Book searchByBookTitle(String title, List<Book> books) {
		//List<Book> temp = new ArrayList<Book>(books);
		//Book book = new Book();
		
		for (Book b : books) {
			if(b.getTitle().equalsIgnoreCase(title)) {
				return b;
			}
		}
		
		return null;
	}
	
	
	
	//rent book
	public String rentBook(Book book, Customer customer) {
		//adding to customer's book list
		//decreasing noOfCopies in database
	
		List<Book> custBooks = customer.getBooks();
		custBooks.add(book);
		
		if(book.getNoOfCopies() == 0) {
			return book.getTitle() + " is out of stock.";
		}
		
		int noOfCopies = book.getNoOfCopies() - 1;
		book.setCopies(noOfCopies);
		
		return "Success!";
	}
	
	public Customer searchCustByFullName(String fName, String lName, List<Customer> customers) {
		for(Customer c: customers) {
			if(c.getFName().equalsIgnoreCase(fName)) {
				if(c.getLName().equalsIgnoreCase(lName)) {
					return c;
					}
				}
			}
		
		return null;
	}
	
	public Customer searchCustByLastName(String lName, List<Customer> customers) {
		for(Customer c: customers) {
			if(c.getLName().equalsIgnoreCase(lName)) {
				return c;
			}
		}
		return null;
	}
}
