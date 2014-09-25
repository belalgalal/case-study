package com.backendtask.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID", unique=true, nullable=false)
	private int userId;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;

	@NotEmpty
	@Column(name="FIRST_NAME", nullable = false, length=45)
	private String firstName;

	@NotEmpty
	@Column(name="LAST_NAME", nullable = false, length=45)
	private String lastName;

	@NotEmpty
	@Column(name="PASSWORD", nullable = false, length=60)
	private String password;

	@Column(name="USER_NAME", unique=true, nullable = false, length=45)
	private String userName;

	//bi-directional many-to-one association to Collection
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<Collection> collections;

	//bi-directional many-to-one association to Monument
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<Monument> monuments;

	//bi-directional many-to-one association to Picture
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<Picture> pictures;

	//bi-directional many-to-one association to UserRole
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private List<UserRole> userRoles;

	@NotEmpty
	@Transient
	private String confirmPassword;
	
	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public Collection addCollection(Collection collection) {
		getCollections().add(collection);
		collection.setUser(this);

		return collection;
	}

	public Collection removeCollection(Collection collection) {
		getCollections().remove(collection);
		collection.setUser(null);

		return collection;
	}

	public List<Monument> getMonuments() {
		return this.monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	public Monument addMonument(Monument monument) {
		getMonuments().add(monument);
		monument.setUser(this);

		return monument;
	}

	public Monument removeMonument(Monument monument) {
		getMonuments().remove(monument);
		monument.setUser(null);

		return monument;
	}

	public List<Picture> getPictures() {
		return this.pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public Picture addPicture(Picture picture) {
		getPictures().add(picture);
		picture.setUser(this);

		return picture;
	}

	public Picture removePicture(Picture picture) {
		getPictures().remove(picture);
		picture.setUser(null);

		return picture;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

}