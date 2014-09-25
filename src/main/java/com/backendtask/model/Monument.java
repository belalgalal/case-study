package com.backendtask.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the monument database table.
 * 
 */
@Entity
@NamedQuery(name="Monument.findAll", query="SELECT m FROM Monument m")
public class Monument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MONUMENT_ID", unique=true, nullable=false)
	private int monumentId;

	@Column(name="MONUMENT_DESC", length=255)
	private String monumentDesc;

	@Column(name="MONUMENT_NAME", unique=true, nullable=false, length=45)
	private String monumentName;

	//bi-directional many-to-one association to Collection
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COLLECTION_ID", nullable =false)
	private Collection collection;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", nullable =false)
	private User user;

	//bi-directional many-to-one association to Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID", nullable =false)
	private Category category;

	//bi-directional many-to-one association to Picture
	@OneToMany(mappedBy="monument", fetch=FetchType.LAZY)
	private List<Picture> pictures;

	public Monument() {
	}

	public int getMonumentId() {
		return this.monumentId;
	}

	public void setMonumentId(int monumentId) {
		this.monumentId = monumentId;
	}

	public String getMonumentDesc() {
		return this.monumentDesc;
	}

	public void setMonumentDesc(String monumentDesc) {
		this.monumentDesc = monumentDesc;
	}

	public String getMonumentName() {
		return this.monumentName;
	}

	public void setMonumentName(String monumentName) {
		this.monumentName = monumentName;
	}

	public Collection getCollection() {
		return this.collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Picture> getPictures() {
		return this.pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public Picture addPicture(Picture picture) {
		getPictures().add(picture);
		picture.setMonument(this);

		return picture;
	}

	public Picture removePicture(Picture picture) {
		getPictures().remove(picture);
		picture.setMonument(null);

		return picture;
	}

}