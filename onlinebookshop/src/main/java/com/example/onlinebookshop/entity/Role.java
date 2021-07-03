package com.example.onlinebookshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="roles", schema="public")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer roleId;
	@Column(name="role_name", length=60)
	@Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(Integer roleId, RoleName roleName) {
		super();
		this.roleId = roleId;
		this.name = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public RoleName getRoleName() {
		return name;
	}

	public void setRoleName(RoleName roleName) {
		this.name = roleName;
	}

	
}
