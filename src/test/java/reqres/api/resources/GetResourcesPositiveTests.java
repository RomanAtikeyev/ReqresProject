package reqres.api.resources;

import api.reqres.dtos.basic.ResourceDto;
import api.reqres.services.ResourcesApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import steps.AssertApiSteps;
import steps.ResourceApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetResourcesPositiveTests {
    private int pageNumber;
    private ResourceDto existedResource;

    @BeforeAll
    void prepareData() {
        pageNumber = RandomValuesUtils
                .nextInt(1, Objects.requireNonNull(ResourcesApi.getResourcesByPageNumber(1).getBody()).getTotalPages());
        existedResource = ResourceApiSteps.getExistedResource();
    }

    @Test
    @DisplayName("Проверка вызова метода GET /unknown?page=<pageNumber>")
    public void checkGetListResourcesByPageNumber() {
        var response = ResourcesApi.getResourcesByPageNumber(pageNumber);

        AssertApiSteps.checkStatusCodeIs200(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        assertAll(
                () -> assertEquals(pageNumber, responseBody.getPage(),
                        "Поле page не соответствует ожидаемому"),
                () -> assertNotNull(responseBody.getPerPage(),
                        "Поле per_page равно null"),
                () -> assertNotNull(responseBody.getTotal(),
                        "Поле total равно null"),
                () -> assertNotNull(responseBody.getTotalPages(),
                        "Поле total_pages равно null"),
                () -> assertNotNull(responseBody.getData(),
                        "Поле data равно null"),
                () -> responseBody.getData().forEach(this::checkResourceFields)
        );
    }

    @Test
    @DisplayName("Проверка вызова метода GET /unknown?id=<resourceId>")
    public void checkGetResourceById() {
        var response = ResourcesApi.getResourceById(existedResource.getId());

        AssertApiSteps.checkStatusCodeIs200(response);

        var resource = Objects.requireNonNull(response.getBody()).getData();
        var support = response.getBody().getSupport();
        assertAll(
                () -> assertEquals(existedResource.getId(), resource.getId(),
                        "Поле id не соответствует ожидаемому"),
                () -> assertEquals(existedResource.getName(), resource.getName(),
                        "Поле name не соответствует ожидаемому"),
                () -> assertEquals(existedResource.getYear(), resource.getYear(),
                        "Поле year не соответствует ожидаемому"),
                () -> assertEquals(existedResource.getColor(), resource.getColor(),
                        "Поле color не соответствует ожидаемому"),
                () -> assertEquals(existedResource.getPantoneValue(), resource.getPantoneValue(),
                        "Поле pantone_value не соответствует ожидаемому"),
                () -> assertNotNull(support.getText(),
                        "Поле text равно null"),
                () -> assertNotNull(support.getUrl(),
                        "Поле url равно null")
        );
    }

    private void checkResourceFields(ResourceDto resource) {
        assertAll(
                () -> assertNotNull(resource.getId(),
                        "Поле id равно null"),
                () -> assertNotNull(resource.getName(),
                        "Поле name равно null"),
                () -> assertNotNull(resource.getYear(),
                        "Поле year равно null"),
                () -> assertNotNull(resource.getColor(),
                        "Поле color равно null"),
                () -> assertNotNull(resource.getPantoneValue(),
                        "Поле pantone_value равно null")
        );
    }
}
