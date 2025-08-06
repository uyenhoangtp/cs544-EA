package client;



import org.springframework.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.util.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;


public class ApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080";

    private HttpHeaders createHeaders(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        String base64Creds = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        headers.set("Authorization", "Basic " + base64Creds);
        return headers;
    }

    public void callEndpoint(String endpoint, String username, String password) {
        String url = baseUrl + endpoint;
        HttpEntity<String> entity = new HttpEntity<>(createHeaders(username, password));

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("[" + username + "] " + endpoint + ": " + response.getStatusCode() + " - " + response.getBody());
        } catch (Exception e) {
            System.out.println("[" + username + "] " + endpoint + ": ERROR - " + e.getMessage());
        }
    }
}
