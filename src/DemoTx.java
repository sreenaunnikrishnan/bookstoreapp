

	import java.sql.Connection;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class DemoTx {

		public static void main(String[] args) {
			//ACID
			Connection connection = ConnectionFactory.getConnection();
			try{
				Statement stmt = connection.createStatement();
				connection.setAutoCommit(false);
				stmt.addBatch("update account set currentBalance = currentBalance-10 where id=1");
				stmt.addBatch("update account set savingBalance = savingBalance+10 where id=1");
				stmt.executeBatch();
				System.out.println("done");
				connection.commit();
			}catch(SQLException e){
				System.out.println("Error!!");
				System.out.println(e);
				try{
					connection.rollback();
					
				}catch(SQLException e1){
					e1.printStackTrace();;
				}
			}

		}

	}



