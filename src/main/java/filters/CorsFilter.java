package filters;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;

import javax.inject.Singleton;

@Singleton
public class CorsFilter implements Filter {

    @Override
    public Result filter(FilterChain filterChain, Context context) {
        // Add the necessary CORS headers to the response
//        context.getResponse().addHeader("Access-Control-Allow-Origin", "*");
//        context.getResponse().addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        context.getResponse().addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Proceed with the request
        return filterChain.next(context);
    }
}