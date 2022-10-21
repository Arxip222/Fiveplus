package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepo extends CrudRepository<Application, Long> {
}
