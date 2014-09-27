package com.backendtask.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


/**
 * The persistent class for the picture database table.
 * 
 */
@Entity
@NamedQuery(name="Picture.findAll", query="SELECT p FROM Picture p")
public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PICTURE_ID", unique=true, nullable=false)
	private int pictureId;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="PICTURE_DATE")
	private Date pictureDate;

	@Column(name="PICTURE_DESC", length=255)
	private String pictureDesc;

	@NotEmpty
	@Column(name="PICTURE_NAME", nullable=false, length=45)
	private String pictureName;

	@Column(name="PICTURE_CONTENT", nullable=false)
	private byte[] pictureContent;

	@Column(name="PICTURE_FILE_NAME", nullable=false, length=45)
	private String pictureFileName;
	
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", nullable = false)
	private User user;

	//bi-directional many-to-one association to Monument
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MONUMENT_ID", nullable =false)
	private Monument monument;

	@Transient
	private MultipartFile fileData;
	
	public Picture() {
	}

	public int getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public Date getPictureDate() {
		return this.pictureDate;
	}

	public void setPictureDate(Date pictureDate) {
		this.pictureDate = pictureDate;
	}

	public String getPictureDesc() {
		return this.pictureDesc;
	}

	public void setPictureDesc(String pictureDesc) {
		this.pictureDesc = pictureDesc;
	}

	public String getPictureName() {
		return this.pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public byte[] getPictureContent() {
		return this.pictureContent;
	}

	public void setPictureContent(byte[] pictureContent) {
		this.pictureContent = pictureContent;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Monument getMonument() {
		return this.monument;
	}

	public void setMonument(Monument monument) {
		this.monument = monument;
	}
	
	public MultipartFile getFileData() {
		return fileData;
	}
	
	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
	
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	
	public String getPictureFileName() {
		return pictureFileName;
	}
	
}