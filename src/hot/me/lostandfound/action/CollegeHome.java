package me.lostandfound.action;

import me.lostandfound.model.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("collegeHome")
public class CollegeHome extends EntityHome<College> {

	public void setCollegeId(Integer id) {
		setId(id);
	}

	public Integer getCollegeId() {
		return (Integer) getId();
	}

	@Override
	protected College createInstance() {
		College college = new College();
		return college;
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

	public College getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Users> getUserses() {
		return getInstance() == null ? null : new ArrayList<Users>(
				getInstance().getUserses());
	}

}
