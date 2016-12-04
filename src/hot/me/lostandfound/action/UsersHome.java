package me.lostandfound.action;

import me.lostandfound.model.*;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;

@Name("usersHome")
public class UsersHome extends EntityHome<Users> {

	@In(create = true)
	CollegeHome collegeHome;
	@In(create = true)
	RolesHome rolesHome;
	
	
	//测试，让messageList引入当前用户
	public Users nowUser;
	

	@In
	FacesMessages facesMessages;

	private String verify;

	private String roleId;

	public void setUsersId(Integer id) {
		setId(id);
	}

	public Integer getUsersId() {
		return (Integer) getId();
	}

	@Override
	protected Users createInstance() {
		Users users = new Users();
		return users;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		College college = collegeHome.getDefinedInstance();
		if (college != null) {
			getInstance().setCollege(college);
		}
		Roles roles = rolesHome.getDefinedInstance();
		if (roles != null) {
			getInstance().setRoles(roles);
		}
	}

	public boolean isWired() {
		if (getInstance().getCollege() == null)
			return false;
		if (getInstance().getRoles() == null)
			return false;
		return true;
	}

	public Users getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Messagess> getMessagesses() {
		return getInstance() == null ? null : new ArrayList<Messagess>(
				getInstance().getMessagesses());
	}

	public List<Messagess> getMessagesses_1() {
		return getInstance() == null ? null : new ArrayList<Messagess>(
				getInstance().getMessagesses_1());
	}

	

	// 娉ㄥ唽鏂扮敤鎴�
	public String register() {
		if (getInstance().getPassword().equals(verify)) {
			List existing = getEntityManager()
					.createQuery(
							"select u.name from Users u where u.name=:name")
					.setParameter("name", getInstance().getName())
					.getResultList();
			if (existing.isEmpty()) {
				Roles roles = new Roles();
				roles.setId(Integer.parseInt(getRoleId()));
				getInstance().setRoles(roles);
				getEntityManager().persist(getInstance());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>persisted");
				return "beckey";
			} else {
				return null;

			}
		} else {
			facesMessages.addToControl("verify", "Re-enter your password");
			verify = null; // 閲嶇疆楠岃瘉瀵嗙爜
			return null;// 鍒锋柊鏈〉锛岄噸鏂板～鍐欒〃鍗�

		}

	}

	/**
	 * @return the verify
	 */
	public String getVerify() {
		return verify;
	}

	/**
	 * @param verify
	 *            the verify to set
	 */
	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Users getNowUser() {
		return nowUser;
	}

	public void setNowUser(Users nowUser) {
		this.nowUser = nowUser;
	}

}
