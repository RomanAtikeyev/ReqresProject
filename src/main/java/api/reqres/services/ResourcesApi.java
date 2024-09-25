package api.reqres.services;

import api.ApiHelper;
import api.reqres.dtos.response.GetResourceResponseDto;
import api.reqres.dtos.response.GetResourcesResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResourcesApi extends ReqresBaseApi {
    private final static String RESOURCES_URI = BASE_URI + "/unknown";

    public static ResponseEntity<GetResourcesResponseDto> getResourcesByPageNumber(int pageNumber) {
        return ApiHelper.get(RESOURCES_URI, GetResourcesResponseDto.class, Map.of(PAGE_PARAM, pageNumber));
    }

    public static ResponseEntity<GetResourceResponseDto> getResourceById(int id) {
        return ApiHelper.get(RESOURCES_URI + "/" + id, GetResourceResponseDto.class);
    }
}
