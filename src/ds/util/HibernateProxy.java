package ds.util;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import ds.entity.Presentations;
import ds.entity.Speakers;

public class HibernateProxy {
	private Session session;
	private List<Presentations> presentations;
	
	
	public HibernateProxy(){
		
	}
	
	public void turnOnSession(){
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public List<Presentations> retrievePresentationData(){
		session.beginTransaction();
		presentations = session.createQuery("from Presentations").list();
		session.getTransaction().commit();				
		return presentations;
	}
	
	public void updatePresentation(){
		
	}
	
	
	

}
