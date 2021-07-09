package com.example.onlinebookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.model.Role;
import com.example.onlinebookshop.repository.RoleRepository;
import com.example.onlinebookshop.service.impl.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> findAllById(List<Integer> listRoleId) {
		return roleRepository.findAllById(listRoleId);
	}
}