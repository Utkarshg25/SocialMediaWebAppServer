package controllers;

import com.google.inject.Singleton;

import dao.PostDao;
import dao.UserDao;
import filters.AuthFilter;

import models.Post;
import models.User;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;
import ninja.params.Param;

import java.util.List;
import org.apache.log4j.LogManager;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Filter;

import com.google.inject.Inject;


@Singleton
public class PostController {
	@Inject
	private PostDao postdao;
	@Inject
	private UserDao userdao;
 	
	private static Logger log = Logger.getLogger(PostController.class);
	

    ///////////////////////////////////////////////////////////////////////
    // get all posts
    ///////////////////////////////////////////////////////////////////////
	
	@FilterWith(AuthFilter.class)
	public Result getAllPost() {

		List<Post> posts = postdao.getAllPosts();
        return Results.json().render(posts);

    }
	
	

    ///////////////////////////////////////////////////////////////////////
    // add posts
    ///////////////////////////////////////////////////////////////////////
	
	@FilterWith(AuthFilter.class)
	public Result addPosts(Post post, Context context) {
		String username = context.getHeader("Authorization");
		User user = userdao.getUser(username);
		
		return Results.json().render(postdao.addPosts(post,user));
		
	}
	
	

    ///////////////////////////////////////////////////////////////////////
    // get post by id
    ///////////////////////////////////////////////////////////////////////
	
	@FilterWith(AuthFilter.class)
	public Result getPost(@PathParam("id") Long id) {

//        Post post = null;
//        if(id!=null) {
//        	post = postdao.getPost(id);
//        }
		log.debug(id);
        Post post = postdao.getPost(id);
        

        return Results.json().render(post);

    }
	
	

    ///////////////////////////////////////////////////////////////////////
    // delete post
    ///////////////////////////////////////////////////////////////////////
	
	@FilterWith(AuthFilter.class)
	public Result deletePost(@PathParam("id") Long id) {
		if(postdao.deletePost(id)==false) {
			return Results.json().render("No such Post exists!!");
		}
		return Results.json().render("Post deleted successfully!!");
		
	}
	
	
	
	///////////////////////////////////////////////////////////////////////
	// add follwers
	///////////////////////////////////////////////////////////////////////
	
	@FilterWith(AuthFilter.class)
	public Result addFollwers(@Param(value = "username") String follower,Context context){
		
		String username = context.getHeader("Authorization");
		User user = userdao.getUser(username);
		return Results.json().render(postdao.addFollwer(follower, user));
		
	}
	
	
}
