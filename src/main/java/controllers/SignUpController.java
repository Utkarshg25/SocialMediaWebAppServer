package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.SignUpDao;
import filters.AuthFilter;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.params.PathParam;
import models.Post;
import models.User;
import models.UserDto;

@Singleton
public class SignUpController {
	
	@Inject
	private SignUpDao signupdao;
	
	
	///////////////////////////////////////////////////////////////////////
	// Signup User
	///////////////////////////////////////////////////////////////////////
	public Result addUserCredentials(User userdto) {

		return Results.json().render(signupdao.addUserCredential(userdto));
		
	}
	
	///////////////////////////////////////////////////////////////////////
	// Get User
	///////////////////////////////////////////////////////////////////////
	public Result getUser(@PathParam("username") String username) {
		UserDto user = signupdao.getUserByName(username);
		return Results.json().render(user);

    }
	
	
	public Result getUserById(@PathParam("id")Long id) {
//		User user = signupdao.getUserById(id);
		return Results.json().render(signupdao.getUserById(id));

    }
	
	
	///////////////////////////////////////////////////////////////////////
	// Register User
	///////////////////////////////////////////////////////////////////////
	public Result registerUser(@PathParam("id") Long id,User newUser) {
		if(signupdao.registerUser(newUser,id)==false) {
			return Results.badRequest().json().render("Please go to signup page first!");
		}
		return Results.json().render(signupdao.registerUser(newUser,id));
	}

}
