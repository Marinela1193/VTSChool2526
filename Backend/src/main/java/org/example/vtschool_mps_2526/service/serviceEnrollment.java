package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.CourseDAO;
import org.example.vtschool_mps_2526.models.dao.EnrollmentDAO;
import org.example.vtschool_mps_2526.models.dao.StudentsDAO;
import org.example.vtschool_mps_2526.models.dto.EnrollmentDTO;
import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.example.vtschool_mps_2526.models.entities.EnrollmentEntity;
import org.example.vtschool_mps_2526.models.entities.StudentEntity;
import org.example.vtschool_mps_2526.models.utils.EnrollmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class serviceEnrollment {

    @Autowired
    private EnrollmentDAO enrollmentDAO;
    @Autowired
    private StudentsDAO studentsDAO;
    @Autowired
    private CourseDAO courseDAO;

    public List<EnrollmentDTO> getEnrollments() {
        List<EnrollmentDTO> enrollmentsList = new ArrayList<>();

        for (EnrollmentEntity enrollmentEntity : enrollmentDAO.findAll()) {
            enrollmentsList.add(EnrollmentMapper.INSTANCE.mapEnrollmentEntityToEnrollmentDTO(enrollmentEntity));
        }
        return enrollmentsList;
    }

    public EnrollmentDTO getEnrollmentById(int id) {
        Optional<EnrollmentEntity> enrollmentEntity = enrollmentDAO.findById(id);
        return enrollmentEntity.isPresent() ? EnrollmentMapper.INSTANCE.mapEnrollmentEntityToEnrollmentDTO(enrollmentEntity.get()) : null;
    }


     public EnrollmentEntity saveEnrollment(EnrollmentDTO enrollmentDTO) {
         Optional<EnrollmentEntity> enrollmentEntity = enrollmentDAO.findById(enrollmentDTO.getId());

         if(enrollmentEntity.isPresent()) {
             return enrollmentDAO.save(EnrollmentMapper.INSTANCE.mapEnrollmentDTOToEnrollmentEntity(enrollmentDTO));
         }
         return null;
    }

    public boolean enrollStudent(String studentIdCard, int courseCode) {

        Optional<StudentEntity> studentOpt = studentsDAO.findById(studentIdCard);
        Optional<CourseEntity> courseOpt = courseDAO.findById(courseCode);

        if (studentOpt.isEmpty() || courseOpt.isEmpty()) {
            return false;
        }

        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setStudent(studentOpt.get());
        enrollment.setCourse(courseOpt.get());
        enrollment.setYear(LocalDate.now().getYear());

        enrollmentDAO.save(enrollment);

        return true;
    }

    public boolean deleteEnrollmentById(int id) {
        Optional<EnrollmentEntity> enrollmentEntity = enrollmentDAO.findById(id);

        if(enrollmentEntity.isPresent()) {
            enrollmentDAO.deleteById(id);
            return true;
        }
        return false;
    }

    public EnrollmentEntity updateEnrollment(EnrollmentDTO enrollmentDTO) {

        Optional<EnrollmentEntity> enrollmentEntity = enrollmentDAO.findById(enrollmentDTO.getId());

        if(enrollmentEntity.isPresent()) {
            return enrollmentDAO.save(EnrollmentMapper.INSTANCE.mapEnrollmentDTOToEnrollmentEntity(enrollmentDTO));
        }
        return null;
    }


}
