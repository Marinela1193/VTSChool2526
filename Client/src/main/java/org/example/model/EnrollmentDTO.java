package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class EnrollmentDTO {

    int id;
    int year;
    StudentDTO student;
    CourseDTO course;
    ScoreDTO scores;

    public EnrollmentDTO() {
    }

    public EnrollmentDTO(int id, int year, StudentDTO student, CourseDTO course, ScoreDTO scores) {
        this.id = id;
        this.year = year;
        this.student = student;
        this.course = course;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public ScoreDTO getScores() {
        return scores;
    }

    public void setScores(ScoreDTO scores) {
        this.scores = scores;
    }

    /*public boolean checkEnrollment(String idCard, int courseCode) {
        try(Session session = SessionFactory.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                    "FROM EnrollmentDTO e " +
                            "WHERE e.studentDTO.idcard = :studentId " +
                            "AND e.course.id = :courseId",
                    Long.class
            )
            .setParameter("studentId", idCard)
            .setParameter("courseId", courseCode)
                    .uniqueResult();

           return count != null && count > 0;

        }
    }

    public void createEnrollment(String idCard, int courseCode) {
        try(Session session = SessionFactory.getSessionFactory().openSession()) {

            EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
            //in order to introduce the studentDTO in the first year
            //we create the course c in order to assign the year 1
            CourseDTO c = session.find(CourseDTO.class, course);
            enrollmentDTO.setStudent(studentDTO);
            enrollmentDTO.setCourse(c);
            enrollmentDTO.setYear(2025);
            //we add the studentDTO in all courses of 1 year

            session.persist(enrollmentDTO);
        }
    }*/

}