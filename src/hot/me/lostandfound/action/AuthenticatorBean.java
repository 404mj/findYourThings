package me.lostandfound.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import me.lostandfound.model.Users;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

//@Stateless
@Name("authenticator")
public class AuthenticatorBean implements Authenticator {
	@Logger
	private Log log;

	@In
	Identity identity;
	@In
	Credentials credentials;
	@In(create = true)
	private EntityManager entityManager;

	@In(required = false)
	@Out(required = false, scope = SESSION)
	private Users users;

	public boolean authenticate() {
		List results = entityManager
				.createQuery(
						"select u from Users u where u.name=:username and u.password=:password")
				.setParameter("username",
						identity.getCredentials().getUsername())
				.setParameter("password",
						identity.getCredentials().getPassword())
				.getResultList();

		if (results.size() == 0) {
			return false;
		} else {
			Users user = (Users) results.get(0);

			if (!(user.getRoles() == null))
				identity.addRole(user.getRoles().getRoleName());
			return true;
		}

	}

}
