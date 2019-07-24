package com.searchbook.service.entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RateEntity {
	@Id
	private String keyword;
	@Column(nullable = true)
	private int number;
	
	protected RateEntity() {
		
	}
	
	public RateEntity(String keyword, int number) {
		this.keyword = keyword;
		this.number = number;
	}
	
	public String getKeyword() {
		return this.keyword;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
}
