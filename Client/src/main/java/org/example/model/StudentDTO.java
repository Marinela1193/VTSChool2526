package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.example.SessionFactory;
import org.hibernate.Session;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO {
    int idcard;
    String firstName;
    String lastName;
    String phone;
    String email;

    public StudentDTO() {
    }

    public StudentDTO(int idcard, String firstName, String lastName, String phone, String email) {
        this.idcard = idcard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getIdcard() {
        return idcard;
    }

    public void setIdcard(int idcard) {
        this.idcard = idcard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   @Override
    public String toString() {
        return idcard + " " + firstName + " " + lastName + " " + phone + " " + email;
    }

    /*public List<StudentDTO> getStudents() {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            Query myQuery = session.createQuery("SELECT s FROM StudentDTO s, StudentDTO.class");
            List<StudentDTO> studentDTOS = myQuery.getResultList();
            return studentDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public StudentDTO getStudentByIdcard(String idCard) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            Query myQuery = session.createQuery("SELECT s FROM StudentDTO s WHERE s.idcard = :idCard");
            myQuery.setParameter("idCard", idcard);
            return StudentDTO.class.cast(myQuery.getSingleResult());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean exists() {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            StudentDTO studentDTO = getStudentByIdcard(this.idcard);

            return studentDTO != null;
        }
    }

    public boolean existsId(String idCard) {
        return getStudentByIdcard(idCard) != null;
    }


    public boolean checkEmail() {
        try {
            String check = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return this.email.matches(check);
        } catch (RuntimeException e) {
            throw new RuntimeException(this.getFirstname() + " has no valid email");
        }
    }

    public boolean checkPhoneNumber() {
        try {
            return this.phone.matches("\\d{9}");
        } catch (RuntimeException e) {
            throw new RuntimeException(this.getFirstname() + " has no valid phone number");
        }
    }

    public boolean checkIdCard() {
        return this.idcard.length() == 8;
    }

    public boolean completedCourse(String idCard, int courseId) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                            "SELECT COUNT(sub) " +
                                    "FROM SubjectDTO sub " +
                                    "JOIN SubjectCourseDTO c " +
                                    "WHERE c.id = :courseId " +
                                    "AND sub.id NOT IN (" +
                                    "    SELECT s.subject.id " +
                                    "    FROM ScoreDTO s " +
                                    "    JOIN s.enrollment e " +
                                    "    JOIN e.student st " +
                                    "    WHERE st.idcard = :studentId " +
                                    "    AND s.score >= 5" +
                                    ")",
                            Long.class)
                    .setParameter("courseId", courseId)
                    .setParameter("studentId", idCard)
                    .uniqueResult();

            return count != null && count == 0;
        } catch (Exception e) {
            System.err.println("Error checking if student completed course: " + e.getMessage());
            return false;
        }
    }

    public List<ScoreDTO> studentInfo(String idCard) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT sc " +
                                    "FROM ScoreDTO sc " +
                                    "JOIN sc.enrollment e " +
                                    "JOIN e.student st " +
                                    "JOIN sc.subject sub " +
                                    "WHERE st.idcard = :studentId " +
                                    "ORDER BY e.year DESC",
                            ScoreDTO.class
                    ).setParameter("studentId", idCard)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }*/

}