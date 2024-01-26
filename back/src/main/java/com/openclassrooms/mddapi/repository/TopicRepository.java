package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Topic;

/**
 * Topic repository.
 * @author tipikae
 * @version 1.0.0
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
