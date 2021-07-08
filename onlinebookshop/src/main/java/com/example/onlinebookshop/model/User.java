package com.example.onlinebookshop.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users", schema="public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
	@Id
	@Column(name="username")
	@Size(min=3, max = 30)
	private String username;
	@Column(name="password")
	@JsonIgnore
//	@Size(min=4, max = 100)
	private String password;
	@Column(name="full_name")
	@NotBlank
    @Size(min=3, max = 50)
	private String fullName;
	@Column(name="gender")
	private Boolean gender;
	@Column(name="address")
	private String address;
	@Column(name="phone_number")
	@Size(min=8, max = 14)
	private String phoneNumber;
	@NaturalId
	@NotBlank
	@Size(max = 50)
    @Email
	@Column(name="email")
	private String email;
	@Column(name="photo")
	private byte[] photo;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Order> orders;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Rating> ratings;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String password, String fullName, Boolean gender, String address, String phoneNumber,
			String email, byte[] photo, Set<Order> orders, Set<Rating> ratings, Set<Role> roles) {
		super();
		this.username = userName;
		this.password = password;
		this.fullName = fullName;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.photo = photo;
		this.orders = orders;
		this.ratings = ratings;
		this.roles = roles;
	}
	
	public User(String userName, String fullName, String email, String password) {
        this.username = userName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Collection<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [" + ", full name=" + fullName + ", username=" + username + ", email=" + email + ", password="
				+ password + ", roles=" + roles + "]";
	}
}