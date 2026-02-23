package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectDTO {

    int id;
    String  name;
    int year;
    int hours;
    ScoreDTO score;
    SubjectCourseDTO subjectCourse;

    public SubjectDTO() {
    }

    public SubjectDTO(int id, String name, int year, int hours, ScoreDTO score, SubjectCourseDTO subjectCourse) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.hours = hours;
        this.score = score;
        this.subjectCourse = subjectCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public ScoreDTO getScore() {
        return score;
    }

    public void setScore(ScoreDTO score) {
        this.score = score;
    }

    public SubjectCourseDTO getSubjectCourse() {
        return subjectCourse;
    }

    public void setSubjectCourse(SubjectCourseDTO subjectCourse) {
        this.subjectCourse = subjectCourse;
    }

    /*public List<SubjectDTO> getSubjectsFirstYear(int courseId) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("SELECT sc.subject FROM SubjectCourseDTO sc WHERE sc.course.id = :courseId AND sc.subject.year = 1", SubjectDTO.class)
            .setParameter("courseId", courseId)
                    .getResultList();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<SubjectDTO> getSubjectsSecondYear(int courseId) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("SELECT sc.subject " +
                    "FROM SubjectCourseDTO sc " +
                    "WHERE sc.course.id = :courseId " +
                    "AND sc.subject.year = 2", SubjectDTO.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<SubjectDTO> getSubjectsFailed(String idCard) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return  session.createNativeQuery(
                    "SELECT * FROM subjectsPending_mps_2526(:studentId)"
            ).setParameter("studentId", idCard)
                    .addEntity(SubjectDTO.class)
                    .getResultList();
        }
    }

    public List<SubjectDTO> getSubjectsPassed(String idCard) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return  session.createNativeQuery(
                            "SELECT * FROM subjectsPassed_mps_2526(:studentId)"
                    ).setParameter("studentId", idCard)
                    .addEntity(SubjectDTO.class)
                    .getResultList();
        }
    }

    public List<SubjectDTO> subjectsStudentIsEnrolled(String idCard) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT sub " +
                                    "FROM SubjectDTO sub " +
                                    "JOIN sub.scoreDTOS s " +
                                    "JOIN s.enrollment e " +
                                    "JOIN e.student st " +
                                    "WHERE st.idcard = :studentId",
                            SubjectDTO.class
                    ).setParameter("studentId", idCard)
                    .getResultList();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }*/
}