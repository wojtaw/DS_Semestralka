package ds.start;

import java.sql.Connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ds.entity.Speakers;
import ds.util.HibernateUtil;


public class Main {
	
	public static void main(String[] args) {
		initHibernate();
		/*
		try {
			connectToDbTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

	}

	private static void initHibernate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();


			Set<Speakers> speakers = new HashSet<Speakers>();
			System.out.println("TISKNU SPEAKERY"+speakers.toString());
			
			/*
			speakers.add(new Speakers("Lojza"));
			speakers.add(new Speakers("Keda"));
			*/
			/*
			Student student1 = new Student("Eswar", speakers);
			Student student2 = new Student("Joe", speakers);
			session.save(student1);
			session.save(student2);
			*/

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
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
