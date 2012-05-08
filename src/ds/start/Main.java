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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ds.entity.Presentations;
import ds.entity.Speakers;
import ds.util.HibernateUtil;


public class Main {
	
	public static void main(String[] args) {
		AppDriver appDriver = new AppDriver();
		//enterInDb();
		//listSpeakers();
		//listPresentations();

	}
	

	private static void enterInDb() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Set<Speakers> speakers = new HashSet();
		
		speakers.add(new Speakers("My new speaker array 1"));
		speakers.add(new Speakers("My new speaker array 2"));
		
		
		session.save(new Speakers("My new speaker array 4"));
		session.save(new Speakers("My new speaker array 5"));
		session.getTransaction().commit();
	}


	private static void listPresentations() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List<Presentations> presentations = session.createQuery("from Presentations").list();
		for (Presentations presentation : presentations){
			if(!presentation.getSpeakers().isEmpty()) System.out.println(presentation.getPresentationTitle()+" | "+
					presentation.getSpeakers().toArray()[0].toString());
			
			for (Iterator iterator = presentation.getSpeakers().iterator(); iterator.hasNext();) {
				Speakers tmpSpeaker = (Speakers) iterator.next();
				System.out.println("Mluvili na ni: "+tmpSpeaker.getSpeakerName());
			}			
		}	
		
		session.getTransaction().commit();		
		
	}


	private static void listSpeakers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List<Speakers> speakers = session.createQuery("from Speakers").list();

		for (Speakers speaker : speakers){
				
			System.out.println(speaker.getSpeakerName() +" | "+ speaker.getSpeakerId() + " | " +
					speaker.getPresentations().toString());
			for (Iterator iterator = speaker.getPresentations().iterator(); iterator.hasNext();) {
				Presentations tmpPresentation = (Presentations) iterator.next();
				System.out.println("PREDNASEL NA: "+tmpPresentation.getPresentationTitle());
			}
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
