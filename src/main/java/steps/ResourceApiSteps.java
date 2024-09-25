package steps;

import api.reqres.dtos.basic.ResourceDto;
import api.reqres.services.ResourcesApi;
import utils.RandomValuesUtils;

import java.util.Objects;

public class ResourceApiSteps {
    public static ResourceDto getExistedResource() {
        return RandomValuesUtils
                .getElementFromList(Objects.requireNonNull(ResourcesApi.getResourcesByPageNumber(1).getBody()).getData());
    }
}
