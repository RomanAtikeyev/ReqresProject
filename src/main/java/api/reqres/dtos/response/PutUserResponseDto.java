package api.reqres.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutUserResponseDto {
    private String name;
    private String job;
    private String updatedAt;
}