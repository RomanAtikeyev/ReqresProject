package reqres.api.users;

import api.reqres.dtos.basic.UserDto;
import api.reqres.dtos.request.PutUserRequestDto;
import api.reqres.services.UsersApi;
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
public class PutUsersPositiveTests {
    private UserDto existedUser;
    private PutUserRequestDto putUserRequestDto;

    @BeforeAll
    void prepareData() {
        putUserRequestDto = PutUserRequestDto.builder()
                .name(RandomValuesUtils.getRandomAlphanumericString(10))
                .job(RandomValuesUtils.getRandomAlphanumericString(10))
                .build();
        existedUser = UserApiSteps.getExistedUser();
    }

    @Test
    @DisplayName("Проверка обновления информации о пользователе через PUT /users/<userId>")
    public void checkPutUser() {
        var response = step("Вызов метода PUT /users/<userId>", () ->
                UsersApi.putUser(putUserRequestDto, existedUser.getId())
        );

        AssertApiSteps.checkStatusCodeIs200(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        step("Проверка маппинга полей в теле ответа", () ->
                assertAll(
                        () -> assertEquals(putUserRequestDto.getName(), responseBody.getName(),
                                "Поле name не соответствует ожидаемому"),
                        () -> assertEquals(putUserRequestDto.getJob(), responseBody.getJob(),
                                "Поле job не соответствует ожидаемому"),
                        () -> assertNotNull(responseBody.getUpdatedAt(), "Поле updatedAt равно null")
                )
        );
    }
}