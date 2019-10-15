package com.hcl.bookapp.model.persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

public class BookDaoImpUsingJdbc implements BookDao {

	private Connection connection;

	public BookDaoImpUsingJdbc() {
		connection = ConnectionFactory.getConnection();
	}

	// id | isbn | title | author | pubDate | price
	@Override
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		Statement statement;
		Book book;
		ResultSet rs;
		try {

			statement = connection.createStatement();
			rs = statement.executeQuery(Queries.GET_ALL_BOOKS);
			// (int id, String isbn, String title, String author, Date pubDate,
			// Double price) {
			while (rs.next()) {
				book = new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
						rs.getDate("pubDate"), rs.getDouble("price"));
				books.add(book);
			}

		} catch (SQLException e) {
			throw new DataAccessException("some jdbc problem", e);
		}
		return books;
	}

	@Override
	public Book addBook(Book book) {
		// isbn | title | author | pubDate | price

		try {
			PreparedStatement pstmt = connection.prepareStatement(Queries.QUERY_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, book.getIsbn());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setDate(4, new Date(book.getPubDate().getTime()));
			pstmt.setDouble(5, book.getPrice());

			int noOfRowsEffected = pstmt.executeUpdate();
			if (noOfRowsEffected > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				book.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DataAccessException("some jdbc hell", e);

		}

		return book;
	}

	@Override
	public Book updateBook(Book book) {

		try {
			PreparedStatement pstmt = connection.prepareStatement(Queries.UPDATE_BOOK);
			pstmt.setDouble(1, book.getPrice());
			pstmt.setInt(2, book.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {

			throw new DataAccessException("some jdbc hell", e);
		}
		return book;
	}

	@Override
	public Book deleteBook(int bookId) {

		Book book = getBookById(bookId);
		if (book != null) {
			try {

				PreparedStatement pstmt = connection.prepareStatement(Queries.DELETE_BOOK);
				pstmt.setInt(1, bookId);
				pstmt.executeUpdate();

			} catch (SQLException e) {

				throw new DataAccessException("some jdbc hell", e);
			}

		}
		return book;
	}

	@Override
	public Book getBookById(int bookId) {
		Book book = null;
		try {

			PreparedStatement pstmt = connection.prepareStatement(Queries.GET_BOOK_BY_ID);
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				book = new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
						rs.getDate("pubDate"), rs.getDouble("price"));

			} else {
				throw new BookNotFoundException("book with id:" + bookId + "is not found");

			}

		} catch (SQLException e) {

			throw new DataAccessException("some jdbc hell", e);
		}
		return book;
	}

}
