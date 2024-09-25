package api.reqres.dtos.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDto {
    @NonNull
    private Integer id;
    @NonNull
    private String token;
    private String error;
}
