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
	public String descr;
	public Timestamp postedAt;
	
	public Post() {}

	public Post(String descr, Timestamp postedAt) {
		super();
		this.descr = descr;
		this.postedAt = new Timestamp(System.currentTimeMillis());
	}
	
	
}
