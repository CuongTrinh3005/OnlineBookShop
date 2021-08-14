package com.example.onlinebookshop.service;

import java.util.List;

import com.example.onlinebookshop.model.Role;

public interface RoleService {
	public List<Role> findAllById(List<Integer> listRoleId);
}