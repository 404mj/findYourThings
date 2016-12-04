package me.lostandfound.action;

import me.lostandfound.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("hopeList")
public class HopeList extends EntityQuery<Hope> {

	private static final String EJBQL = "select hope from Hope hope";

	private static final String[] RESTRICTIONS = {"lower(hope.hopeName) like lower(concat(#{hopeList.hope.hopeName},'%'))",};

	private Hope hope = new Hope();

	public HopeList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Hope getHope() {
		return hope;
	}
}
