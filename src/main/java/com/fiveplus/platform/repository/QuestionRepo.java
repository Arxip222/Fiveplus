package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepo extends CrudRepository<Question, Long> {
}
