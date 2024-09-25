package api.reqres.dtos.response;

import api.reqres.dtos.basic.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetUsersResponseDto extends BaseListResponseDto {
    private List<UserDto> data;
}
