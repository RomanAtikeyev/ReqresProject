package steps;

import api.reqres.dtos.basic.UserDto;
import api.reqres.services.UsersApi;
import io.qameta.allure.Step;
import utils.RandomValuesUtils;

import java.util.Objects;

public class UserApiSteps {
    @Step("Получение существуюшего пользователя")
    public static UserDto getExistedUser() {
        return RandomValuesUtils
                .getElementFromList(Objects.requireNonNull(UsersApi.getUsersByPageNumber(1).getBody()).getData());
    }
}
