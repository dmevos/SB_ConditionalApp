package ru.osipov.conditionalapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalAppApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
//    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
//        prodApp.start();
    }

    @Test
    void contextLoads() {
        var devAppUrl = "http://localhost:" + devApp.getMappedPort(8080) + "/profile";
//        var prodAppUrl = "http://localhost:" + prodApp.getMappedPort(8081) + "/profile";

        ResponseEntity<String> devEntity = restTemplate.getForEntity(devAppUrl, String.class);
//        ResponseEntity<String> prodEntity = restTemplate.getForEntity(prodAppUrl, String.class);

//        System.out.println(devEntity.getBody());
        Assertions.assertEquals("Current profile is dev", devEntity.getBody());
//        Assertions.assertEquals("Current profile is production", prodEntity.getBody());
    }

}
