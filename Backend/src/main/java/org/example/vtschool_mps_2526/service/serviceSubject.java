package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.*;
import org.example.vtschool_mps_2526.models.dto.SubjectDTO;
import org.example.vtschool_mps_2526.models.entities.*;
import org.example.vtschool_mps_2526.models.utils.SubjectMapper;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class serviceSubject {
    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private StudentsDAO studentsDAO;
    @Autowired
    private EnrollmentDAO enrollmentDAO;
    @Autowired
    private SubjectCourseDAO subjectCourseDAO;
    @Autowired
    private ScoreDAO scoreDAO;

    public List<SubjectDTO> getSubjects() {
        List<SubjectDTO> subjectsList = new ArrayList<>();
        for (SubjectEntity subjectEntity : subjectDAO.findAll()) {
            subjectsList.add(SubjectMapper.INSTANCE.mapSubjectEntityToSubjectDTO(subjectEntity));
        }
        return subjectsList;
    }

    public SubjectDTO getSubjectById(int id) {
        Optional<SubjectEntity> subjectEntity = subjectDAO.findById(id);

        return subjectEntity.isPresent() ? SubjectMapper.INSTANCE.mapSubjectEntityToSubjectDTO(subjectEntity.get()) : null;
    }


    public SubjectEntity saveSubject(SubjectDTO subject) {
        Optional<SubjectEntity> subjectEntity = subjectDAO.findById(subject.getId());

        if(subjectEntity.isPresent()) {
            return subjectDAO.save(SubjectMapper.INSTANCE.mapSubjectDTOToSubjectEntity(subject));
        }
        return null;
    }

    public SubjectEntity updateSubject(SubjectDTO subject) {
        Optional<SubjectEntity> subjectEntity = subjectDAO.findById(subject.getId());

        if(subjectEntity.isPresent()) {
            return subjectDAO.save(SubjectMapper.INSTANCE.mapSubjectDTOToSubjectEntity(subject));
        }
        return null;
    }

    public boolean deleteSubjectById(int id) {
        Optional<SubjectEntity> subjectEntity = subjectDAO.findById(id);

        if(subjectEntity.isPresent()) {
            subjectDAO.deleteById(id);
            return true;
        }
        return false;
    }

    public List<SubjectDTO> getPendingSubjectsByStudent(String studentIdCard) {
        List<SubjectDTO> pendingSubjects = new ArrayList<>();

        EnrollmentEntity latestEnrollment = null;

        for (EnrollmentEntity enrollment : enrollmentDAO.findAll()) {
            if (enrollment.getStudent().getIdcard().equals(studentIdCard)) {
                if (latestEnrollment == null || enrollment.getYear() > latestEnrollment.getYear()) {
                    latestEnrollment = enrollment;
                }
            }
        }

        if (latestEnrollment == null) {
            return pendingSubjects;
        }

        Integer courseId = latestEnrollment.getCourse().getId();
        Integer enrollmentId = latestEnrollment.getId();

        List<Integer> subjectIdsInCourse = new ArrayList<>();
        for (SubjectCourseEntity sc : subjectCourseDAO.findAll()) {
            if (sc.getCourse().getId().equals(courseId)) {
                subjectIdsInCourse.add(sc.getSubject().getId());
            }
        }

        List<Integer> scoredSubjectIds = new ArrayList<>();
        for (ScoreEntity score : scoreDAO.findAll()) {
            if (score.getEnrollment().getId().equals(enrollmentId)) {
                scoredSubjectIds.add(score.getSubject().getId());
            }
        }

        for (SubjectEntity subject : subjectDAO.findAll()) {
            if (subjectIdsInCourse.contains(subject.getId()) &&
                    !scoredSubjectIds.contains(subject.getId())) {
                pendingSubjects.add(SubjectMapper.INSTANCE.mapSubjectEntityToSubjectDTO(subject));
            }
        }

        return pendingSubjects;
    }
}
