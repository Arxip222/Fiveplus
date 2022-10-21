package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    Set<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
