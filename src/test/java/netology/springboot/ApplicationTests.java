package netology.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devContainer = new GenericContainer<>("dev-app:latest")
            .withExposedPorts(8080, 8080)
            .withEnv("ACTIVE_PROFILE", "development");

    @Container
    private static final GenericContainer<?> prodContainer = new GenericContainer<>("prod-app:latest")
            .withExposedPorts(8090, 8090)
            .withEnv("ACTIVE_PROFILE", "production");

    @Test
    void contextLoads() {
        ResponseEntity<String> devEntity = restTemplate.getForEntity("http://localhost:" + devContainer.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals("Current profile is development", devEntity.getBody());

        ResponseEntity<String> prodEntity = restTemplate.getForEntity("http://localhost:" + prodContainer.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals("Current profile is production", prodEntity.getBody());

    }

}
