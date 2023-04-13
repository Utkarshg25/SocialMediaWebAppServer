package models;



import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;



@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public String subtitle;
	public String image;
	public String content;
	public Timestamp postedAt;
	
	public Post() {}

	public Post(String content,String subtitle, Timestamp postedAt) {
		super();
		this.content = content;
		this.subtitle = subtitle;
		this.postedAt = new Timestamp(System.currentTimeMillis());
	}
	
	
}
