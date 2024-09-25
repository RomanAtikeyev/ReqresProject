package reqres.api.registration;

import api.reqres.dtos.basic.UserDto;
import api.reqres.dtos.request.RegisterRequestDto;
import api.reqres.services.RegisterApi;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import steps.UserApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationPositiveTests {
    private UserDto existedUser;
    private String randomPassword;

    @BeforeAll
    void prepareData() {
        existedUser = UserApiSteps.getExistedUser();
        randomPassword = RandomValuesUtils.getRandomAlphanumericString(10);
    }

    @Test
    public void registerExistedUser() {
        var response = RegisterApi.register(RegisterRequestDto.builder()
                .email(existedUser.getEmail())
                .password(randomPassword)
                .build());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(),
                "Статус код ответа не соответствует ожидаемому");

        var responseBody = Objects.requireNonNull(response.getBody());
        assertAll(
                () -> assertEquals(existedUser.getId(), responseBody.getId(),
                        "Id тела ответа не соответствует ожидаемому"),
                () -> assertNotNull(responseBody.getToken(),
                        "Токен из тела ответа равен null")
        );
    }
}
