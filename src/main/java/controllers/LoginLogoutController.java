/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.session.Session;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.UserDao;
import models.LoginRequestDto;
import models.LoginResponseDto;

@Singleton
public class LoginLogoutController {
    
    @Inject
    UserDao userDao;
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Login
    ///////////////////////////////////////////////////////////////////////////
    public Result login(Context context) {

        return Results.html();

    }

    public Result loginPost(LoginRequestDto loginRequestDto) {

    	String username = loginRequestDto.username;
    	String password = loginRequestDto.password;
        boolean isUserNameAndPasswordValid = userDao.isUserAndPasswordValid(username, password);

        if (isUserNameAndPasswordValid) {

        	LoginResponseDto loginResponseDto = new LoginResponseDto();
        	loginResponseDto.token = username;
            return Results.json().render(loginResponseDto);

        } else {


            return Results.json().render("invalid credentials");

        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // Logout
    ///////////////////////////////////////////////////////////////////////////
    public Result logout(Context context) {

        // remove any user dependent information
        context.getSession().clear();
        context.getFlashScope().success("login.logoutSuccessful");

        return Results.redirect("/");

    }

}





