package com.hcl.bookapp.model.persistance;
import java.util.*;
public interface BookDao {
	public List<Book> getAllBooks();
	public Book addBook(Book book);
	public Book updateBook(Book book);
	public Book deleteBook(int bookId);
	public Book getBookById(int bookId);

}
