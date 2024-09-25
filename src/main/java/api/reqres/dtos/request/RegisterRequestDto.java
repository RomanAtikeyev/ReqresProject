package api.reqres.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    private String email;
    private String password;
}
