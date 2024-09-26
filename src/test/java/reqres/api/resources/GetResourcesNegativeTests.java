package reqres.api.resources;

import api.reqres.services.ResourcesApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import steps.AssertApiSteps;
import utils.RandomValuesUtils;

import java.util.Objects;

import static io.qameta.allure.Allure.step;
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
    @DisplayName("Проверка получения несуществующего ресурса через GET /unknown/<resourceId>")
    public void checkGetResourceWithNotExistedResourceId() {
        var response = step("Вызов метода GET /unknown/<resourceId>", () ->
                ResourcesApi.getResourceById(notExistedResourceId)
        );

        AssertApiSteps.checkStatusCodeIs404(response);

        var responseBody = Objects.requireNonNull(response.getBody());
        step("Проверка маппинга полей в теле ответа", () ->
                assertAll(
                        () -> assertNull(responseBody.getData(), "Тело запроса не равно null"),
                        () -> assertNull(responseBody.getSupport(), "Поле support не равно null")
                )
        );
    }
}
