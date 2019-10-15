package com.hcl.bookapp.model.persistance;

public class Queries {
	public static String GET_ALL_BOOKS = "select * from  books";
	public static String GET_BOOK_BY_ID = "select * from books where id =?";
	public static String QUERY_INSERT=("insert into books(isbn ,title,author,pubDate, price)values(?,?,?,?,?)");
	public static String UPDATE_BOOK = "update books set price=? where id=?";
	public static String DELETE_BOOK = "delete from books where id=?";
}
