package org.example;

import org.example.model.StudentDTO;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/*public class StudentXmlParser extends DefaultHandler {

    protected String tagContent;


    //we declare both so we can use them globally
    private List<StudentDTO> studentDTOS;
    private StudentDTO currentStudentDTO;

    public StudentXmlParser() {
        this.studentDTOS = new ArrayList<>();
        this.currentStudentDTO = null;
    }

    public List<StudentDTO> getStudents() {
        return studentDTOS;
    }

    public void setStudents(List<StudentDTO> studentDTOS) {
        this.studentDTOS = studentDTOS;
    }

    //This method will return the studentDTOS' list
    public List<StudentDTO> getStudentList() {
        return this.studentDTOS;
    }

    //We start when we read an opening tag to create the object student
    //so in order to create the student we look for the tag that contains all information
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("student")) {
            currentStudentDTO = new StudentDTO();
        }
    }

    //Returns the content within the tags
    public void characters(char ch[], int start, int length)
            throws SAXException {
        tagContent = new String(ch, start, length);
    }

    //method that will execute when the tag is of closure
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (currentStudentDTO != null) {

            switch (qName.toLowerCase()) {
                //we set the attributes that will have the student's information
                case "idcard":
                    currentStudentDTO.setIdcard(tagContent);
                    break;
                case "firstname":
                    currentStudentDTO.setFirstname(tagContent);
                    break;
                case "lastname":
                    currentStudentDTO.setLastname(tagContent);
                    break;
                case "email":
                    currentStudentDTO.setEmail(tagContent);
                    break;
                case "phone":
                    currentStudentDTO.setPhone(tagContent);
                    break;
                //when we find the end tag of student, this means that we have all information of this student
                //we save the student within the object StudentDTO
                //we reset the information to add a new student
                case "student":
                    if(currentStudentDTO.getIdcard().length()==8) {
                        studentDTOS.add(currentStudentDTO);
                        currentStudentDTO = null;
                    }else{
                        throw new RuntimeException("The IDCARD must have 8 characteres. No Students added");
                    }
                    break;
            }
        }
    }

    List<StudentDTO> read(String file) throws RuntimeException {
        try {
            //we call the parser
            SAXParser saxParser = SAXParserFactory.
                    newInstance().newSAXParser();

            //we parse the file that we indicate in route

            saxParser.parse(file, this);
            return this.getStudents();
        }catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }catch (FileNotFoundException e){
            System.err.println("File not found");
        } catch (Exception e ) {
            e.printStackTrace();
        }
        //we return the studentDTOS
        return getStudentList();
    }

}*/