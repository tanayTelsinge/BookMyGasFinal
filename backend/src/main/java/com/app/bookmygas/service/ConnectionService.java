package com.app.bookmygas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.bookmygas.entity.ConnectionRequest;
import com.app.bookmygas.entity.GasAgency;
import com.app.bookmygas.entity.User;
import com.app.bookmygas.repository.AgencyRepository;
import com.app.bookmygas.repository.ConnectionRepository;
import com.app.bookmygas.repository.UserRepository;

import java.util.List;

@Service
public class ConnectionService {

	@Autowired
	private ConnectionRepository connectionRequestRepository;
	@Autowired
	private AgencyRepository agencyRepository;
	@Autowired
	private UserRepository userRepository;

	public ConnectionRequest createConnectionRequest(ConnectionRequest request) {
		GasAgency gasAgency = agencyRepository.findById(request.getGasAgency().getAgencyId()).get();

		User user = userRepository.findById(request.getUser().getUserId()).get();

		request.setUser(user);
		request.setGasAgency(gasAgency);
		return connectionRequestRepository.save(request);
	}

	public ConnectionRequest getConnectionRequestById(Integer id) {
		return connectionRequestRepository.findById(id).orElse(null);
	}

	public List<ConnectionRequest> getAllConnectionRequests() {
		return connectionRequestRepository.findAll();
	}

	public ConnectionRequest updateConnectionRequest(Integer id, ConnectionRequest request) {
		if (connectionRequestRepository.existsById(id)) {
			request.setRequestId(id);
			return connectionRequestRepository.save(request);
		}
		return null;
	}

	public boolean deleteConnectionRequest(Integer id) {
		if (connectionRequestRepository.existsById(id)) {
			connectionRequestRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
