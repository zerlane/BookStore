package ncc1023_hw5;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Creating this AbstractBook abstract class allows the
 * @author Nautishay Cain
 *
 */
public abstract class AbstractBook {
	List<Book> books;
	
	AbstractBook() {
		this.books = new ArrayList<Book>();
	}
	
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void deleteBook(Book book) {
		books.remove(book);
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		books = this.books;
	}
	
}
