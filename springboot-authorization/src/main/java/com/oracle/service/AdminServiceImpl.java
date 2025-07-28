package com.oracle.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.AdminDao;
import com.oracle.exception.EmailAlreadyExistsException;
import com.oracle.model.Admin;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Admin registerAdmin(Admin admin) {
		Admin existingAdmin = adminDao.findByEmail(admin.getEmail());

	    if (existingAdmin != null) {
	        throw new EmailAlreadyExistsException("Admin with email " + admin.getEmail() + " already exists.");
	    }
		return adminDao.save(admin);
	}

	@Override
	public Admin loginAdmin(String email, String password) {
	    Admin admin = adminDao.findByEmail(email);
	    if(admin != null && admin.getPassword().equals(password)) {
	        return admin;
	    }
	    return null;
	}
	
	
}
