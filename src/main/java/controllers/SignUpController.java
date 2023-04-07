package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.SignUpDao;
import ninja.Result;
import ninja.Results;
import models.User;
import models.UserDto;

@Singleton
public class SignUpController {
	@Inject
	private SignUpDao signupdao;
	
	
	public Result addUserCredentials(User userdto) {

		return Results.json().render(signupdao.addUserCredential(userdto));
		
	}
	
}
