package me.lostandfound.action;

import me.lostandfound.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("hopeHome")
public class HopeHome extends EntityHome<Hope> {

	public void setHopeId(Integer id) {
		setId(id);
	}

	public Integer getHopeId() {
		return (Integer) getId();
	}

	@Override
	protected Hope createInstance() {
		Hope hope = new Hope();
		return hope;
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

	public Hope getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
