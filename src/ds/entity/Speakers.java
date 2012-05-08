package ds.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Speakers")
public class Speakers implements java.io.Serializable {
	private long speakerId;
	private String speakerName;
	private long user_id;
	@ManyToMany(mappedBy="speakersList")
	public Set<Presentations> presentations = new HashSet<Presentations>();
	
	public Speakers(){
		
	}
	
	
	public Speakers(String speakerName){
		this.speakerName = speakerName;
	}	
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator = "generator")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	public long getSpeakerId() {
		return this.speakerId;
	}
	
	/*
	public void setPresentations(Set<Presentations> presentations) {
		this.presentations = presentations;
	}
	
	public Set<Presentations> getPresentations() {
		return this.presentations;
	}
	*/
	
	public void setSpeakerId(long speakerId) {
		this.speakerId = speakerId;
	}		
	
	@Column(name="name")
	public String getSpeakerName() {
		return this.speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}	
	
}
