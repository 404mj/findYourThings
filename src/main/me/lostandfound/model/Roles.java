package me.lostandfound.model;
// Generated 2014-10-29 23:03:33 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "roles", schema = "public")
public class Roles implements java.io.Serializable {

	private int id;
	private String roleName;
	private Set<Users> userses = new HashSet<Users>(0);

	public Roles() {
	}

	public Roles(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	public Roles(int id, String roleName, Set<Users> userses) {
		this.id = id;
		this.roleName = roleName;
		this.userses = userses;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name="roles_seq",sequenceName="roles_id_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="roles_seq")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "role_name", nullable = false, length = 12)
	@NotNull
	@Length(max = 12)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}