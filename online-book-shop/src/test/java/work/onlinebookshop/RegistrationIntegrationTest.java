package work.onlinebookshop;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import work.onlinebookshop.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanUsers() {
        userRepository.deleteAll();
    }

    @Test
    void registersAndPersistsAllFieldsWithoutExposingPassword() throws Exception {
        HttpResponse<String> response = register("john@example.com", "securePassword123", "John");
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).contains("john@example.com", "John", "Doe", "Main Street")
                .doesNotContain("password", "securePassword123", "repeatPassword");
        var user = userRepository.findByEmail("john@example.com").orElseThrow();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("John");
        assertThat(user.getLastName()).isEqualTo("Doe");
        assertThat(user.getShippingAddress()).isEqualTo("Main Street");
    }

    @Test
    void rejectsDuplicateEmail() throws Exception {
        register("john@example.com", "securePassword123", "John");
        assertThat(register("john@example.com", "securePassword123", "John").statusCode())
                .isEqualTo(409);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    void rejectsMismatchedPasswords() throws Exception {
        HttpResponse<String> response = register("john@example.com", "differentPassword", "John");
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.body()).contains("repeatPassword");
        assertThat(userRepository.count()).isZero();
    }

    @Test
    void rejectsInvalidEmailAndBlankName() throws Exception {
        HttpResponse<String> response = register("invalid", "securePassword123", "");
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.body()).contains("email", "firstName");
        assertThat(userRepository.count()).isZero();
    }

    private HttpResponse<String> register(String email, String repeatPassword, String firstName)
            throws Exception {
        String body = """
                {"email":"%s","password":"securePassword123","repeatPassword":"%s",
                 "firstName":"%s","lastName":"Doe","shippingAddress":"Main Street"}
                """.formatted(email, repeatPassword, firstName);
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("http://localhost:" + port + "/api/auth/registration"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
