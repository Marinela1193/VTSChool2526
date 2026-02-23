/*package org.example.model;

import org.example.SessionFactory;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class ScoreMethods {

    //method to look for the student's scores by id
    public List<ScoreDTO> getScores(String idCard) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT sc " +
                                    "FROM ScoreDTO sc " +
                                    "JOIN sc.enrollment e " +
                                    "JOIN e.student st " +
                                    "WHERE sc.score IS NULL AND st.idcard = :studentId",
                            ScoreDTO.class
                    ).setParameter("studentId", idCard)
                    .getResultList();
        }
    }

    public int checkScorevalue(int value) {
        if (value == 99) {
            System.out.println("Skipping subject.");
            return 99;
        }
        if (value < 0 || value > 10) {
            System.out.println("Invalid score. Must be between 0 and 10.");
            return -1;
        }
        return value;
    }

    public void addScores(Session session, List<ScoreDTO> scoreDTOS) {
        Scanner sc = new Scanner(System.in);

        //transaccion declarar
        for (ScoreDTO scoreDTO : scoreDTOS) {
            SubjectDTO s = scoreDTO.getSubject();
            int scoreValue;

            while(true) {

                System.out.println("Introduce the scoreDTO for subject: " + s.getName() + " (0-10 or 99 to skip)");
                //we need to make sure first the scoreDTO written goes between 0 - 10
                scoreValue = sc.nextInt();

                int checkValue = this.checkScorevalue(scoreValue);

                if (checkValue == 99) {
                    continue;
                }
                if (checkValue == -1) {
                    break;
                }

                scoreDTO.setScore(scoreValue);
                session.merge(scoreDTO);
                System.out.println("ScoreDTO for subject " + s.getName() + " updated correctly.");
                break;
            }
        }
    }
}*/
