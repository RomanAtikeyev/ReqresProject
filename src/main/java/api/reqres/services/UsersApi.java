package api.reqres.services;

import api.ApiHelper;
import api.reqres.dtos.request.PutPatchUserRequestDto;
import api.reqres.dtos.response.GetUserResponseDto;
import api.reqres.dtos.response.GetUsersResponseDto;
import api.reqres.dtos.response.PutPatchUserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class UsersApi extends ReqresBaseApi {
    private final static String USERS_URI = BASE_URI + "/users";

    public static ResponseEntity<GetUsersResponseDto> getUsersByPageNumber(int pageNumber) {
        return ApiHelper.get(USERS_URI, GetUsersResponseDto.class, Map.of(PAGE_PARAM, pageNumber));
    }

    public static ResponseEntity<GetUserResponseDto> getUserById(int id) {
        return ApiHelper.get(USERS_URI + "/" + id, GetUserResponseDto.class);
    }

    public static ResponseEntity<PutPatchUserResponseDto> patchUser(PutPatchUserRequestDto registerRequestDto, int id) {
        return ApiHelper.patch(USERS_URI + "/" + id, registerRequestDto, PutPatchUserResponseDto.class);
    }

    public static ResponseEntity<PutPatchUserResponseDto> putUser(PutPatchUserRequestDto registerRequestDto, int id) {
        return ApiHelper.put(USERS_URI + "/" + id, registerRequestDto, PutPatchUserResponseDto.class);
    }

    public static ResponseEntity<String> deleteUser(int id) {
        return ApiHelper.delete(USERS_URI + "/" + id, String.class);
    }
}
