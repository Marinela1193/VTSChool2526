package org.example;

import org.example.api.ENDPOINTS;
import org.example.api.HttpResponse;
import org.example.api.JSONMapper;
import org.example.api.RestAPIConnection;
import org.example.model.StudentDTO;
import org.hibernate.Session;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Set;


public class App {
    public static void main(String[] args) throws IOException {

    RestAPIConnection api = new RestAPIConnection();

    HttpResponse response;
    StudentDTO studentDTO = new StudentDTO();
    //create
    response = api.postRequest(ENDPOINTS.STUDENTS+ ENDPOINTS.CREATE,JSONMapper.mapToJSON(studentDTO));
    if(response.getStatusCode() == HttpURLConnection.HTTP_OK){
        System.out.println("Student Created");
    }

    //get
    response = api.getRequest(ENDPOINTS.STUDENTS + studentDTO.getIdcard());
    if(response.getStatusCode() == HttpURLConnection.HTTP_OK){
        System.out.println(JSONMapper.mapJSONObject(response.getBody(), StudentDTO.class));
    }

    //update
    studentDTO.setEmail(response.getBody());
   response = api.getRequest(ENDPOINTS.STUDENTS+studentDTO.getIdcard());
   if(response.getStatusCode() == HttpURLConnection.HTTP_OK){
       System.out.println(JSONMapper.mapJSONObject(response.getBody(), StudentDTO.class));
       System.out.println("Student updated correctly");
   }

   //get
   response = api.getRequest(ENDPOINTS.STUDENTS+studentDTO.getIdcard());
   if(response.getStatusCode() == HttpURLConnection.HTTP_OK){
       System.out.println(JSONMapper.mapJSONObject(response.getBody(), StudentDTO.class));
   }




    /*Set<StudentDTO> students = JSONMapper.mapJSONArray(response.getBody(), StudentDTO.class);

    for (StudentDTO student : students){
        System.out.println(student);
        System.out.println();
    }

    System.out.println("--------------");
    System.out.println();
    response = api.getRequest(ENDPOINTS.STUDENTS + "1486");
    System.out.println(JSONMapper.mapJSONObject(response.getBody(), StudentDTO.class));

   /*Menu menu = new Menu(args);
   menu.start();*/

    }
}
