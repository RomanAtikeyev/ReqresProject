package reqres.api.resources;

import api.reqres.services.ResourcesApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import steps.AssertApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetResourcesNegativeTests {
    private int notExistedResourceId;

    @BeforeAll
    void prepareData() {
        notExistedResourceId = RandomValuesUtils.nextInt(100_000_000, 200_000_000);
    }

    @Test
    @DisplayName("Проверка вызова метода GET /unknown/<resourceId> для несуществующего ресурса")
    public void checkGetResourceWithNotExistedResourceId() {
        var response = ResourcesApi.getResourceById(notExistedResourceId);

        AssertApiSteps.checkStatusCodeIs404(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        assertAll(
                () -> assertNull(responseBody.getData(), "Тело запроса не равно null"),
                () -> assertNull(responseBody.getSupport(), "Поле support не равно null")
        );
    }
}
