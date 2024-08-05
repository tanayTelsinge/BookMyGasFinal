package com.app.bookmygas.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmygas.entity.Staff;
import com.app.bookmygas.repository.StaffRepository;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff getStaffById(int id) {
        return staffRepository.findById(id).orElse(null);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff updateStaff(int id, Staff staff) {
        if (staffRepository.existsById(id)) {
            staff.setStaffId(id);
            return staffRepository.save(staff);
        }
        return null;
    }

    public boolean deleteStaff(int id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
