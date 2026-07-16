package com.example.client;

import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.Arrays;
import java.util.List;

public class RestAPIClient {

    private static final String BASE_URL = "http://localhost:8080/api/users";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            System.out.println("=== REST API Client ===\n");

            // GET all users
            System.out.println("1. GET all users:");
            getAllUsers(httpClient);

            // GET user by ID
            System.out.println("\n2. GET user by ID (1):");
            getUserById(httpClient, 1L);

            // POST create new user
            System.out.println("\n3. POST create new user:");
            User newUser = new User(null, "Alice Brown", "alice@example.com", 25);
            createUser(httpClient, newUser);

            // PUT update user
            System.out.println("\n4. PUT update user (1):");
            User updatedUser = new User(null, "John Updated", "john.updated@example.com", 31);
            updateUser(httpClient, 1L, updatedUser);

            // DELETE user
            System.out.println("\n5. DELETE user (3):");
            deleteUser(httpClient, 3L);

            // GET all users again
            System.out.println("\n6. GET all users (final):");
            getAllUsers(httpClient);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getAllUsers(CloseableHttpClient httpClient) throws Exception {
        HttpGet httpGet = new HttpGet(BASE_URL);
        String response = httpClient.execute(httpGet, responseHandler -> {
            String body = new String(responseHandler.getEntity().getContent().readAllBytes());
            System.out.println("Status: " + responseHandler.getCode());
            return body;
        });
        List<User> users = Arrays.asList(objectMapper.readValue(response, User[].class));
        users.forEach(u -> System.out.println("  " + u));
    }

    private static void getUserById(CloseableHttpClient httpClient, Long id) throws Exception {
        HttpGet httpGet = new HttpGet(BASE_URL + "/" + id);
        String response = httpClient.execute(httpGet, responseHandler -> {
            String body = new String(responseHandler.getEntity().getContent().readAllBytes());
            System.out.println("Status: " + responseHandler.getCode());
            return body;
        });
        User user = objectMapper.readValue(response, User.class);
        System.out.println("  " + user);
    }

    private static void createUser(CloseableHttpClient httpClient, User user) throws Exception {
        HttpPost httpPost = new HttpPost(BASE_URL);
        String jsonBody = objectMapper.writeValueAsString(user);
        httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
        String response = httpClient.execute(httpPost, responseHandler -> {
            String body = new String(responseHandler.getEntity().getContent().readAllBytes());
            System.out.println("Status: " + responseHandler.getCode());
            return body;
        });
        User createdUser = objectMapper.readValue(response, User.class);
        System.out.println("  Created: " + createdUser);
    }

    private static void updateUser(CloseableHttpClient httpClient, Long id, User user) throws Exception {
        HttpPut httpPut = new HttpPut(BASE_URL + "/" + id);
        String jsonBody = objectMapper.writeValueAsString(user);
        httpPut.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
        String response = httpClient.execute(httpPut, responseHandler -> {
            String body = new String(responseHandler.getEntity().getContent().readAllBytes());
            System.out.println("Status: " + responseHandler.getCode());
            return body;
        });
        User updatedUser = objectMapper.readValue(response, User.class);
        System.out.println("  Updated: " + updatedUser);
    }

    private static void deleteUser(CloseableHttpClient httpClient, Long id) throws Exception {
        HttpDelete httpDelete = new HttpDelete(BASE_URL + "/" + id);
        httpClient.execute(httpDelete, responseHandler -> {
            System.out.println("Status: " + responseHandler.getCode());
            return null;
        });
        System.out.println("  User deleted successfully");
    }
}
