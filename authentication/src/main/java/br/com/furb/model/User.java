package br.com.furb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.furb.enumeration.Role;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name", nullable = false, length = 32)
	private String name;
	@Column(name = "role", nullable = false, length = 2)
	private Role role;
	@Column(name = "salt", nullable = false, length = 32)
	private String salt;
	@Column(name = "password", nullable = false, length = 64)
	private String password;

	public User() {

	}

	public User(Long id, String name, Role role, String salt, String password) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.salt = salt;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
