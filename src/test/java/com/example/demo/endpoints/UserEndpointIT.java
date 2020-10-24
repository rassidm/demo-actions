package com.example.demo.endpoints;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserEndpointIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void user_endpoint_should_return_all_users() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List<User>> response = this.restTemplate.exchange(createUrlWith("/users"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {
        });

        assertEquals(2, response.getBody().size());
    }

    @Test
    public void hello_endpoint_should_return_hello_world() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = this.restTemplate.exchange(createUrlWith("/hello"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("hello wordl!", response.getBody());
    }

    private String createUrlWith(String endpoint) {
        return "http://localhost:" + port + endpoint;
    }
}
