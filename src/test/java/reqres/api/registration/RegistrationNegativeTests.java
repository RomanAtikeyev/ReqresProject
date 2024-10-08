package reqres.api.registration;

import api.reqres.dtos.request.RegisterRequestDto;
import api.reqres.services.RegisterApi;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import steps.AssertApiSteps;
import steps.UserApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationNegativeTests {
    private static final String NOT_DEFINED_RESPONSE = "Note: Only defined users succeed registration";
    private static final String MISSING_EMAIL_OR_USERNAME_RESPONSE = "Missing email or username";
    private static final String MISSING_PASSWORD_RESPONSE = "Missing password";
    private String existedUserEmail;
    private String notExistedUserEmail;
    private String generatedPassword;


    @BeforeAll
    void prepareData() {
        existedUserEmail = UserApiSteps.getExistedUser().getEmail();
        notExistedUserEmail = RandomValuesUtils.getEmailAddress();
        generatedPassword = RandomValuesUtils.getRandomAlphanumericString(10);
    }

    Stream<Arguments> invalidData() {
        return Stream.of(
                arguments("несуществующий пользователь", notExistedUserEmail, generatedPassword, NOT_DEFINED_RESPONSE),
                arguments("пустое поле email", StringUtils.EMPTY, generatedPassword, MISSING_EMAIL_OR_USERNAME_RESPONSE),
                arguments("пустое поле password", existedUserEmail, StringUtils.EMPTY, MISSING_PASSWORD_RESPONSE)
        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Регистрация пользователя через POST /register с невалидными данными: ")
    @MethodSource("invalidData")
    public void registerNotExistedUser(String caseName, String email, String password, String errorMessage) {
        var response = step("Вызов метода POST /register", () ->
                RegisterApi.register(RegisterRequestDto.builder()
                        .email(email)
                        .password(password)
                        .build())
        );

        AssertApiSteps.checkStatusCodeIs400(response);
        var responseBody = Objects.requireNonNull(response.getBody());

        step("Проверка сообщения об ошибке", () ->
                assertEquals(errorMessage, responseBody.getError(),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }
}
