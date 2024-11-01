package com.eventManagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.eventManagement.entity.Category;

public class CategoryResponse extends CommonApiResponse{
	private List<Category> categories = new ArrayList();

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public CategoryResponse(String responseMessage, boolean isSuccess, List<Category> categories) {
		super(responseMessage, isSuccess);
		this.categories = categories;
	}

	public CategoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryResponse(String responseMessage, boolean isSuccess) {
		super(responseMessage, isSuccess);
		// TODO Auto-generated constructor stub
	} 
	
}
