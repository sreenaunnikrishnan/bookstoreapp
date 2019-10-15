import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.hcl.bookapp.model.persistance.ConnectionFactory;
import java.sql.CallableStatement;

public class DemoStoreProc {

	public static void main(String[] args) {
		Connection connection = ConnectionFactory.getConnection();
		try{
		CallableStatement cstmt = connection.prepareCall("call test1(?,?,?)");
		
		cstmt.setInt(1, 20);
		cstmt.setInt(2, 30);
		cstmt.registerOutParameter(3, Types.INTEGER);
		cstmt.execute();
		System.out.println(cstmt.getInt(3));

		}catch(SQLException e){
			e.printStackTrace();
		}

	}

}
