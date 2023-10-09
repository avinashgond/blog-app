package com.blog.app.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User Entity
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	/**
	 * id -- Primary key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * name -- contains user's name
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * email -- contains user's email
	 */
	@Column(nullable = false)
	private String email;
	
	/**
	 * password --  contains user's password
	 */
	@Column(nullable = false)
	private String password;
	
	/**
	 * about -- contains details about user
	 */
	private String about;
}
