package client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class JwtApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080";

    public void signup(String firstname, String lastname, String email, String password) {
        String url = baseUrl + "/auth/signup";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = String.format(
                "{\"firstname\":\"%s\",\"lastname\":\"%s\",\"email\":\"%s\",\"password\":\"%s\"}",
                firstname, lastname, email, password);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            System.out.println("Signup successful: " + response.getStatusCode());
        } catch (Exception e) {
            System.out.println("Signup skipped or failed: " + e.getMessage());
        }
    }

    public String signin(String email, String password) {
        String url = baseUrl + "/auth/signin";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            return (String) response.getBody().get("token");
        } catch (Exception e) {
            System.out.println("Failed to sign in: " + e.getMessage());
            return null;
        }
    }

    public void callEndpoint(String endpoint, String token) {
        String url = baseUrl + endpoint;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println(endpoint + " => " + response.getStatusCode() + ": " + response.getBody());
        } catch (Exception e) {
            System.out.println(endpoint + " => ERROR: " + e.getMessage());
        }
    }
}
