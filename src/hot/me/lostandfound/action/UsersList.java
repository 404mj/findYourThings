package me.lostandfound.action;

import me.lostandfound.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.security.Credentials;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Name("usersList")
public class UsersList extends EntityQuery<Users> {
	private static final String EJBQL = "select users from Users users";
	private static final String search="select u from Users u where u.name=:username";
	private static final String[] RESTRICTIONS = {
			"lower(users.name) like lower(concat(#{usersList.users.name},'%'))",
			"lower(users.password) like lower(concat(#{usersList.users.password},'%'))",
			"lower(users.phoneNumber) like lower(concat(#{usersList.users.phoneNumber},'%'))",
			"lower(users.professional) like lower(concat(#{usersList.users.professional},'%'))",};

	private Users users = new Users();
	@In
	Credentials credentials;

	
	
	
	public UsersList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Users getUsers() {
		return users;
	}
	
	
	public Users makeId(){
		String username=credentials.getUsername();
		if(username!=null){
		users= (Users)getEntityManager().createQuery(search).setParameter("username",username).getSingleResult();
		return 	users;
		}
		else{
			return null;
		}

	}
	
		
	
}
