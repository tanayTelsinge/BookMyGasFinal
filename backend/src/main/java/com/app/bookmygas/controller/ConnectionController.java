package com.app.bookmygas.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.bookmygas.entity.ConnectionRequest;
import com.app.bookmygas.entity.GasAgency;
import com.app.bookmygas.entity.User;
import com.app.bookmygas.entity.Vendor;
import com.app.bookmygas.service.ConnectionService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/connections")
public class ConnectionController {

	@Autowired
	private ConnectionService connectionRequestService;

	@PostMapping
	public ResponseEntity<ConnectionRequest> createConnectionRequest(@RequestBody ConnectionRequest request) {
		if (request != null) {
			request.setRequestDate(LocalDateTime.now());
		}
		ConnectionRequest createdRequest = connectionRequestService.createConnectionRequest(request);
		return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConnectionRequest> getConnectionRequestById(@PathVariable Integer id) {
		ConnectionRequest request = connectionRequestService.getConnectionRequestById(id);
		return request != null ? new ResponseEntity<>(request, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
    public ResponseEntity<List<ConnectionRequest>> getAllConnectionRequests(@RequestParam("userId") Integer userId) {
        List<ConnectionRequest> requests = connectionRequestService.getAllConnectionRequests();
        if(userId != null) {
			List<ConnectionRequest> filteredRequests = new ArrayList<>();
			for(ConnectionRequest request : requests) {
				User user = request.getUser();
				if(user.getUserId() == userId) {
				  filteredRequests.add(request);
				}
			}
			requests.clear();
			requests.addAll(filteredRequests);
		}
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

	@PutMapping("/{id}")
	public ResponseEntity<ConnectionRequest> updateConnectionRequest(@PathVariable Integer id,
			@Valid @RequestBody ConnectionRequest request) {
		ConnectionRequest updatedRequest = connectionRequestService.updateConnectionRequest(id, request);
		return updatedRequest != null ? new ResponseEntity<>(updatedRequest, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteConnectionRequest(@PathVariable Integer id) {
		boolean isDeleted = connectionRequestService.deleteConnectionRequest(id);
		return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}