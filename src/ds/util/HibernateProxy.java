package ds.util;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import ds.entity.Presentations;
import ds.entity.Speakers;

public class HibernateProxy {
	
	
	public HibernateProxy(){
		
	}
	
	public List<Presentations> retrievePresentationData(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List<Presentations> presentations = session.createQuery("from Presentations").list();
		session.getTransaction().commit();				
		return presentations;
	}
	
	

}
