package reqres.api.registration;

import api.reqres.dtos.basic.UserDto;
import api.reqres.dtos.request.RegisterRequestDto;
import api.reqres.services.RegisterApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import steps.AssertApiSteps;
import steps.UserApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @DisplayName("Регистрация пользователя через POST /register с валидными данными")
    public void registerExistedUser() {
        var response = step("Вызов метода POST /register", () ->
                RegisterApi.register(RegisterRequestDto.builder()
                        .email(existedUser.getEmail())
                        .password(randomPassword)
                        .build())
        );

        AssertApiSteps.checkStatusCodeIs200(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        step("Проверка маппинга полей в теле ответа", () ->
                assertAll(
                        () -> assertEquals(existedUser.getId(), responseBody.getId(),
                                "Id тела ответа не соответствует ожидаемому"),
                        () -> assertNotNull(responseBody.getToken(),
                                "Токен из тела ответа равен null")
                )
        );
    }
}
