package ds.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Presentations")
public class Presentations {
	private long presentationId;
	private String presentationTitle;
	private String presentationDescription;
	private String presentationLanguage;
	private Set<Speakers> speakers = new HashSet<Speakers>();
	
	public Presentations(){
		
	}
	
	public Presentations(String presentationTitle){
		this.presentationTitle = presentationTitle;
	}
	
	public Presentations(String presentationTitle, Set<Speakers> speakers){
		this.presentationTitle = presentationTitle;
		this.speakers = speakers;
	}	
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public long getPresentationId() {
		return this.presentationId;
	}
	
	public void setPresentationId(long presentationId) {
		this.presentationId = presentationId;
	}
	
	@Column(name = "presentationTitle")
	public String getPresentationTitle() {
		return this.presentationTitle;
	}	
	
	public void setPresentationTitle(String presentationTitle) {
		this.presentationTitle = presentationTitle;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "presentations_speakers", joinColumns = { @JoinColumn(name = "presentation_id", referencedColumnName="id") }, inverseJoinColumns = { @JoinColumn(name = "speaker_id",referencedColumnName="id") })
	public Set<Speakers> setSpeakers() {
		return this.speakers;
	}

	public void setSpekers(Set<Speakers> speakers) {
		this.speakers = speakers;
	}
	
	

}
