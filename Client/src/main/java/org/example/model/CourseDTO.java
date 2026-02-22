package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.example.SessionFactory;
import org.hibernate.Session;

import java.util.LinkedHashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {

    int id;
    String name;

    public CourseDTO() {
    }

    public CourseDTO(int id, String name) {
        this.id = id;
        this.name = name;
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

    /*public boolean checkCourse(int coursCode) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                            "SELECT COUNT(c) " +
                                    "FROM CourseDTO c " +
                                    "WHERE c.id = :code", Long.class)
                    .setParameter("code", coursCode)
                    .uniqueResult();

            return count != null && count > 0;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalSubjects(int courseId) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                            "SELECT COUNT(sc.subject) " +
                                    "FROM SubjectCourseDTO sc " +
                                    "WHERE sc.course.id = :courseId", Long.class)
                    .setParameter("courseId", courseId)
                    .uniqueResult();

            return count != null ? count.intValue() : 0;
        } catch (RuntimeException e) {
            System.err.println("Error getting total subjects: " + e.getMessage());
            return 0;
        }
    }*/

}