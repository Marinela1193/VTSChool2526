package org.example.vtschool_mps_2526.models.dao;

import org.example.vtschool_mps_2526.models.entities.SubjectCourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectCourseDAO extends CrudRepository<SubjectCourseEntity,Integer> {
    List<SubjectCourseEntity> findByCourse_Id(Integer courseId);
}
