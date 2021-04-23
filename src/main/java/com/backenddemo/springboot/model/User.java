package com.backenddemo.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "dob")
	private String dob;
	@Column(name = "hobby")
	private String hobby;
	@Column(name = "occupation")
	private String occupation;
	@Column(name = "location")
	private String location;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, String dob, String hobby, String occupation,
			String location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.hobby = hobby;
		this.occupation = occupation;
		this.location = location;
	}

}
