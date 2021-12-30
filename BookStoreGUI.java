package ncc1023_hw5;

import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class BookStoreGUI {
	
	public BookStoreGUI() {
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Welcome to the Nameless BookStore");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		frame.setSize(400, 400);
		
		
		JPanel panel = createMainPanel();
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static JPanel createMainPanel() {
		List<Book> books = readInBookFile();
		List<Customer> customers = readInCustomerFile();
		
		BookStore bookstore = new BookStore();
		
		JPanel panel = new JPanel();

		JPanel listPanel = new JPanel();
		
		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        //button for displaying list of books
        JButton bookListBtn = new JButton("Display Books");
        bookListBtn.addActionListener(new ActionListener() {      	
			@Override 
        	public void actionPerformed(ActionEvent e) {		
				
        		try {       			    			
        			for(Book b : books) {
        				JOptionPane.showMessageDialog(panel, b.toString());
        			}   			
        		} catch(Exception ex) {
        			JOptionPane.showMessageDialog(panel, "Database not found!");
        		}
        	}
        });
        panel.add(bookListBtn);
        
        //button for displaying customers
        JButton custListBtn = new JButton("Display Customers");
        custListBtn.addActionListener(new ActionListener() {      	
			@Override 
        	public void actionPerformed(ActionEvent e) {
				
        		try {	
        			for(Customer c : customers) {
        				JOptionPane.showMessageDialog(panel, c.toString());
        			}
        			
        		} catch(Exception ex) {
        			JOptionPane.showMessageDialog(panel, "Database not found!");
        		}
        	}
        });
        panel.add(custListBtn);
        
        //button for the user to rent a book for customer
        JButton rentBookBtn = new JButton("Rent Book");
        rentBookBtn.addActionListener(new ActionListener() {
        	@Override 
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String customer = JOptionPane.showInputDialog("Enter customer's last name: ");
        			String book = JOptionPane.showInputDialog("Enter the title of the book that is being rented: ");
        			
        			Customer getCustomer = bookstore.searchCustByLastName(customer, customers);
        			Book getBook = bookstore.searchByBookTitle(book, books);
        			
        			updateBookFile(books);
        			
        			JOptionPane.showMessageDialog(panel, bookstore.rentBook(getBook, getCustomer));
        		} catch(Exception ex) {
        			JOptionPane.showMessageDialog(panel, "Book unable to rent book!");
        		}
        	}
        });
        panel.add(rentBookBtn);
       
        JButton searchBook = new JButton("Search Book by Title");
        searchBook.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String title = JOptionPane.showInputDialog("Enter title of book: ");
        			Book searchedBook = bookstore.searchByBookTitle(title, books);
        			JOptionPane.showMessageDialog(panel, searchedBook.toString());		
        		} catch(Exception ex) {
        			JOptionPane.showMessageDialog(panel, "Can't find book!");
        		}
        	}
        });
        panel.add(searchBook);
       
        //search customer by full name
        JButton searchCustByName = new JButton("Search Customer by Full Name");
        searchCustByName.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String name = JOptionPane.showInputDialog("Enter customer's full name: ");	
        			String[] splitName = name.split(" ");
        			Customer searchedCustFullName = bookstore.searchCustByFullName(splitName[0], splitName[1], customers);
        			JOptionPane.showMessageDialog(panel, searchedCustFullName.toString());
        		} catch (Exception ex) {
        			JOptionPane.showMessageDialog(panel, "Can't find customer!");
        		}
        	}
        });
        panel.add(searchCustByName);
		
        JButton searchCustByLastName = new JButton("Search Customer by Last Name");
        searchCustByLastName.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String lastName = JOptionPane.showInputDialog("Enter customer's full name: ");
        			Customer searchedCustLastName = bookstore.searchCustByLastName(lastName, customers);
        			JOptionPane.showMessageDialog(panel, searchedCustLastName.toString());
        		} catch (Exception ex) {
        			JOptionPane.showMessageDialog(panel, "Can't find customer!");
        		}
        	}
        });
       panel.add(searchCustByLastName);
       
       JButton addBook = new JButton("Add Book");
       addBook.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   try {
    			   Book lastElem = books.get(books.size() - 1);
    			   int id = lastElem.getId() + 1;
    			   String title = JOptionPane.showInputDialog(panel, "Enter title:");
    			   String author = JOptionPane.showInputDialog(panel, "Enter author");
    			   String publisher = JOptionPane.showInputDialog(panel, "Enter publisher");
    			   int noOfPages = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter number of pages"));
    			   int noOfCopies = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter number of copies"));
    			 
    			   Book newBook = new Book(id, title, author, publisher, noOfPages, noOfCopies);
    			   books.add(newBook);
    			   System.out.println(books);
    			   
    			   updateBookFile(books);
    			   JOptionPane.showMessageDialog(panel, "Book successfully added!");
    		   } catch (Exception ex) {
    			   JOptionPane.showMessageDialog(panel, "Book couldn't be added");
    		   }
    	   }
       });
       panel.add(addBook);
       
       JButton addCustomer = new JButton("Add Customer");
       addCustomer.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   try {
    			   Customer lastElem = customers.get(customers.size() - 1);
        		   int id = lastElem.getId() + 1;
        		   String fName = JOptionPane.showInputDialog(panel, "Enter first name");
        		   String lName = JOptionPane.showInputDialog(panel, "Enter last name");
        		   String email = JOptionPane.showInputDialog(panel, "Enter email address");
        		   String phoneNum = JOptionPane.showInputDialog(panel, "Enter phone number");
        		   
        		   Customer newCustomer = new Customer(id, fName, lName, email, phoneNum);
        		   customers.add(newCustomer);
        		   
        		   updateCustomerFile(customers);
        		   
        		   JOptionPane.showMessageDialog(panel, "Customer successfully added!");
    		   } catch (Exception ex) {
    			   JOptionPane.showMessageDialog(panel, "Customer couldn't be added");
    		   }
    		   
    		  
    	   }
       });
       panel.add(addCustomer);
       
       panel.add(listPanel);
       
       return panel;
	}
	
	//read in book database
	public static List<Book> readInBookFile() {
		String bookFilePath = "/Users/zerlane/Documents/UAB Fall21/CS203/Homework/HW5/books.txt";
		File bookFile = new File(bookFilePath);
		
		String title, author, publisher;
		int noOfPages, noOfCopies, id;
		
		List<Book> books = new ArrayList<Book>();
		Book book = new Book();
		
		try {
			Scanner sc = new Scanner(bookFile);
			
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] values = line.split(", ");
				
				id = Integer.parseInt(values[0]);
				title = values[1];
				author = values[2];
				publisher = values[3];
				noOfPages = Integer.parseInt(values[4]);
				noOfCopies = Integer.parseInt(values[5]);
				
				book = new Book(id, title, author, publisher, noOfPages, noOfCopies);
				books.add(book);
				
			}
		} catch (FileNotFoundException fNfe) {
			System.err.println("Database unavailable");
		}
		
		return books;
	}


	public static List<Book> updateBookFile(List<Book> books) {
		String bookFilePath = "/Users/zerlane/Documents/UAB Fall21/CS203/Homework/HW5/books.txt";
		File bookFile = new File(bookFilePath);
		
		try {
			PrintWriter pw = new PrintWriter(bookFile);
			for (Book b : books) {
				pw.println(b.getId() + ", " + b.getTitle() + ", " + b.getAuthor() + ", " + b.getPublisher() + ", "
						  + b.getNoOfPages() + ", " + b.getNoOfCopies());
			}
			
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
	//read in customers database
	public static List<Customer> readInCustomerFile() {
		String customerFilePath = "/Users/zerlane/Documents/UAB Fall21/CS203/Homework/HW5/customers.txt";
		File customerFile = new File(customerFilePath);
		
		String fName, lName, email, phoneNum;
		int id;
		
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer();
		
		
		try {
			Scanner sc = new Scanner(customerFile);
			
			int index = 5;
			
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] values = line.split(", ");
				int length = values.length;
				
				id = Integer.parseInt(values[0]);
				fName = values[1];
				lName = values[2];
				email = values[3];
				phoneNum = values[4];
				
				customer = new Customer(id, fName, lName, email, phoneNum);
				customers.add(customer);
			}
		} catch (FileNotFoundException fNfe) {
			System.err.println("Database unavailable");
		}
		
		return customers;
	}
	
	public static List<Customer> updateCustomerFile(List<Customer> customers) {
		String customerFilePath = "/Users/zerlane/Documents/UAB Fall21/CS203/Homework/HW5/customers.txt";
		File customerFile = new File(customerFilePath);
		
		try {
			PrintWriter pw = new PrintWriter(customerFile);
			
			for(Customer c : customers) {
				pw.println(c.getId() + ", " + c.getFName() + ", " + c.getLName() + ", " +
						c.getEmail() + ", " + c.getPhoneNo() + ", " + c.getBooks());
			}	
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
	public static void main(String[] args) {
		
		new BookStoreGUI();
	}

}
