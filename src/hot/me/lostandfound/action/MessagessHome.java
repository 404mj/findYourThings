package me.lostandfound.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import me.lostandfound.model.*;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityHome;

@Name("messagessHome")
public class MessagessHome extends EntityHome<Messagess> {

	@In(create = true)
	MessageSortHome messageSortHome;
	@In(create = true)
	UsersHome usersHome;

	private String status;
	private String date1;

	public void setMessagessId(Integer id) {
		setId(id);
	}

	public Integer getMessagessId() {
		return (Integer) getId();
	}

	protected Messagess createInstance() {
		Messagess messagess = new Messagess();
		return messagess;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		MessageSort messageSort = messageSortHome.getDefinedInstance();
		if (messageSort != null) {
			getInstance().setMessageSort(messageSort);
		}
		Users users = usersHome.getDefinedInstance();
		if (users != null) {
			getInstance().setUsers(users);
		}
	}

	public boolean isWired() {
		if (getInstance().getMessageSort() == null)
			return false;
		if (getInstance().getUsers() == null)
			return false;
		return true;
	}

	public Messagess getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	@Override
	public Messagess getInstance() {
		// TODO Auto-generated method stub
		return super.getInstance();
	}

	public List<Comment> getComments() {
		return getInstance() == null ? null : new ArrayList<Comment>(
				getInstance().getComments());
	}

	public String createDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}

	public void transDate() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date2 = sdf1.parse(date1);
			getInstance().setTime(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the date1
	 */
	public String getDate1() {
		return date1;
	}

	/**
	 * @param date1
	 *            the date1 to set
	 */
	public void setDate1(String date1) {
		this.date1 = date1;
	}

}
