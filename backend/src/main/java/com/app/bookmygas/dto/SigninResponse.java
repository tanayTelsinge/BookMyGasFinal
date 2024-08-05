package com.app.bookmygas.dto;

import com.app.bookmygas.entity.User;
import com.app.bookmygas.security.CustomUserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SigninResponse {
	private String jwt;
	private String mesg;
	private User user;
	public SigninResponse(String jwt, String mesg) {
		this.jwt = jwt;
		this.mesg = mesg;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMesg() {
		return mesg;
	}
	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
