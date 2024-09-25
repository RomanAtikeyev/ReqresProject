package reqres.api.users;

import api.reqres.dtos.basic.UserDto;
import api.reqres.dtos.request.PutPatchUserRequestDto;
import api.reqres.services.UsersApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import steps.AssertApiSteps;
import steps.UserApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PutUsersPositiveTests {
    private UserDto existedUser;
    private PutPatchUserRequestDto putUserRequestDto;

    @BeforeAll
    void prepareData() {
        putUserRequestDto = PutPatchUserRequestDto.builder()
                .name(RandomValuesUtils.getRandomAlphanumericString(10))
                .job(RandomValuesUtils.getRandomAlphanumericString(10))
                .build();
        existedUser = UserApiSteps.getExistedUser();
    }

    @Test
    @DisplayName("Проверка вызова метода PUT /users/<userId>")
    public void checkPutUser() {
        var response = UsersApi.putUser(putUserRequestDto, existedUser.getId());

        AssertApiSteps.checkStatusCodeIs200(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        assertAll(
                () -> assertEquals(putUserRequestDto.getName(), responseBody.getName(),
                        "Поле name не соответствует ожидаемому"),
                () -> assertEquals(putUserRequestDto.getJob(), responseBody.getJob(),
                        "Поле job не соответствует ожидаемому"),
                () -> assertNotNull(responseBody.getUpdatedAt(), "Поле updatedAt равно null")
        );
    }
}