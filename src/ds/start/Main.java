package ds.start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
	
	public static void main(String[] args) {
		try {
			connectToDbTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void connectToDbTest() throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager
		    .getConnection("jdbc:postgresql://krizik.felk.cvut.cz:5434/student_db12_10",
		"student_db12_10","db12_10");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM channels");
		
		while(rs.next()) {
		    System.out.println(rs.getString("name"));
		}
		
		conn.close();
		
	}

}
