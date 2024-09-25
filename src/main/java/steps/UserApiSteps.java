package steps;

import api.reqres.dtos.basic.UserDto;
import api.reqres.services.UsersApi;
import utils.RandomValuesUtils;

import java.util.Objects;

public class UserApiSteps {
    public static UserDto getExistedUser() {
        return RandomValuesUtils
                .getElementFromList(Objects.requireNonNull(UsersApi.getUsersByPageNumber(1).getBody()).getData());
    }
}
