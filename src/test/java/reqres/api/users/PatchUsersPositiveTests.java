package reqres.api.users;

import api.reqres.dtos.basic.UserDto;
import api.reqres.dtos.request.PatchUserRequestDto;
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
public class PatchUsersPositiveTests {
    private UserDto existedUser;
    private PatchUserRequestDto patchUserRequestDto;

    @BeforeAll
    void prepareData() {
        patchUserRequestDto = PatchUserRequestDto.builder()
                .name(RandomValuesUtils.getRandomAlphanumericString(10))
                .job(RandomValuesUtils.getRandomAlphanumericString(10))
                .build();
        existedUser = UserApiSteps.getExistedUser();
    }

    @Test
    @DisplayName("Проверка обновления полей пользователя через PATCH /users/<userId>")
    public void checkPatchUser() {
        var response = step("Вызов метода PATCH /users/<userId>", () ->
                UsersApi.patchUser(patchUserRequestDto, existedUser.getId())
        );

        AssertApiSteps.checkStatusCodeIs200(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        step("Проверка маппинга полей в теле ответа", () ->
                assertAll(
                        () -> assertEquals(patchUserRequestDto.getName(), responseBody.getName(),
                                "Поле name не соответствует ожидаемому"),
                        () -> assertEquals(patchUserRequestDto.getJob(), responseBody.getJob(),
                                "Поле job не соответствует ожидаемому"),
                        () -> assertNotNull(responseBody.getUpdatedAt(), "Поле updatedAt равно null")
                )
        );
    }
}