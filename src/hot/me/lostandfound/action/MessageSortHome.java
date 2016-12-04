package me.lostandfound.action;

import me.lostandfound.model.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("messageSortHome")
public class MessageSortHome extends EntityHome<MessageSort> {

	public void setMessageSortId(Integer id) {
		setId(id);
	}

	public Integer getMessageSortId() {
		return (Integer) getId();
	}

	@Override
	protected MessageSort createInstance() {
		MessageSort messageSort = new MessageSort();
		return messageSort;
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

	public MessageSort getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Messagess> getMessagesses() {
		return getInstance() == null ? null : new ArrayList<Messagess>(
				getInstance().getMessagesses());
	}

}
