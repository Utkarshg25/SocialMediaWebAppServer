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
import ninja.params.Param;
import ninja.params.PathParam;

import java.util.List;


import org.apache.log4j.Logger;

import com.google.inject.Inject;


@Singleton
public class PostController {
	@Inject
	private PostDao postdao;
	@Inject
	private UserDao userdao;

	
	
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
    // update posts
    ///////////////////////////////////////////////////////////////////////
	@FilterWith(AuthFilter.class)
	public Result updatePost(@PathParam("id") Long id,Post newPost) {
		if(postdao.updatePost(newPost, id)==false) {
			return Results.badRequest().json().render("Post doesn't exists!!");
		}
		return Results.json().render(postdao.updatePost(newPost, id));
	}

	
	
	
    ///////////////////////////////////////////////////////////////////////
    // get post by id
    ///////////////////////////////////////////////////////////////////////
	@FilterWith(AuthFilter.class)
	public Result getPost(@PathParam("id") Long id) {
		Post post = postdao.getPost(id);
		return Results.json().render(post);

    }
	
	
	
    ///////////////////////////////////////////////////////////////////////
    // get post by User id(token)
    ///////////////////////////////////////////////////////////////////////
	@FilterWith(AuthFilter.class)
	public Result getMyPost(@PathParam("username") String username) {
		List<Post> post = postdao.getUserPosts(username);
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
	// add followers
	///////////////////////////////////////////////////////////////////////
	@FilterWith(AuthFilter.class)
	public Result addFollwers(@Param("influencer") String influencer,Context context){
		String username = context.getHeader("Authorization");
		User follower = userdao.getUser(username);
		return Results.json().render(postdao.addFollwer(influencer, follower));
	}
	
	
}
