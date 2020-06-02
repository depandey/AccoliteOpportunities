package com.accolite.opportunities.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accolite.opportunities.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{}
