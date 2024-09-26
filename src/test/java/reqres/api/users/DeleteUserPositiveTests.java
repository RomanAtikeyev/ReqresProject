package reqres.api.users;

import api.reqres.dtos.basic.UserDto;
import api.reqres.services.UsersApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import steps.AssertApiSteps;
import steps.UserApiSteps;

import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteUserPositiveTests {
    private UserDto existedUser;

    @BeforeAll
    void prepareData() {
        existedUser = UserApiSteps.getExistedUser();
    }

    @Test
    @DisplayName("Проверка удаления пользователя через DELETE /users/<userId> для существующего пользователя")
    public void checkDeleteUserWithExistedUserId() {
        var response = step("Вызов метода DELETE /users/<userId>", () ->
                UsersApi.deleteUser(existedUser.getId())
        );

        AssertApiSteps.checkStatusCode(response, HttpStatus.NO_CONTENT);

        step("Проверка маппинга полей в теле ответа", () ->
                assertNull(response.getBody(), "Тело запроса не равно null")
        );

        step("Проверка удаления пользователя из общего списка", () -> {
            var deletedUserResponse = UsersApi.getUserById(existedUser.getId());
            var deletedUserId = Objects.requireNonNull(deletedUserResponse.getBody()).getData().getId();
            assertNull(deletedUserId,
                    String.format("Пользователь c id = %d не был удален из общего списка", deletedUserId));
        });
    }
}
