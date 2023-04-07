package dao;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Article;
import models.Post;
import models.User;
import ninja.jpa.UnitOfWork;

public class PostDao {

	@Inject
    Provider<EntityManager> entitiyManagerProvider;
	
	

    ///////////////////////////////////////////////////////////////////////
    // get all posts
    ///////////////////////////////////////////////////////////////////////
	
	
	@UnitOfWork
    public List<Post> getAllPosts() {
        
        EntityManager entityManager = entitiyManagerProvider.get();
        
        TypedQuery<Post> q = entityManager.createQuery("SELECT x FROM Post x", Post.class);
        List<Post> posts = q.getResultList();        

        
        return posts;
        
    }


    ///////////////////////////////////////////////////////////////////////
    // get post by id
    ///////////////////////////////////////////////////////////////////////
	
	
	@UnitOfWork
	public Post getPost(Long id) {
		EntityManager entityManager = entitiyManagerProvider.get();
		
		TypedQuery<Post> q = entityManager.createQuery("SELECT x FROM Post x WHERE id = :idParam", Post.class);
		
		Post post =  getSingleResult(q.setParameter("idParam", id));
		
		return post;
	}
	
	

    ///////////////////////////////////////////////////////////////////////
    // delete post
    ///////////////////////////////////////////////////////////////////////
	
	
	private static <T> T getSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }


	@Transactional
	public boolean deletePost(Long id) {
		
		EntityManager entityManager = entitiyManagerProvider.get();
		Post post = getPost(id);
		
		if(post != null) {
			entityManager.remove(post);
			return true;
		}
		
		
		return false;
	}
	
	

    ///////////////////////////////////////////////////////////////////////
    // add post
    ///////////////////////////////////////////////////////////////////////
	
	
	@Transactional
	public Post addPosts(Post post,User user) {
		
		post.postedAt=new Timestamp(System.currentTimeMillis());
		EntityManager entityManager = entitiyManagerProvider.get();	
		
		entityManager.persist(post);
		if(user.post==null) {
			user.post = new ArrayList<>();
		}
		user.post.add(post);
		entityManager.merge(user);
		
		return post;
		
	}
	
	

    ///////////////////////////////////////////////////////////////////////
    // add followers
    ///////////////////////////////////////////////////////////////////////
	
	@Transactional
	public User addFollwer(String follower,User user) {
		EntityManager entityManager = entitiyManagerProvider.get();
		if(user.followers==null) {
			user.followers = new ArrayList<>();
		}
		user.followers.add(user);
		entityManager.merge(user);
		return user;
	}
	
	
}
