package com.eventManagement.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventManagement.Repository.CategoryRepo;
import com.eventManagement.entity.Category;
import com.eventManagement.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryrepo;

	@Override
	public Category addCategory(Category category) {
		return this.categoryrepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return this.categoryrepo.save(category);
	}

	@Override
	public Category getCategoryById(int categoryId) {

		Optional<Category> optionalCategory = this.categoryrepo.findById(categoryId);

		if (optionalCategory.isPresent()) {
			return optionalCategory.get();
		} else {
			return null;
		}

	}

	@Override
	public List<Category> getCategoriesByStatusIn(List<String> status) {
		return this.categoryrepo.findByStatusIn(status);
	}

}
