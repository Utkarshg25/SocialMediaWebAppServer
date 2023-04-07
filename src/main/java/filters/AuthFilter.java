package filters;

import com.google.inject.Inject;

import dao.UserDao;
import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import ninja.Results;

public class AuthFilter implements Filter{

	@Inject
	private UserDao userdao;
	
	@Override
	public Result filter(FilterChain filterChain, Context context) {
		// TODO Auto-generated method stub
		String authToken = context.getHeader("Authorization");
		if(authToken==null){
			return Results.badRequest().text().render("Token Missing!!");
		}
		else {
			if(userdao.isUserExist(authToken)) {
				return filterChain.next(context);
			}
			else {
				return Results.badRequest().text().render("User does not exist!");
			}
		}
	}

}
