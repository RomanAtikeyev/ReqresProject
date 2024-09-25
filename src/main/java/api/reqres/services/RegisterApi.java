package api.reqres.services;

import api.ApiHelper;
import api.reqres.dtos.request.RegisterRequestDto;
import api.reqres.dtos.response.RegisterResponseDto;
import org.springframework.http.ResponseEntity;

public class RegisterApi extends ReqresBaseApi {
    private final static String REGISTER_URI = BASE_URI + "/register";

    public static ResponseEntity<RegisterResponseDto> register(RegisterRequestDto registerRequestDto) {
        return ApiHelper.post(REGISTER_URI, registerRequestDto, RegisterResponseDto.class);
    }
}
