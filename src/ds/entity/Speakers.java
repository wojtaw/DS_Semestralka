package ds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Speakers")
public class Speakers {
	private long speakerId;
	private String speakerName;
	private long user_id;
	
	public Speakers(){
		
	}
	
	public Speakers(String speakerName){
		this.speakerName = speakerName;
	}	
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public long getSpeakerId() {
		return this.speakerId;
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
