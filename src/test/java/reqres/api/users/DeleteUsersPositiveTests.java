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

import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteUsersPositiveTests {
    private UserDto existedUser;

    @BeforeAll
    void prepareData() {
        existedUser = UserApiSteps.getExistedUser();
    }

    @Test
    @DisplayName("Проверка вызова метода DELETE /users/<userId>")
    public void checkDeleteUserWithExistedUserId() {
        var response = UsersApi.deleteUser(existedUser.getId());
        AssertApiSteps.checkStatusCode(response, HttpStatus.NO_CONTENT);

        assertNull(response.getBody(), "Тело запроса не равно null");

        var deletedUserResponse = UsersApi.getUserById(existedUser.getId());
        AssertApiSteps.checkStatusCodeIs404(deletedUserResponse);
    }
}
