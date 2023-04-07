package dao;


import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

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
	
}
