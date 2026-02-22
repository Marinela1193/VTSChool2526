package org.example;

import org.example.api.ENDPOINTS;
import org.example.api.HttpResponse;
import org.example.api.JSONMapper;
import org.example.api.RestAPIConnection;
import org.example.model.StudentDTO;
import org.hibernate.Session;

import java.io.IOException;
import java.util.Set;


public class App {
    public static void main(String[] args) throws IOException {

    RestAPIConnection api = new RestAPIConnection();

    HttpResponse response = api.getRequest(ENDPOINTS.STUDENTS);
    Set<StudentDTO> students = JSONMapper.mapJSONArray(response.getBody(), StudentDTO.class);

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
