package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {
    List<Role> findByName(String name);

}
