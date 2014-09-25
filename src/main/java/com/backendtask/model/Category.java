package com.backendtask.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CATEGORY_ID", unique=true, nullable=false)
	private int categoryId;

	@Column(name="CATEGORY_NAME", unique=true, nullable=false, length=45)
	private String categoryName;

	//bi-directional many-to-one association to Monument
	@OneToMany(mappedBy="category", fetch=FetchType.LAZY)
	private List<Monument> monuments;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Monument> getMonuments() {
		return this.monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	public Monument addMonument(Monument monument) {
		getMonuments().add(monument);
		monument.setCategory(this);

		return monument;
	}

	public Monument removeMonument(Monument monument) {
		getMonuments().remove(monument);
		monument.setCategory(null);

		return monument;
	}

}