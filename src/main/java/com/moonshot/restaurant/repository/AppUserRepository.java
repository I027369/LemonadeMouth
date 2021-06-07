package com.moonshot.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.moonshot.restaurant.entity.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

}
