package com.hcl.bookapp.model.persistance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConcurrentUpdate {

	public static void main(String[] args) {
		Connection connection = ConnectionFactory.getConnection();
		try{
			Statement stmt= connection.createStatement
					(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=stmt.executeQuery("select * from books");
			while (rs.next()) {
				System.out.println(rs.getInt("id")+":"+ rs.getString("isbn")+":" +rs.getString("title")+":" +rs.getString("author")+
						":"+rs.getDate("pubDate")+":" +rs.getDouble("price"));
			}
			rs.absolute(1);//going to first record
			
			rs.updateString("title", "new title");
			rs.updateRow();
			
			rs.beforeFirst();
			
			System.out.println("----------Result again-----------");
			while (rs.next()) {
				System.out.println(rs.getInt("id")+":"+ rs.getString("isbn")+":" +rs.getString("title")+":" +rs.getString("author")+
						":"+rs.getDate("pubDate")+":" +rs.getDouble("price"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	
	}

}
