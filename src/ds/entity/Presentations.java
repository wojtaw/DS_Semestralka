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
@Table(name="Presentations")
public class Presentations implements java.io.Serializable {
	private long presentationId;
	private String presentationTitle;
	private String presentationDescription;
	private String presentationLanguage;	
	private Set<Speakers> speakersList = new HashSet<Speakers>();
	
	public Presentations(){
		
	}
	
	public Presentations(String presentationTitle){
		this.presentationTitle = presentationTitle;
	}
	
	public Presentations(String presentationTitle, Set<Speakers> speakers){
		this.presentationTitle = presentationTitle;
		this.speakersList = speakers;
	}	
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator = "generator")	
	@Column(name = "id")
	public long getPresentationId() {
		return this.presentationId;
	}
	
	public void setPresentationId(long presentationId) {
		this.presentationId = presentationId;
	}
	
	@Column(name = "title")
	public String getPresentationTitle() {
		return this.presentationTitle;
	}	
	
	public void setPresentationTitle(String presentationTitle) {
		this.presentationTitle = presentationTitle;
	}
	
	@Column(name = "description")
	public String getPresentationDescription() {
		return this.presentationDescription;
	}	
	
	public void setPresentationDescription(String presentationDescription) {
		this.presentationDescription = presentationDescription;
	}	
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="presentations")	
	public Set<Speakers> getSpeakers() {
		return this.speakersList;
	}

	public void setSpeakers(Set<Speakers> speakers) {
		this.speakersList = speakers;
	}
	
	

}
