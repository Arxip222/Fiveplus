package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Child;
import com.fiveplus.platform.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepo extends CrudRepository<Child, Long> {
    List<Child> findByParent(User parent);
}
