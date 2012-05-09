package ds.util;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import ds.entity.Presentations;
import ds.entity.Speakers;

public class HibernateProxy {
	Session session;
	
	public HibernateProxy(){
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public List<Presentations> retrievePresentationData(){
		//Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List<Presentations> presentations = session.createQuery("from Presentations").list();
		session.getTransaction().commit();				
		return presentations;
	}
	
	public List<Speakers> retrieveSpeakersData(){
		//Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List<Speakers> speakers = session.createQuery("from Speakers").list();
		session.getTransaction().commit();				
		return speakers;
	}	
	
	public boolean updatePresentation(Presentations presentationToUpdate){
		session.beginTransaction();
		ApplicationOutput.printLog("UPDATING "+presentationToUpdate.getPresentationTitle());
		for (Iterator iterator = presentationToUpdate.getSpeakers().iterator(); iterator.hasNext();) {
			Speakers tmpSpeaker = (Speakers) iterator.next();
			ApplicationOutput.printLog("SPEAKERS OF THE PRESENTATION"+tmpSpeaker.getSpeakerName());
		}					
		session.saveOrUpdate(presentationToUpdate);
		session.getTransaction().commit();			
		return true;
	}

	public boolean deletePresentation(Presentations presentation) {
		ApplicationOutput.printLog("Attempting to delete presentation");
		session.beginTransaction();
		session.delete(presentation);
		session.getTransaction().commit();			
		return true;		
	}
	
	public boolean updateSpeaker(Speakers speakerToUpdate){
		session.beginTransaction();
		
		ApplicationOutput.printLog("UPDATING "+speakerToUpdate.getSpeakerName());
		for (Iterator iterator = speakerToUpdate.getPresentations().iterator(); iterator.hasNext();) {
			Presentations tmpPresentation = (Presentations) iterator.next();
			ApplicationOutput.printLog("SPEAKERS OF THE PRESENTATION"+tmpPresentation.getPresentationTitle());
		}			
		
		session.saveOrUpdate(speakerToUpdate);
		session.getTransaction().commit();			
		return true;
	}

	public boolean deleteSpeaker(Speakers speaker) {
		ApplicationOutput.printLog("Attempting to delete presentation");
		session.beginTransaction();
		session.delete(speaker);
		session.getTransaction().commit();			
		return true;		
	}

	public boolean updateSpeakerRelation(Speakers speakerToUpdate) {
		session.close();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		ApplicationOutput.printLog("UPDATING "+speakerToUpdate.getSpeakerName());
		for (Iterator iterator = speakerToUpdate.getPresentations().iterator(); iterator.hasNext();) {
			Presentations tmpPresentation = (Presentations) iterator.next();
			ApplicationOutput.printLog("SPEAKERS OF THE PRESENTATION"+tmpPresentation.getPresentationTitle());
		}			
		
		session.saveOrUpdate(speakerToUpdate);
		session.getTransaction().commit();			
		return true;
		
	}	
	
	

}
