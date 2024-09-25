package reqres.api.users;

import api.reqres.dtos.basic.UserDto;
import api.reqres.services.UsersApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import steps.AssertApiSteps;
import steps.UserApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetUsersPositiveTests {
    private int pageNumber;
    private UserDto existedUser;

    @BeforeAll
    void prepareData() {
        pageNumber = RandomValuesUtils
                .nextInt(1, Objects.requireNonNull(UsersApi.getUsersByPageNumber(1).getBody()).getTotalPages());
        existedUser = UserApiSteps.getExistedUser();
    }

    @Test
    @DisplayName("Проверка вызова метода GET /users?page=<pageNumber>")
    public void checkGetListUsersByPageNumber() {
        var response = UsersApi.getUsersByPageNumber(pageNumber);

        AssertApiSteps.checkStatusCodeIs200(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        assertAll(
                () -> assertEquals(pageNumber, responseBody.getPage(),
                        "Поле page не соответствует ожидаемому"),
                () -> assertNotNull(responseBody.getPerPage(),
                        "Поле per_page равно null"),
                () -> assertNotNull(responseBody.getTotal(),
                        "Поле total равно null"),
                () -> assertNotNull(responseBody.getTotalPages(),
                        "Поле total_pages равно null"),
                () -> assertNotNull(responseBody.getData(),
                        "Поле data равно null"),
                () -> responseBody.getData().forEach(this::checkUserFields)
        );
    }

    @Test
    @DisplayName("Проверка вызова метода GET /users?id=<userId>")
    public void checkGetUserById() {
        var response = UsersApi.getUserById(existedUser.getId());

        AssertApiSteps.checkStatusCodeIs200(response);

        var user = Objects.requireNonNull(response.getBody()).getData();
        var support = response.getBody().getSupport();
        assertAll(
                () -> assertEquals(existedUser.getId(), user.getId(),
                        "Поле id не соответствует ожидаемому"),
                () -> assertEquals(existedUser.getEmail(), user.getEmail(),
                        "Поле email не соответствует ожидаемому"),
                () -> assertEquals(existedUser.getAvatar(), user.getAvatar(),
                        "Поле avatar не соответствует ожидаемому"),
                () -> assertEquals(existedUser.getFirstName(), user.getFirstName(),
                        "Поле first_name не соответствует ожидаемому"),
                () -> assertEquals(existedUser.getLastName(), user.getLastName(),
                        "Поле last_name не соответствует ожидаемому"),
                () -> assertNotNull(support.getText(),
                        "Поле text равно null"),
                () -> assertNotNull(support.getUrl(),
                        "Поле url равно null")
        );
    }

    private void checkUserFields(UserDto user) {
        assertAll(
                () -> assertNotNull(user.getId(),
                        "Поле id равно null"),
                () -> assertNotNull(user.getEmail(),
                        "Поле email равно null"),
                () -> assertNotNull(user.getAvatar(),
                        "Поле avatar равно null"),
                () -> assertNotNull(user.getFirstName(),
                        "Поле first_name равно null"),
                () -> assertNotNull(user.getLastName(),
                        "Поле last_name равно null")
        );
    }
}
