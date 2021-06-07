package com.moonshot.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonshot.restaurant.controller.ResturantAddressController;
import com.moonshot.restaurant.entity.AppUser;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantAddress;
import com.moonshot.restaurant.repository.AppUserRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;
	
	public List<AppUser> getAllAppUsers() {
		// TODO Auto-generated method stub
		List<AppUser> appUserList = new ArrayList<>();
		appUserRepository.findAll()
		.forEach(r->appUserList.add(r));
		return appUserList;
	}

	public AppUser getAppUser(Long id) {
		// TODO Auto-generated method stub
		return appUserRepository.findById(id);
	}

	public void addAppUser(AppUser appUser) {
		// TODO Auto-generated method stub
		appUserRepository.save(appUser);		
		
	}

	public void updateAppUser(Long id, AppUser appUser) {
		// TODO Auto-generated method stub
		AppUser existing = appUserRepository.findById(id);
		long tempId = existing.getId();
		BeanUtils.copyProperties(appUser, existing);
		existing.setId(tempId);
		appUserRepository.save(existing);		
	}

	public void deleteAppUser(Long id) {
		// TODO Auto-generated method stub
		appUserRepository.delete(id);
		
	}

}
