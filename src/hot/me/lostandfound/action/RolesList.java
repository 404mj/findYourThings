package me.lostandfound.action;

import me.lostandfound.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("rolesList")
public class RolesList extends EntityQuery<Roles> {

	private static final String EJBQL = "select roles from Roles roles";

	private static final String[] RESTRICTIONS = {"lower(roles.roleName) like lower(concat(#{rolesList.roles.roleName},'%'))",};

	private Roles roles = new Roles();

	public RolesList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Roles getRoles() {
		return roles;
	}
}
