package me.lostandfound.action;

import me.lostandfound.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("messageSortList")
public class MessageSortList extends EntityQuery<MessageSort> {

	private static final String EJBQL = "select messageSort from MessageSort messageSort";

	private static final String[] RESTRICTIONS = {"lower(messageSort.messort) like lower(concat(#{messageSortList.messageSort.messort},'%')"};

	private MessageSort messageSort = new MessageSort();

	public MessageSortList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(30);
	}

	public MessageSort getMessageSort() {
		return messageSort;
	}
}
