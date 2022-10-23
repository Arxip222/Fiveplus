package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Application;
import com.fiveplus.platform.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends CrudRepository<Application, Long> {
    List<Application> findByParent(User user);
}
