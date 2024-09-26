package reqres.api.users;

import api.reqres.services.UsersApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import steps.AssertApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetUserNegativeTests {
    private int notExistedUserId;

    @BeforeAll
    void prepareData() {
        notExistedUserId = RandomValuesUtils.nextInt(100_000_000, 200_000_000);
    }

    @Test
    @DisplayName("Проверка получения несуществующего пользователя через GET /users/<userId>")
    public void checkGetUserWithNotExistedUserId() {
        var response = step("Вызов метода GET /users/<userId>", () ->
                UsersApi.getUserById(notExistedUserId)
        );

        AssertApiSteps.checkStatusCodeIs404(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        step("Проверка отсутствия тела ответа", () ->
                assertAll(
                        () -> assertNull(responseBody.getData(), "Тело запроса не равно null"),
                        () -> assertNull(responseBody.getSupport(), "Поле support не равно null")
                )
        );
    }
}
