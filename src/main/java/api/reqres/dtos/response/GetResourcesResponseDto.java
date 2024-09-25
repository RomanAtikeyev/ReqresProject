package api.reqres.dtos.response;

import api.reqres.dtos.basic.ResourceDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetResourcesResponseDto extends BaseListResponseDto {
    private List<ResourceDto> data;
}
