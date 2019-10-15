package com.hcl.bookapp.web;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import com.hcl.bookapp.model.persistance.Book;
import com.hcl.bookapp.model.persistance.BookDao;
import com.hcl.bookapp.model.persistance.BookDaoImpUsingJdbc;
import com.hcl.bookapp.model.persistance.BookNotFoundException;

public class Main {

	public static void main(String[] args) {
		BookDao dao = new BookDaoImpUsingJdbc();
		List<Book> books = dao.getAllBooks();

		for (Book book : books) {
			System.out.println(book);
		}
		// get an book
		/*try {
			Book book = dao.getBookById(109);
			System.out.println(book);
		} catch (BookNotFoundException e) {
			System.out.println("------------Result of getBookById----------------------");
			System.out.println(e.getMessage());
		}*/

		// inserting a book
		/*Date date;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse("11/04/1995");
			Book book = new Book("AZ1238", "Life is cool", "sreena", date, 129.00);
			book = dao.addBook(book);
			System.out.println("---------------------Result of addBook()-----------------");
			System.out.println(book);
		} catch (ParseException e) {

			e.printStackTrace();
		}*/
		 //delete a book
		/*Book book =dao.deleteBook(1);
		System.out.println(book);*/
		//update a book
		Book bookToUpdate = dao.getBookById(5);
		bookToUpdate.setPrice(210.00);
		System.out.println(dao.updateBook(bookToUpdate));

	}

}
