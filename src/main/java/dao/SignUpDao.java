package dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Post;
import models.User;
import models.UserDto;

public class SignUpDao {

	@Inject
    Provider<EntityManager> entitiyManagerProvider;
	
	@Transactional
	public User addUserCredential(User userdto) {
		
		EntityManager entityManager = entitiyManagerProvider.get();
		entityManager.persist(userdto);
		return userdto;
	}
	
	
	public User getUserById(Long id) {
		EntityManager entityManager = entitiyManagerProvider.get();		
		TypedQuery<User> q = entityManager.createQuery("SELECT x FROM User_data x WHERE id = :idParam", User.class);		
		User user =  getSingleResult(q.setParameter("idParam", id));
		
		return user;
	}
	
	public UserDto getUserByName(String username) {
		EntityManager entityManager = entitiyManagerProvider.get();		
		TypedQuery<User> q = entityManager.createQuery("SELECT x FROM User_data x WHERE username = :usernameParam", User.class);
		User user = q.setParameter("usernameParam", username).getSingleResult();  
		UserDto userdto=new UserDto();
		userdto.username = user.username;
		userdto.id = user.id;
		userdto.fullname = user.fullname;
		userdto.Email = user.Email;
		userdto.Address = user.Address;
		userdto.Mobile_Number = user.Mobile_Number;
		userdto.post=user.post;
		return userdto;
	}
	
	private static <T> T getSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

	
	@Transactional
	public boolean registerUser(User newUser,Long id) {
		EntityManager entityManager = entitiyManagerProvider.get();
		User user = getUserById(id);
		if(user!=null) {
			user.Email = newUser.Email;
			user.Address = newUser.Address;
			user.Mobile_Number = newUser.Mobile_Number;
			user.fullname = newUser.fullname;
			entityManager.merge(user);
			return true;
		}
		return false;
	}
	
	
}
