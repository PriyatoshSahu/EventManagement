package com.eventManagement.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.eventManagement.Exception.CategorySaveFailedException;
import com.eventManagement.dto.CategoryResponse;
import com.eventManagement.dto.CommonApiResponse;
import com.eventManagement.entity.Category;
import com.eventManagement.entity.Event;
import com.eventManagement.service.CategoryService;
import com.eventManagement.service.EventService;
import com.eventManagement.utility.Constant.ActiveStatus;

@Component
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private EventService eventService;

	public ResponseEntity<CommonApiResponse> addCategory(Category category) {

		CommonApiResponse response = new CommonApiResponse();

		if (category == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		category.setStatus(ActiveStatus.ACTIVE.value());

		Category savedCategory = this.categoryService.addCategory(category);

		if (savedCategory == null) {
			throw new CategorySaveFailedException("Failed to add category");
		}

		response.setResponseMessage("Category Added Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<CommonApiResponse> updateCategory(Category category) {


		CommonApiResponse response = new CommonApiResponse();

		if (category == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (category.getId() == 0) {
			response.setResponseMessage("missing category Id");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		category.setStatus(ActiveStatus.ACTIVE.value());
		Category savedCategory = this.categoryService.updateCategory(category);

		if (savedCategory == null) {
			throw new CategorySaveFailedException("Failed to update category");
		}

		response.setResponseMessage("Category Updated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<CategoryResponse> fetchAllCategory() {

		CategoryResponse response = new CategoryResponse();

		List<Category> categories = new ArrayList<>();

		categories = this.categoryService.getCategoriesByStatusIn(Arrays.asList(ActiveStatus.ACTIVE.value()));

		if (CollectionUtils.isEmpty(categories)) {
			response.setResponseMessage("No Categories found");
			response.setSuccess(false);

			return new ResponseEntity<CategoryResponse>(response, HttpStatus.OK);
		}

		response.setCategories(categories);
		response.setResponseMessage("Category fetched successful");
		response.setSuccess(true);

		return new ResponseEntity<CategoryResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> deleteCategory(int categoryId) {


		CommonApiResponse response = new CommonApiResponse();

		if (categoryId == 0) {
			response.setResponseMessage("missing category Id");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Category category = this.categoryService.getCategoryById(categoryId);

		if (category == null) {
			response.setResponseMessage("category not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		List<Event> events = this.eventService.getEventByStatusAndCategory(ActiveStatus.ACTIVE.value(), category);

		category.setStatus(ActiveStatus.DEACTIVATED.value());
		Category updatedCategory = this.categoryService.updateCategory(category);

		if (updatedCategory == null) {
			throw new CategorySaveFailedException("Failed to delete the Category");
		}

		if (!CollectionUtils.isEmpty(events)) {

			for (Event event : events) {
				event.setStatus(ActiveStatus.DEACTIVATED.value());
			}

			List<Event> udpatedEvents = this.eventService.updateEvents(events);

			if (CollectionUtils.isEmpty(udpatedEvents)) {
				throw new CategorySaveFailedException("Failed to delete the Event Category");
			}

		}

		response.setResponseMessage("Event Category & all its Events Deleted Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

}
