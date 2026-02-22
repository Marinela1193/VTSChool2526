package org.example;

import org.example.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


/*public class Menu {

    private String[] args;

    public Menu(String[] args) {
        this.args = args;
    }

    public void start() {

        if (args.length == 0) {
            System.err.print("No arguments to read \n");
            helpScreen();
            return;
        }

        String arg = args[0];
        switch (arg) {
            case "-h":
            case "--help":
                helpScreen();
                break;
            case "-a":
            case "--add":
                if (this.args.length > 1) {
                    String filename = this.args[1];
                    addStudentsXMLFile(filename);
                } else {
                    System.err.println("The filename is mandatory");
                    helpScreen();
                }
                break;
            case "-e":
            case "--enroll":
                if (this.args.length > 2) {
                    String idCard = this.args[1];
                    int courseid  = Integer.parseInt(this.args[2]);
                    enrollStudent(idCard, courseid);
                } else {
                    System.err.println("Two parameters required");
                    helpScreen();
                }
                break;
            case "-p":
            case "--print":
                if (this.args.length > 2) {
                    String idCard = this.args[1];
                    int courseid  = Integer.parseInt(this.args[2]);
                    printScores(idCard, courseid);
                } else {
                    System.err.println("Two parameters required");
                    helpScreen();
                }
                break;
            case "-q":
            case "--qualify":
                if (this.args.length > 2) {
                    String idCard = this.args[1];
                    int courseid  = Integer.parseInt(this.args[2]);
                    introScores(idCard, courseid);
                } else {
                    System.err.println("Two parameters required");
                    helpScreen();
                }
                break;
        }
    }

    public static void helpScreen() {

        System.out.println("Options:");
        System.out.println("-h, --help: show this help");
        System.out.println("-a, --add {filename.xml}: add the students in the XML file to the database.");
        System.out.println("-e, --enroll {studentId} {courseId}: enroll a student in a course");
        System.out.println("-p, --print {studentId} {courseId}: show the scores of a student in a course");
        System.out.println("-q, --qualify {studentId} {courseId}: introduce the scores obtained by the student in the course.");

    }

    public static void addStudentsXMLFile(String filename) {
        try{
            //we declare the class that contains the method to read the students
            StudentXmlParser myXMLStudentsHandler = new StudentXmlParser();

            //we declare a list were the students will be added
            List<StudentDTO> studentsXML = myXMLStudentsHandler.read(filename);

            addListToDataBase(studentsXML);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addListToDataBase(List<StudentDTO> studentDTOList) throws RuntimeException {

        try(Session session = SessionFactory.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            try {
                for (StudentDTO studentDTO : studentDTOList) {
                    System.out.println(studentDTO);
                    if (studentDTO.existsId(studentDTO.getIdcard())) {
                        throw new RuntimeException("IDCARD:  " + studentDTO.getIdcard() + " is already in the system.");
                    }

                    if (!studentDTO.checkEmail()) {
                        throw new RuntimeException("IDCARD:  " + studentDTO.getIdcard() + " has an invalid email");

                    }

                    if (!studentDTO.checkPhoneNumber()) {
                        throw new RuntimeException("IDCARD:  " + studentDTO.getIdcard() + " has an invalid phone number");

                    }
                    session.persist(studentDTO);
                }
            }catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                throw new RuntimeException("No students added to the system");
            }
            transaction.commit();

            System.out.println(studentDTOList.size() + " StudentDTO(s) added correctly");
        }
    }

    public static void printScores(String idCard, int idCourse) {

        try (Session session = SessionFactory.getSessionFactory().openSession()) {

            //check that the IDCard is valid
            StudentDTO studentDTO = new StudentDTO();
            if (!studentDTO.checkIdCard()) {
                System.err.println("IDCARD:  " + idCard + " is invalid, it must have 8 characteres.");
                return;
            }
            //check the IDCard exists
            if (!studentDTO.existsId(idCard)) {
                System.err.println("IDCARD:  " + idCard + " does not exist in the system");
                return;
            }

            //check the IDCours exists
            CourseDTO course = new CourseDTO();
            if (!course.checkCourse(idCourse)) {
                System.err.println("IDCourse: " + idCourse + " does not exist in the system.");
                return;
            }


            List<ScoreDTO> studentInfoList = studentDTO.studentInfo(idCard);
            ScoreDTO score = new ScoreDTO();
            score.printScores(studentInfoList);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enrollStudent(String idCard, int idCourse) {
        /*The -e / --enroll option will enroll an existing student (idCard must be provided)
        in an existing course. */
        /*Transaction transaction = null;
        try (Session session = SessionFactory.getSessionFactory().openSession()) {

            //check that the IDCard is valid
            StudentDTO studentDTO = new StudentDTO();
            if (!studentDTO.checkIdCard()) {
                System.err.println("IDCARD:  " + idCard + " is invalid, it must have 8 characteres.");
                return;
            }
            //check the IDCard exists
            if (!studentDTO.existsId(idCard)) {
                System.err.println("IDCARD:  " + idCard + " does not exist in the system");
                return;
            }

            //check the IDCours exists
            CourseDTO course = new CourseDTO();
            if (!course.checkCourse(idCourse)) {
                System.err.println("IDCourse: " + idCourse + " does not exist in the system.");
                return;
            }

            //check if studentDTO has completed the course
            /*if (studentDTO.completedCourse(idCard, idCourse)) {
                System.err.println("The studentDTO has already completed this course and cannot enroll again.");
                return;
            }*/
            //check table enrollments has id and course
           /* EnrollmentDTO enrollment = new EnrollmentDTO();
            SubjectDTO subject = new SubjectDTO();
            ScoreDTO score = new ScoreDTO();
            transaction = session.beginTransaction();

            if (!enrollment.checkEnrollment(idCard, idCourse)) {
                System.err.println("IDCard: " + idCard + " does not exist in the Course: " + idCourse + " we will proceed to enroll the studentDTO in the first year");

                enrollment.createEnrollment(idCard, idCourse);

                List<SubjectDTO> subjectsToEnroll = subject.getSubjectsFirstYear(idCourse);

                for (SubjectDTO s : subjectsToEnroll) {
                    score.createScore();
                }
            } else {

                List<SubjectDTO> passedSubjects = subject.getSubjectsPassed(idCard);
                int count = 0;
                for (SubjectDTO s : passedSubjects) {
                    count++;
                }

                if (count == 5) {
                    System.out.println("The studentDTO has already passed the course");
                    return;
                } else {
                    List<SubjectDTO> failedSubjects = subject.getSubjectsFailed(idCard);

                    for (SubjectDTO s : failedSubjects) {
                        score.createScore();
                    }

                    List<SubjectDTO> secondYearSubjects = subject.getSubjectsSecondYear(idCourse);
                    for (SubjectDTO s : secondYearSubjects) {
                        score.createScore();
                    }
                }
                transaction.commit();
                System.out.println("The studentDTO has been enrolled in the subjects failed and 2nd year subjects");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error during enrollment: " + e.getMessage());
        }

    }

    public static void introScores (String idCard,int idCourse){

            StudentDTO studentDTO = new StudentDTO();
            if (!studentDTO.checkIdCard()) {
                System.err.println("IDCARD:  " + idCard + " is invalid, it must have 8 characteres.");
                return;
            }
            //check the IDCard exists
            if (!studentDTO.existsId(idCard)) {
                System.err.println("IDCARD:  " + idCard + " does not exist in the system");
            }

            //check the IDCours exists
            CourseDTO course = new CourseDTO();
            if (!course.checkCourse(idCourse)) {
                System.err.println("IDCourse: " + idCourse + " does not exist in the system.");
            }

            try (Session session = SessionFactory.getSessionFactory().openSession()) {
//            Scanner sc = new Scanner(System.in);

                //We create a list of the subjects this studentDTO is enrolled and
                ScoreMethods scoreMethods = new ScoreMethods();
                List<ScoreDTO> scoresStudent = scoreMethods.getScores(idCard);
                scoreMethods.addScores(session, scoresStudent);

                System.out.println("All scores updated successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}*/



