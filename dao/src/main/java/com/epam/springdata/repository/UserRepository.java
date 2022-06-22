package com.epam.springdata.repository;

import com.epam.springdata.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
