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

package models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "User_data")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;
    public String username;
    public String password;
    public String fullname;
    public boolean isAdmin;
    
    public String Email;
    public String Mobile_Number;
    public String Address;
    
    
    @OneToMany(fetch=FetchType.EAGER)
    public List<Post> post;
    
    @ElementCollection(fetch=FetchType.LAZY)
    public List<User> followers;
    
    @ElementCollection(fetch=FetchType.LAZY)
    public List<User> following;
    
    
    public User() {}
    
    public User(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }
    
    public User(UserDto userdto) {
    	this.username = userdto.username;
    	this.fullname = userdto.fullname;
//    	this.Address = userdto.Address;
    	this.password = userdto.password;
    }
 
}
