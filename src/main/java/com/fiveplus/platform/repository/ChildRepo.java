package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Child;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepo extends CrudRepository<Child, Long> {
}
