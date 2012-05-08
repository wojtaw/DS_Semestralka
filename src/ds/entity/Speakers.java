package ds.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Speakers")
public class Speakers implements java.io.Serializable {
	private long speakerId;
	private String speakerName;
	private long user_id;
	//@ManyToMany(mappedBy="speakersList")
	private Set<Presentations> presentations = new HashSet<Presentations>();
	
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
	

	public void setPresentations(Set<Presentations> presentations) {
		this.presentations = presentations;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "presentations_speakers", joinColumns = { @JoinColumn(name = "speaker_id", referencedColumnName="id") }, inverseJoinColumns = { @JoinColumn(name = "presentation_id",referencedColumnName="id") })	
	public Set<Presentations> getPresentations() {
		return this.presentations;
	}
	
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
