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
 * Copyright (C) 2012-2020 the original author or authors.
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

package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;

import controllers.ApiController;
import controllers.ApplicationController;
import controllers.ArticleController;
import controllers.CorsHeaderController;
import controllers.LoginLogoutController;
import controllers.PostController;
import controllers.SignUpController;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {  
        
        // puts test data into db:
        if (!ninjaProperties.isProd()) {
            router.GET().route("/setup").with(ApplicationController::setup);
        }
        
        router.OPTIONS().route("/.*").with(CorsHeaderController::allowCors);
        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/login").with(LoginLogoutController::login);
        router.GET().route("/logout").with(LoginLogoutController::logout);
        

        ///////////////////////////////////////////////////////////////////////
        // add signUp details
        ///////////////////////////////////////////////////////////////////////
        
        router.POST().route("/signup").with(SignUpController::addUserCredentials);
        router.GET().route("/signup/{username}").with(SignUpController::getUser);
        router.GET().route("/signup/{id}").with(SignUpController::getUserById);
        router.PUT().route("/signup/{id}").with(SignUpController::registerUser);
        
        router.POST().route("/login").with(LoginLogoutController::loginPost);
        
        
        ///////////////////////////////////////////////////////////////////////
        // Create new article
        ///////////////////////////////////////////////////////////////////////
        
        
        router.GET().route("/article/new").with(ArticleController::articleNew);
        router.POST().route("/article/new").with(ArticleController::articleNewPost);
        
        
        ///////////////////////////////////////////////////////////////////////
        // Create new post
        ///////////////////////////////////////////////////////////////////////
        
        router.GET().route("/post").with(PostController::getAllPost);
        router.POST().route("/post").with(PostController::addPosts);
        router.GET().route("/post/{id}").with(PostController::getPost);
        router.PUT().route("/post/{id}").with(PostController::updatePost);
        router.DELETE().route("/post/{id}").with(PostController::deletePost);
        
        router.GET().route("/post/me/{username}").with(PostController::getMyPost);
        
        router.POST().route("/followers/{influencer}").with(PostController::addFollwers);
        
        
        
        
        
        ///////////////////////////////////////////////////////////////////////
        // Create new article
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/article/{id}").with(ArticleController::articleShow);

        ///////////////////////////////////////////////////////////////////////
        // Api for management of software
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/api/{username}/articles.json").with(ApiController::getArticlesJson);
        router.GET().route("/api/{username}/article/{id}.json").with(ApiController::getArticleJson);
        router.GET().route("/api/{username}/articles.xml").with(ApiController::getArticlesXml);
        router.POST().route("/api/{username}/article.json").with(ApiController::postArticleJson);
        router.POST().route("/api/{username}/article.xml").with(ApiController::postArticleXml);
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController::index);
    }

}
