package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Topic;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

}
