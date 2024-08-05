package com.app.bookmygas.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffid")
    private Integer staffId;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be less than or equal to 100 characters")
    private String name;

    @Column(name = "phonenumber")
    @Size(max = 15, message = "Phone number must be less than or equal to 15 characters")
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
    private String email;

    @Column(name = "position")
    @Size(max = 50, message = "Position must be less than or equal to 50 characters")
    private String position;

    @ManyToOne
    @JoinColumn(name = "agencyid", nullable = false)
    @JsonIgnore
    private GasAgency gasAgency;

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public GasAgency getGasAgency() {
		return gasAgency;
	}

	public void setGasAgency(GasAgency gasAgency) {
		this.gasAgency = gasAgency;
	}

	
}
