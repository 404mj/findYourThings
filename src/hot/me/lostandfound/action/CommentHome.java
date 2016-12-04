package me.lostandfound.action;

import me.lostandfound.model.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("commentHome")
public class CommentHome extends EntityHome<Comment> {

	@In(create = true)
	MessagessHome messagessHome;

	public void setCommentId(Integer id) {
		setId(id);
	}

	public Integer getCommentId() {
		return (Integer) getId();
	}

	@Override
	protected Comment createInstance() {
		Comment comment = new Comment();
		return comment;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Messagess messagess = messagessHome.getDefinedInstance();
		if (messagess != null) {
			getInstance().setMessagess(messagess);
		}
	}

	public boolean isWired() {
		if (getInstance().getMessagess() == null)
			return false;
		return true;
	}

	public Comment getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
