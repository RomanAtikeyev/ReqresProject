package api.reqres.dtos.response;

import api.reqres.dtos.basic.ResourceDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetResourceResponseDto extends BaseGetResponseDto {
    private ResourceDto data;
}
