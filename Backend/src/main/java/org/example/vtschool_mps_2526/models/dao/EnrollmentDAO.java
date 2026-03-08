package org.example.vtschool_mps_2526.models.dao;

import org.example.vtschool_mps_2526.models.entities.EnrollmentEntity;
import org.springframework.data.repository.CrudRepository;


public interface EnrollmentDAO extends CrudRepository<EnrollmentEntity, Integer> {
}
