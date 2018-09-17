package ch.zhaw.iwi.pathexamplejava.model.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import ch.zhaw.iwi.pathexamplejava.model.AbstractEntity;
import ch.zhaw.iwi.pathexamplejava.model.user.permission.PermissionRole;
import ch.zhaw.iwi.pathexamplejava.server.json.ExcludeFromJson;

@Entity
@Table(name = "UserAccount")
public class User extends AbstractEntity implements Comparable<User> {

	private String firstName;

	private String lastName;

	private String email;

	@Transient
	private String password;

	@Transient
	private String repeatPassword;

	@ExcludeFromJson
	private byte[] encryptedPassword;

	@Column(length = COMMENT_FIELD_LENGTH)
	private String comments;

	private Date evtCreationDate;

	private Date evtClosingDate;

	@ManyToMany
	@JoinTable(name = "User_PermissionRoles", inverseJoinColumns = @JoinColumn(name = "permissionRole_key"))
	private Set<PermissionRole> permissionRoles = new HashSet<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(byte[] encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getEvtClosingDate() {
		return evtClosingDate;
	}

	public void setEvtClosingDate(Date evtClosingDate) {
		this.evtClosingDate = evtClosingDate;
	}

	public Date getEvtCreationDate() {
		return evtCreationDate;
	}

	public void setEvtCreationDate(Date evtCreationDate) {
		this.evtCreationDate = evtCreationDate;
	}

	public Set<PermissionRole> getPermissionRoles() {
		return permissionRoles;
	}

	@Transient
	public String getName() {
		return this.getLastName() + " " + this.getFirstName();
	}

	@Override
	public int compareTo(User o) {
		return this.getLastName().compareTo(o.getLastName());
	}

}
