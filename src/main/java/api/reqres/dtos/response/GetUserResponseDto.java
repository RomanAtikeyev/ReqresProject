package api.reqres.dtos.response;

import api.reqres.dtos.basic.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponseDto extends BaseGetResponseDto {
    private UserDto data;
}
