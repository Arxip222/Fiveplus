package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends CrudRepository<Chat, Long> {
}
