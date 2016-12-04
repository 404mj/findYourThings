package me.lostandfound.action;

import me.lostandfound.model.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("rolesHome")
public class RolesHome extends EntityHome<Roles> {

	public void setRolesId(Integer id) {
		setId(id);
	}

	public Integer getRolesId() {
		return (Integer) getId();
	}

	@Override
	protected Roles createInstance() {
		Roles roles = new Roles();
		return roles;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Roles getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Users> getUserses() {
		return getInstance() == null ? null : new ArrayList<Users>(
				getInstance().getUserses());
	}

}
