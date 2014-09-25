package com.backendtask.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the collection database table.
 * 
 */
@Entity
@NamedQuery(name="Collection.findAll", query="SELECT c FROM Collection c")
public class Collection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COLLECTION_ID", unique=true, nullable=false)
	private int collectionId;

	@Column(name="COLLECTION_NAME", unique=true, nullable=false, length=45)
	private String collectionName;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", nullable =false)
	private User user;

	//bi-directional many-to-one association to Monument
	@OneToMany(mappedBy="collection", fetch=FetchType.LAZY)
	private List<Monument> monuments;

	public Collection() {
	}

	public int getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public String getCollectionName() {
		return this.collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Monument> getMonuments() {
		return this.monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	public Monument addMonument(Monument monument) {
		getMonuments().add(monument);
		monument.setCollection(this);

		return monument;
	}

	public Monument removeMonument(Monument monument) {
		getMonuments().remove(monument);
		monument.setCollection(null);

		return monument;
	}

}