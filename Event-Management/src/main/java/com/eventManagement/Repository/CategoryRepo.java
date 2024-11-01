package com.eventManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventManagement.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	List<Category> findByStatusIn(List<String> status);

	Long countByStatusIn(List<String> status);
}
