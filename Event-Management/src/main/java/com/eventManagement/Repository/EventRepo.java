package com.eventManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eventManagement.entity.Category;
import com.eventManagement.entity.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer>{
	
//	@Query("SELECT DISTINCT e.Category FROM category e")
//	List<String> findDistinctCategory();
	
	List<Event> findByStatusOrderByIdDesc(String status);
	
	List<Event> findByStatusAndStartDateGreaterThan(String status, String sartDate);
	

	List<Event> findByStatusAndEventNameContainingIgnoreCaseAndStartDateGreaterThan(String status, String eventName, String startDate);

	
	List<Event> findByStatusAndCategoryAndStartDateGreaterThan(String status, Category category, String startDate);

	List<Event> findByStatusAndCategory(String status, Category category);

}
