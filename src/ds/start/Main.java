package ds.start;

import java.sql.Connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ds.entity.Presentations;
import ds.entity.Speakers;
import ds.util.HibernateUtil;


public class Main {
	
	public static void main(String[] args) {
		enterInDb();
		//initHibernate();
		//listPresentations();
		/*
		try {
			connectToDbTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

	}
	

	private static void enterInDb() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Speakers speaker = new Speakers("TEST22");
		
		
		session.save(speaker);
		session.getTransaction().commit();
	}


	private static void listPresentations() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List<Presentations> presentations = session.createQuery("from Presentations").list();
		for (Presentations presentation : presentations){
			System.out.println(presentation.getPresentationTitle());
		}	
		
		session.getTransaction().commit();		
		
	}


	private static void initHibernate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List<Speakers> speakers = session.createQuery("from Speakers").list();

		for (Speakers speaker : speakers){
			System.out.println(speaker.getSpeakerName() +" | "+ speaker.getSpeakerId());
		}	

		//Set<Speakers> speakers = new HashSet<Speakers>();
		
		//speakers.add(new Speakers("Lojza"));
		//speakers.add(new Speakers("Keda"));
		
		//session.save(speakers);
		session.getTransaction().commit();
			
		
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
