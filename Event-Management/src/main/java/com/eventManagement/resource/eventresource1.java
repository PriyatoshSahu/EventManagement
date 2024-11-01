//package com.eventManagement.resource;
//
//public class eventresource1 {
//
//	package com.eventManagement.resource;
//
//	import java.io.InputStream;
//	import java.time.LocalDate;
//	import java.time.LocalDateTime;
//	import java.time.ZoneId;
//	import java.util.Arrays;
//	import java.util.List;
//
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.core.io.Resource;
//	import org.springframework.http.HttpStatus;
//	import org.springframework.http.ResponseEntity;
//	import org.springframework.stereotype.Component;
//	import org.springframework.util.CollectionUtils;
//	import org.springframework.util.FileCopyUtils;
//
//	import com.eventManagement.Exception.EventSaveFailedException;
//	import com.eventManagement.dto.AddEventRequestDto;
//	import com.eventManagement.dto.CommonApiResponse;
//	import com.eventManagement.dto.EventResponseDto;
//	import com.eventManagement.entity.Category;
//	import com.eventManagement.entity.Event;
//	import com.eventManagement.service.CategoryService;
//	import com.eventManagement.service.EventService;
//	import com.eventManagement.service.StorageService;
//	import com.eventManagement.service.UserService;
//	import com.eventManagement.utility.Constant.ActiveStatus;
//
//	import jakarta.servlet.ServletOutputStream;
//	import jakarta.servlet.http.HttpServletResponse;
//
//	@Component
//	public class EventResource {
//
//		@Autowired
//		private EventService eventservice;
//		
//		@Autowired
//		private StorageService storageservice;
//		
//		@Autowired
//		private UserService userservice;
//		
//		@Autowired
//		private CategoryService categoryservice;
//
//		public ResponseEntity<CommonApiResponse> addEvent(AddEventRequestDto request) {
//
//		    CommonApiResponse response = new CommonApiResponse();
//
//		    if (isInvalidRequest(request)) {
//		        response.setResponseMessage("Bad request - missing or invalid input");
//		        response.setSuccess(false);
//		        return ResponseEntity.badRequest().body(response);
//		    }
//
//		    
//		    
//		    long addedTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//		    try {
//		        // Parse startDate in "yyyy-MM-dd" format to LocalDate
//		        LocalDate eventStartDate = LocalDate.parse(request.getStartDate());
//		        // Convert to start of the day in milliseconds
//		        long eventStartTime = eventStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//		        if (eventStartTime < addedTime) {
//		            response.setResponseMessage("Event Start Time should be a Future Date!!!");
//		            response.setSuccess(false);
//		            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		        }
//		    } catch (Exception e) {
//		        response.setResponseMessage("Invalid date format for Event Start Time. Use 'yyyy-MM-dd'.");
//		        response.setSuccess(false);
//		        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		    }
//		     
//		    Category category = categoryservice.getCategoryById(request.getCategoryId());
//		    if (category == null) {
//		        response.setResponseMessage("Event Category not found");
//		        response.setSuccess(false);
//		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//		    }
//
//		    try {
//		        Event event = createEventFromRequest(request, category);
//		        Event savedEvent = eventservice.addEvent(event);
//
//		        response.setResponseMessage("Event added successfully");
//		        response.setSuccess(true);
//		        return ResponseEntity.ok(response);
//
//		    } catch (Exception e) {
//		        throw new EventSaveFailedException(e.getMessage());
//		    }
//		}
//
//		
//		private boolean isInvalidRequest(AddEventRequestDto request) {
//		    return request == null || request.getCategoryId() == 0 || 
//		           request.getName() == null || request.getDescription() == null || 
//		           request.getLocation() == null || request.getNoOfTickets() <= 0 || 
//		           request.getVenueName() == null || request.getVenueType() == null || 
//		           request.getStartDate() == null || request.getEndDate() == null;
//		}
//
//		private boolean isPastEventStartTime(String startDate) {
//		    long currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//		    return Long.parseLong(startDate) < currentTime;
//		}
//
//		private Event createEventFromRequest(AddEventRequestDto request, Category category) {
//			String eventImage= storageservice.store(request.getImage());
//		    Event event = AddEventRequestDto.toEntity(request);
//		    event.setTicketAvailable(request.getNoOfTickets());
//		    event.setImage(eventImage);
//		    event.setCategory(category);
//		    event.setStatus(ActiveStatus.ACTIVE.value());
//		    event.setStartDate(String.valueOf(System.currentTimeMillis()));
//		    return event;
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		public ResponseEntity<CommonApiResponse> updateEvent(AddEventRequestDto request) {
//
//		    CommonApiResponse response = new CommonApiResponse();
//
//		    if (isInvalidRequest(request)) {
//		        response.setResponseMessage("Bad request - missing or invalid input");
//		        response.setSuccess(false);
//		        return ResponseEntity.badRequest().body(response);
//		    }
//
//		    Event dbEvent = eventservice.getEventById(request.getId());
//		    if (dbEvent == null) {
//		        response.setResponseMessage("Event not found");
//		        response.setSuccess(false);
//		        return ResponseEntity.badRequest().body(response);
//		    }
//
//		    if (isPastEventStartTime(request.getStartDate())) {
//		        response.setResponseMessage("Event Start Time should be a future date");
//		        response.setSuccess(false);
//		        return ResponseEntity.badRequest().body(response);
//		    }
//
//		    Event updatedEvent = updateEventFromRequest(dbEvent, request);
//
//		    try {
//		        eventservice.updateEvent(updatedEvent);
//		        response.setResponseMessage("Event updated successfully");
//		        response.setSuccess(true);
//		        return ResponseEntity.ok(response);
//
//		    } catch (Exception e) {
//		        throw new EventSaveFailedException(e.getMessage());
//		    }
//		}
//
//
//
//		private Event updateEventFromRequest(Event dbEvent, AddEventRequestDto request) {
//		    Event event = AddEventRequestDto.toEntity(request);
//		    event.setId(dbEvent.getId());
//		    event.setStartDate(dbEvent.getStartDate());
//		    event.setCategory(dbEvent.getCategory());
//		    event.setStartDate(request.getStartDate() != null && !request.getStartDate().isEmpty() ? request.getStartDate() : dbEvent.getStartDate());
//		    event.setEndDate(request.getEndDate() != null && !request.getEndDate().isEmpty() ? request.getEndDate() : dbEvent.getEndDate());
//		    event.setImage(storageservice.store(request.getImage()));
//		    event.setStatus(ActiveStatus.ACTIVE.value());
//		    event.setTotalTicket(request.getAvailableTickets());
//
//		    if (dbEvent.getCategory().getId() != (request.getCategoryId())) {
//		        Category category = categoryservice.getCategoryById(request.getCategoryId());
//		        event.setCategory(category);
//		    }
//
//		    return event;
//		}
//		
//		
//		
//		
//
//		public ResponseEntity<EventResponseDto> fetchActiveEvents() {
//
//			String currentTime = String
//					.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//
//			EventResponseDto response = new EventResponseDto();
//
//			List<Event> events = this.eventservice.findByStatusAndStartDateGreaterThan(ActiveStatus.ACTIVE.value(),
//					currentTime);
//
//			
//			
//			if (CollectionUtils.isEmpty(events)) {
//				response.setResponseMessage("Events not found");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//			}
//
//			response.setEvents(events);
//			response.setResponseMessage("Events fetched successful!!");
//			response.setSuccess(true);
//
//			return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//		}
//		
//		
//		
//		
//		
//		
//		
//		public ResponseEntity<EventResponseDto> fetchAllEventsByStatus(String status) {
//
//			EventResponseDto response = new EventResponseDto();
//
//			if (status == null) {
//				response.setResponseMessage("missing input!!!");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.BAD_REQUEST);
//			}
//
//			List<Event> events = this.eventservice.getEventByStatus(status);
//
//			if (CollectionUtils.isEmpty(events)) {
//				response.setResponseMessage("Events not found");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//			}
//
//			response.setEvents(events);
//			response.setResponseMessage("Events fetched successful!!");
//			response.setSuccess(true);
//
//			return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//		}
//		
//		
//		
//		
//		
//		
//		public ResponseEntity<EventResponseDto> fetchEventByEventId(Integer eventId) {
//
//			EventResponseDto response = new EventResponseDto();
//
//			if (eventId == null) {
//				response.setResponseMessage("missing input!!!");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.BAD_REQUEST);
//			}
//
//			Event event = this.eventservice.getEventById(eventId);
//
//			if (event == null) {
//				response.setResponseMessage("event not found!!!");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.BAD_REQUEST);
//			}
//
//			response.setEvents(Arrays.asList(event));
//			response.setResponseMessage("Events fetched successful!!");
//			response.setSuccess(true);
//
//			return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//		}
//		
//		
//		
//		
//		public ResponseEntity<EventResponseDto> fetchActiveEventsByCategory(Integer categoryId) {
//
//			EventResponseDto response = new EventResponseDto();
//
//			if (categoryId == null) {
//				response.setResponseMessage("missing input!!!");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.BAD_REQUEST);
//			}
//
//			Category category = this.categoryservice.getCategoryById(categoryId);
//
//			if (category == null) {
//				response.setResponseMessage("category not found");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//
//			String currentTime = String
//					.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//
//			List<Event> events = eventservice.getEventByStatusAndCategoryAndStartDateGreaterThan(ActiveStatus.ACTIVE.value(), category, currentTime);
//
//			if (CollectionUtils.isEmpty(events)) {
//				response.setResponseMessage("Events not found");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//			}
//
//			response.setEvents(events);
//			response.setResponseMessage("Events fetched successful!!");
//			response.setSuccess(true);
//
//			return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//		}
//
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		public ResponseEntity<EventResponseDto> searchActiveEventsByName(String eventName) {
//
//			EventResponseDto response = new EventResponseDto();
//
//			if (eventName == null) {
//				response.setResponseMessage("missing input!!!");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.BAD_REQUEST);
//			}
//
//			String currentTime = String
//					.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//
//			List<Event> events =eventservice.findByStatusAndNameContainingIgnoreCaseAndStartDateGreaterThan(
//					ActiveStatus.ACTIVE.value(), eventName, currentTime);
//
//			if (CollectionUtils.isEmpty(events)) {
//				response.setResponseMessage("Events not found");
//				response.setSuccess(false);
//
//				return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//			}
//
//			response.setEvents(events);
//			response.setResponseMessage("Events fetched successful!!");
//			response.setSuccess(true);
//
//			return new ResponseEntity<EventResponseDto>(response, HttpStatus.OK);
//		}
//
//		
//		
//		
//		
//
//		public void fetchEventImage(String eventImageName, HttpServletResponse resp) {
//			Resource resource = storageservice.load(eventImageName);
//			if (resource != null) {
//				try (InputStream in = resource.getInputStream()) {
//					ServletOutputStream out = resp.getOutputStream();
//					FileCopyUtils.copy(in, out);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		
//		
//		
//		
//		
//		public ResponseEntity<CommonApiResponse> deleteEvent(Integer eventId) {
//
//			CommonApiResponse response = new CommonApiResponse();
//
//			if (eventId == null) {
//				response.setResponseMessage("missing input!!!");
//				response.setSuccess(false);
//
//				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
//			}
//
//			Event event = this.eventservice.getEventById(eventId);
//
//			if (event == null) {
//				response.setResponseMessage("event not found!!!");
//				response.setSuccess(false);
//
//				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
//			}
//			
//			event.setStatus(ActiveStatus.DEACTIVATED.value());
//			this.eventservice.updateEvent(event);
//
//			response.setResponseMessage("Events Deleted successful!!");
//			response.setSuccess(true);
//
//			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
//		}
//		
//		
//		
//		
//	}
//
//}
