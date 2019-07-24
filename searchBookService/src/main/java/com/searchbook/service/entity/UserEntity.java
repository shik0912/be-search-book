package com.searchbook.service.entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserEntity {
	@Id
	private String id;
	@Column(nullable = false)
	private String password;
	
	protected UserEntity() {
		
	}
	
	public UserEntity(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}
	public String getPassword() {
		return this.password;
	}
}
