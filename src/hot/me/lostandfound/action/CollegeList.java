package me.lostandfound.action;

import me.lostandfound.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("collegeList")
public class CollegeList extends EntityQuery<College> {

	private static final String EJBQL = "select college from College college";

	private static final String[] RESTRICTIONS = {"lower(college.collegeName) like lower(concat(#{collegeList.college.collegeName},'%'))",};

	private College college = new College();

	public CollegeList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public College getCollege() {
		return college;
	}
}
