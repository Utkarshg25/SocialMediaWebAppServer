package controllers;

import com.google.inject.Singleton;

import ninja.Result;
import ninja.Results;

@Singleton
public class CorsHeaderController {

	public Result allowCors() {
		
		return allowHeaders(Results.ok().json().render("key","value"));
	}
	
	public Result allowHeaders(Result result) {
		return result.addHeader("Access-Control-Allow-Headers", "Authorization");
	}
}
