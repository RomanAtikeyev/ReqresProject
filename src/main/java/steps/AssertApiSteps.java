package steps;

import io.qameta.allure.Step;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertApiSteps {
    public static <T> void checkStatusCodeIs200(ResponseEntity<T> response) {
        checkStatusCode(response, HttpStatus.OK);
    }

    public static <T> void checkStatusCodeIs400(ResponseEntity<T> response) {
        checkStatusCode(response, HttpStatus.BAD_REQUEST);
    }

    public static <T> void checkStatusCodeIs404(ResponseEntity<T> response) {
        checkStatusCode(response, HttpStatus.NOT_FOUND);
    }

    @Step("Проверка полученного статус кода ответа")
    public static <T> void checkStatusCode(ResponseEntity<T> response, HttpStatus expectedStatus) {
        assertEquals(expectedStatus.value(), response.getStatusCode().value(),
                "Статус код ответа не соответствует ожидаемому");
    }
}
