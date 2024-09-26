package steps;

import api.reqres.dtos.basic.ResourceDto;
import api.reqres.services.ResourcesApi;
import io.qameta.allure.Step;
import utils.RandomValuesUtils;

import java.util.Objects;

public class ResourceApiSteps {
    @Step("Получение существуюшего ресурса")
    public static ResourceDto getExistedResource() {
        return RandomValuesUtils
                .getElementFromList(Objects.requireNonNull(ResourcesApi.getResourcesByPageNumber(1).getBody()).getData());
    }
}
