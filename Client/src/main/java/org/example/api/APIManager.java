package org.example.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIManager {
    public static void main(String[] args) throws JSONException {
       /* GetRequest();
        PostRequest();
        DeleteRequest();*/
    }

    public static void GetRequest() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(
                    "http://localhost:8080/api-rest/departments");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();

                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject)
                            jsonArray.get(i);
                    System.out.println(jsonObject.get("deptno") + " "
                            + jsonObject.get("dname"));
                }
            } else
                System.out.println("Connection failed.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }

    public static void PostRequest() throws JSONException {
        HttpURLConnection conn = null;
        String jsonInputString = new JSONObject()
                .put("empno", "1234")
                .put("ename", "JONES")
                .put("job", "CLERK")
                .put("department", new JSONObject()
                        .put("deptno", 20)
                        .put("dname", "RESEARCH")
                        .put("loc", "DALLAS")
                        .toString()).toString();
        try {
            URL url = new URL("http://localhost:8080/apirest/Employees");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() == 200)
                System.out.println("Employee inserted");
            else
                System.out.println("Connection failed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }

    public static void DeleteRequest(String codeToDelete) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/" +
                    "api-rest/Employees/" + codeToDelete);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == 200)
                System.out.println("Employee deleted");
            else
                System.out.println("Connection failed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
