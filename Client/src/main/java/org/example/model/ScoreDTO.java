package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoreDTO {

    int id;
    EnrollmentDTO enrollment;
    SubjectDTO subject;
    int score;

    public ScoreDTO() {
    }

    public ScoreDTO(int id, EnrollmentDTO enrollment, SubjectDTO subject, int score) {
        this.id = id;
        this.enrollment = enrollment;
        this.subject = subject;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnrollmentDTO getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(EnrollmentDTO enrollment) {
        this.enrollment = enrollment;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    /*public void createScore() {
        Transaction transaction = null;
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            ScoreDTO scoreDTO = new ScoreDTO();
            scoreDTO.setEnrollment(enrollmentDTO);
            scoreDTO.setSubject(subject);
            scoreDTO.setScore(null);
            session.persist(scoreDTO);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error creating score: " + e.getMessage());
        }
    }

    public void printScores(List<ScoreDTO> scoreDTOS){
        Transaction transaction = null;
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            System.out.println("Year      Subjets                            ScoreDTO");
            System.out.println("---------------------------------------------------");
            for (ScoreDTO s : scoreDTOS) {
                int year = s.getEnrollment().getYear();
                String subject = s.getSubject().getName();
                int score = s.getScore();
                System.out.println(year + " " + subject + " " + score);
                session.persist(s);
            }
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }*/


}