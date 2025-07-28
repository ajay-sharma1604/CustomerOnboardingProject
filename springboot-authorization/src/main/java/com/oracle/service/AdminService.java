package com.oracle.service;

import org.springframework.stereotype.Service;

import com.oracle.model.Admin;

@Service
public interface AdminService {
	Admin registerAdmin(Admin admin);
	Admin loginAdmin(String email, String password);
}
