package org.example.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RestAPIConnection {
    protected String server = ENDPOINTS.SERVER;
    protected String apiPath = ENDPOINTS.API_PATH;

    public String getServer() { return server; }
    public String getApiPath() { return getServer()+apiPath; }

    public void setApiPath(String server, String apiPath) {
        this.server = server;
        this.apiPath = apiPath;
    }

    public RestAPIConnection() {
        setApiPath(server, apiPath);
    }

    public boolean isServerAvailable() {
        try {
            URL url = new URL(getServer());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            int responseCode = connection.getResponseCode();
            return (responseCode == 200);
        } catch (IOException e) {
            return false;
        }
    }

    public HttpResponse getRequest(String endpoint) throws IOException {

        HttpURLConnection connection = null;
        URL url = new URL(getApiPath()+endpoint);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        HttpResponse response = new HttpResponse(connection.getResponseCode(), connection.getResponseMessage(), "");

        InputStream stream = null;

        if (connection.getResponseCode() == 200) {
            stream = connection.getInputStream();
        }else  {
            stream = connection.getErrorStream();
        }

        if(stream != null) {
            Scanner scanner = new Scanner(stream, "UTF-8");
            String body = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();
            response.setBody(body);
        }

        if(connection != null) {
            connection.disconnect();
        }
        return response;
    }

    public HttpResponse gRequest(String endpoint, String codeToRetrieve) throws IOException {
        return getRequest(endpoint + "/" + codeToRetrieve);
    }

    public HttpResponse postRequest(String endpoint, String jsonInputString) throws IOException {
        return postOrPut(jsonInputString, endpoint, "POST");
    }

    public HttpResponse putRequest(String endpoint, String jsonInputString) throws IOException {
        return postOrPut(jsonInputString, endpoint, "PUT");
    }

    HttpResponse postOrPut(String jsonInputString, String endpoint, String method) throws IOException {
        HttpURLConnection connection = null;
        URL url = new URL(getApiPath()+endpoint);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        HttpResponse response = new HttpResponse(connection.getResponseCode(), connection.getResponseMessage(), "");

        InputStream stream = null;

        if(connection.getResponseCode() == 200) {
            stream = connection.getInputStream();
        }else{
            stream = connection.getErrorStream();
        }

        if(stream != null) {
            Scanner scanner = new Scanner(stream, "UTF-8");
            String body = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();
            response.setBody(body);
        }

        if(connection != null) {
            connection.disconnect();
        }
        return response;
    }

    public void delete(String endpoint, String codeToDelete) throws IOException {
        HttpURLConnection connection = null;

        URL url = new URL(getApiPath()+endpoint + "/" + codeToDelete);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        if(connection.getResponseCode() != 200) {
            throw new IOException(connection.getResponseMessage());
        }

        if(connection != null) {
            connection.disconnect();
        }
    }


















}
