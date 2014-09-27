package com.backendtask.web.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.backendtask.category.service.ICategoryService;
import com.backendtask.model.Picture;
import com.backendtask.picture.service.IPictureService;

@Controller
public class PictureController {

	@Autowired
	IPictureService pictureService;

	@Autowired
	ICategoryService categoryService;

	private int currentMonumentId;
	private String currentMonumentName;

	private List<Picture> picturesList = new ArrayList<Picture>();
	
	private byte[] currentPictureBuffer;
	private String currentPictureFileName;
	
	@RequestMapping(value = "/picturesPage", method = RequestMethod.GET)
	public ModelAndView picturesPage(
			@RequestParam("monumentId") int monumentId,
			@RequestParam("monumentName") String monumentName) {
		currentMonumentId = monumentId;
		currentMonumentName = monumentName;
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth.getPrincipal() instanceof String) {
			if (auth.getPrincipal().equals("anonymousUser")) {
				model.setViewName("403");
			}
		} else if (auth.getPrincipal() instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			picturesList = pictureService.getPictures(monumentId,
					userDetail.getUsername());
			model.addObject("picturesList", picturesList);
			model.addObject("currentMonumentName", currentMonumentName);
			model.setViewName("picturesPage");
		}
		return model;
	}

	@RequestMapping(value = "/editPicture", method = RequestMethod.GET)
	public ModelAndView editPicture(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		for (Picture picture : picturesList) {
			if (picture.getPictureId() == id) {
				model.addObject("title", "Edit Picture");
				model.addObject("message", "Current monument is: "
						+ currentMonumentName
						+ ". Use the bellow form to edit an existing picture.");
				model.addObject("action", "saveEditedPicture");
				model.addObject("picture", picture);
				model.addObject("categories", categoryService.getCategories());
				currentPictureBuffer = picture.getPictureContent();
				currentPictureFileName = picture.getPictureFileName();
				break;
			}
		}
		model.addObject("mode", "edit");
		model.setViewName("addEditPicture");
		return model;
	}

	@RequestMapping(value = "/deletePicture", method = RequestMethod.GET)
	public String deletePicture(@RequestParam("id") int id) {
		for (Picture picture : picturesList) {
			if (picture.getPictureId() == id) {
				Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				pictureService.deletePicture(picture, userDetail.getUsername());
				break;
			}
		}
		return "successOperation";
	}

	@RequestMapping(value = "/addPicture", method = RequestMethod.GET)
	public ModelAndView addPicture() {
		ModelAndView model = new ModelAndView();
		Picture picture = new Picture();
		model.addObject("title", "Add Picture");
		model.addObject("message", "Current monument is: "
				+ currentMonumentName
				+ ". Use the bellow form to add a new picture.");
		model.addObject("action", "saveAddedPicture");
		model.addObject("picture", picture);
		model.addObject("mode", "add");
		model.setViewName("addEditPicture");
		return model;
	}

	@RequestMapping(value = "/saveEditedPicture", method = RequestMethod.POST)
	public String saveEditedPicture(Picture picture) {
		// TODO add validator
		for (Picture c : picturesList) {
			if (c.getPictureId() == picture.getPictureId()) {
				Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				picture.setPictureContent(currentPictureBuffer);
				picture.setPictureFileName(currentPictureFileName);
				pictureService.updatePicture(picture, currentMonumentId,
						userDetail.getUsername());
				break;
			}
		}

		return "successOperation";
	}

	@RequestMapping(value = "/saveAddedPicture", method = RequestMethod.POST)
	public ModelAndView saveAddedPicture(Picture picture, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		// start working with the pic
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			ModelAndView model = new ModelAndView();
			model.addObject("title", "Add Picture");
			model.addObject("message", "Current monument is: "
					+ currentMonumentName
					+ ". Use the bellow form to add a new picture.");
			model.addObject("action", "saveAddedPicture");
			model.addObject("picture", picture);
			model.setViewName("addEditPicture");
			return model;
		}

		// Some type of file processing...
		System.err.println("-------------------------------------------");

		MultipartFile file = picture.getFileData();
		String fileName = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			if (file.getSize() > 0) {
				inputStream = file.getInputStream();
				if (file.getSize() > 10000000) {
					System.out.println("File Size:::" + file.getSize());
					
					//same lines in the addPicture
					ModelAndView model = new ModelAndView();
					model.addObject("title", "Add Picture");
					model.addObject("message", "Current monument is: "
							+ currentMonumentName
							+ ". Use the bellow form to add a new picture.");
					model.addObject("action", "saveAddedPicture");
					model.addObject("picture", picture);
					model.setViewName("addEditPicture");
					return model;
				}
				System.out.println("size::" + file.getSize());
				fileName = request.getRealPath("") + File.separator + "images" + File.separator + file.getOriginalFilename();
				File dir = new File(request.getRealPath("") + File.separator + "images");
				dir.mkdirs();
				outputStream = new FileOutputStream(fileName);
				System.out.println("fileName:" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				
				picture.setPictureContent(buffer);
				picture.setPictureFileName(file.getOriginalFilename());
				FileUtils.cleanDirectory(dir);
				outputStream.close();
				inputStream.close();
			}
			// ..........................................
			session.setAttribute("uploadFile", file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// end working with the pic

		// TODO add validator
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		pictureService.addPicture(picture, currentMonumentId,
				userDetail.getUsername());

		ModelAndView model = new ModelAndView();
		model.setViewName("successOperation");
		return model;
	}
	
	@RequestMapping(value="/getPicture")
	public void getUserImage(HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		InputStream in1 = new ByteArrayInputStream(currentPictureBuffer);
		IOUtils.copy(in1, response.getOutputStream());
	}
	
	@RequestMapping(value="/getPictureById/{pictureId}")
	public void getPictureById(HttpServletResponse response , @PathVariable("pictureId") int pictureId) throws IOException{
		response.setContentType("image/jpeg");
		for(Picture picture : picturesList) {
			if(picture.getPictureId() == pictureId) {
				InputStream in1 = new ByteArrayInputStream(picture.getPictureContent());
				IOUtils.copy(in1, response.getOutputStream());
				break;
			}
		}        
	}
}