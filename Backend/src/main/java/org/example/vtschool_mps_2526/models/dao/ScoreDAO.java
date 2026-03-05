package org.example.vtschool_mps_2526.models.dao;

import org.example.vtschool_mps_2526.models.entities.ScoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScoreDAO extends CrudRepository<ScoreEntity, Integer> {

    //Optional<ScoreEntity> findByEmail(String email);
}
