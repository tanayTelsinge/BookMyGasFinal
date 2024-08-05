package com.app.bookmygas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmygas.entity.GasAgency;
import com.app.bookmygas.repository.AgencyRepository;

import java.util.List;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository agencyRepository;

	public GasAgency createAgency(GasAgency agency) {
		return agencyRepository.save(agency);
	}

	public GasAgency getAgencyById(int id) {
		return agencyRepository.findById(id).orElse(null);
	}

	public List<GasAgency> getAllAgencies() {
		return agencyRepository.findAll();
	}

	public GasAgency updateAgency(int id, GasAgency agency) {
		if (agencyRepository.existsById(id)) {
			agency.setAgencyId(id);
			return agencyRepository.save(agency);
		}
		return null;
	}

	public boolean deleteAgency(int id) {
		if (agencyRepository.existsById(id)) {
			agencyRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
