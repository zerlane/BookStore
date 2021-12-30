package ncc1023_hw5;

import static org.junit.Assert.*; 
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * 
 * BookTest class implements JUnit testing for BookStore Class
 * @author Nautishay Cain
 *
 */

public class BookStoreTest {
		private BookStore store;
	
		private Book book1 = new Book(1, "Book1 Title", "Author 1", "Publisher 1", 30, 3);
		private Book book2 = new Book(2, "Book2 Title", "Author 2", "Publisher 2", 40, 2);
		private Book book3 = new Book(3, "Book3 Title", "Author 3", "Publisher 3", 55, 4);
		private Book book4 = new Book(4, "Book4 Title", "Author 4", "Publisher 4", 123, 1);
		private Book book5 = new Book(5, "Book5 Title", "Author 5", "Publisher 5", 560, 3);
		private Book book6 = new Book(6, "Book6 Title", "Author 6", "Publisher 6", 220, 5);
		
		private Customer cust1 = new Customer(1, "Cust", "One", "cust1@gmail.com", "111-111-1111");
		private Customer cust2 = new Customer(2, "Cust", "Two", "cust2@gmail.com", "222-222-2222");
		private Customer cust3 = new Customer(3, "Cust", "Three", "cust3@gmail.com", "333-333-3333");
		private Customer cust4 = new Customer(4, "Cust", "Four", "cust4@gmail.com", "444-444-4444");
		
		@Before
		public void setUpBookStore() {
			store = new BookStore();
			
			store.addBook(book1);
			store.addBook(book2);
			store.addBook(book3);
			store.addBook(book4);
			store.addBook(book6);
			
			store.addCustomer(cust1);
			store.addCustomer(cust2);
			store.addCustomer(cust3);
			store.addCustomer(cust4);
			
			cust2.addBook(book1);
			cust2.addBook(book3);
			
			cust3.addBook(book4);
			
		}
		
//		@Test
//		public void testListBook() {
//			store.displayBookList();
//		}
		
		@Test
		public void testAddBook() {
			store.addBook(book5);
			assertTrue(store.getBooks().contains(book5));
		}
		
		@Test
		public void testDeleteBook() {
			store.deleteBook(book2);
			assertTrue(!store.getBooks().contains(book2));
		}
		
		@Test
		public void testSearchByBookTitle() {
			assertTrue(store.searchByBookTitle("BOOk1 Title", store.getBooks()).equals(book1));
		}
		
//		@Test
//		public void testListCustomer() {
//			store.displayCustomerList();
//		}
		
		@Test
		public void testRentBook() {
			store.rentBook(book5, cust1);
			assertEquals(book5.getNoOfCopies(), 2);
			assertTrue(cust1.getBooks().contains(book5));
		}
		
		@Test
		public void testSearchCustByFullName() {
			assertTrue(store.searchCustByFullName("Cust", "Four", store.getCustomers()).equals(cust4));	
		}
		
		@Test
		public void testSearchCustByLastName() {
			assertTrue(store.searchCustByLastName("Four", store.getCustomers()).equals(cust4));
		}
		
}
