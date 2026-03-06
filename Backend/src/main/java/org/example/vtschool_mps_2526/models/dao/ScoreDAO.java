package org.example.vtschool_mps_2526.models.dao;

import org.example.vtschool_mps_2526.models.entities.ScoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreDAO extends CrudRepository<ScoreEntity, Integer> {

    List<ScoreEntity> findByEnrollmentEntity_StudentEntity_Idcard(String idcard);

    List<ScoreEntity> findByEnrollmentEntity_StudentEntity_IdcardAndEnrollmentEntity_Course_Id(String idcard, Integer courseId);
}
