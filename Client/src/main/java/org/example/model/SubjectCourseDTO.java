package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.security.auth.Subject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectCourseDTO {

    int id;
    Subject subject;
    CourseDTO course;

    public SubjectCourseDTO() {
    }

    public SubjectCourseDTO(int id, CourseDTO course, Subject subject) {
        this.id = id;
        this.course = course;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}