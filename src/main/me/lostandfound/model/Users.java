package me.lostandfound.model;
// Generated 2014-10-29 23:03:33 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Scope;

import static org.jboss.seam.ScopeType.SESSION;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", schema = "public")
public class Users implements java.io.Serializable {

	private Integer id;
	private Roles roles;
	private College college;
	private String name;
	private String professional;
	private String password;
	private String phoneNumber;
	private byte[] userImage;
	private String personalName;
	private String personalSaying;
	private Set<Messagess> messagesses = new HashSet<Messagess>(0);
	
	private Set<Messagess> messagesses_1 = new HashSet<Messagess>(0);

	public Users() {
	}

	public Users(Integer id, Roles roles, College college, String name,
			String password, String phoneNumber) {
		this.id = id;
		this.roles = roles;
		this.college = college;
		this.name = name;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	public Users(Integer id, Roles roles, College college, String name,
			String professional, String password, String phoneNumber,
			String personalName,String personalSaying,byte[] userImage, 
			Set<Messagess> messagesses,
			Set<Messagess> messagesses_1) {
		this.id = id;
		this.roles = roles;
		this.college = college;
		this.name = name;
		this.professional = professional;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.userImage = userImage;
		this.messagesses = messagesses;
		this.personalName=personalName;
		this.personalSaying=personalSaying;
		
		this.messagesses_1 = messagesses_1;
	}

	
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name="users_seq",sequenceName="users_id_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="users_seq")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	@NotNull
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "college_id", nullable = false)
	@NotNull
	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	@Column(name = "name", nullable = false, length = 20)
	@NotNull
	@Length(max = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "professional", length = 15)
	@Length(max = 15)
	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	@Column(name = "password", nullable = false, length = 30)
	@NotNull
	@Length(max = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "phone_number", nullable = false, length = 11)
	@NotNull
	@Length(max = 11)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "user_image")
	@Basic(fetch=FetchType.LAZY)
	public byte[] getUserImage() {
		return this.userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}
	

	@Column(name = "personal_name")
	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
    
	@Column(name="personal_saying")
	public String getPersonalSaying() {
		return personalSaying;
	}

	public void setPersonalSaying(String personalSaying) {
		this.personalSaying = personalSaying;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Messagess> getMessagesses() {
		return this.messagesses;
	}

	public void setMessagesses(Set<Messagess> messagesses) {
		this.messagesses = messagesses;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Messagess> getMessagesses_1() {
		return this.messagesses_1;
	}

	public void setMessagesses_1(Set<Messagess> messagesses_1) {
		this.messagesses_1 = messagesses_1;
	}

}
