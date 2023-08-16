package me.danilo.planeraktivnosti.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.*;

public class AuthService {

    private static AuthService instance;

    private AuthService() {}

    public static AuthService getInstance() {
        if(instance == null)
            instance = new AuthService();
        return instance;
    }

    public boolean isUsernameValid(String username) {
        if(username.isBlank() || username.length() > 30)
            return false;
        String pattern = "^(?=[a-zA-Z0-9._]{4,30}$)(?!.*[_.]{2})[^_.].*[^_.]$";
        if(!username.matches(pattern))
            return false;
        return true;
    }

    public boolean isPasswordValid(String password) {
        if(password.length() < 5)
            return false;
        return true;
    }

    public void authenticateUser(String username, String password) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String apiUrl = "http://localhost:8080/api/auth/login";

            HttpPost httpPost = new HttpPost(apiUrl);

            httpPost.setHeader("Content-Type", "application/json");
            String jsonInput = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
            StringEntity entity = new StringEntity(jsonInput);
            httpPost.setEntity(entity);

            HttpEntity responseEntity = null;

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                responseEntity = response.getEntity();

                if (responseEntity != null) {
                    String responseContent = EntityUtils.toString(responseEntity, "UTF-8");
                    JSONObject jsonResponse = new JSONObject(responseContent.trim());
                    System.out.println(jsonResponse);

                    String isAuthenticated = jsonResponse.getString("autentificated");
                    if (isAuthenticated.equalsIgnoreCase("true")) {
                        String userId = jsonResponse.getString("id");
                        System.out.println("Authentication successful. User ID: " + userId);
                    } else {
                        System.out.println("Authentication failed.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerUser(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/api/auth/register");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInput = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] inputBytes = jsonInput.getBytes("utf-8");
                os.write(inputBytes, 0, inputBytes.length);
            }

            if(connection.getResponseCode() == 400) {
                System.out.println("Username already exists!");
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
