package api;

import api.config.ErrorHandler;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ApiHelper {
    private static final RestTemplate REST_TEMPLATE;
    private static final HttpHeaders HTTP_HEADERS;

    static {
        REST_TEMPLATE = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        REST_TEMPLATE.setMessageConverters(messageConverters);
        REST_TEMPLATE.setErrorHandler(new ErrorHandler());

        HTTP_HEADERS = new HttpHeaders();
        HTTP_HEADERS.setContentType(MediaType.APPLICATION_JSON);
    }

    public static <T> ResponseEntity<T> get(String url, Class<T> responseType, Map<String, ?> uriParams) {
        return REST_TEMPLATE.getForEntity(url, responseType, uriParams);
    }

    public static <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        return REST_TEMPLATE.getForEntity(url, responseType);
    }

    public static <T, R> ResponseEntity<T> post(String url, R body, Class<T> responseType) {
        return REST_TEMPLATE.exchange(url, HttpMethod.POST, new HttpEntity<>(body, HTTP_HEADERS), responseType);
    }

    public static <T, R> ResponseEntity<T> patch(String url, R body, Class<T> responseType) {
        return REST_TEMPLATE.exchange(url, HttpMethod.PATCH, new HttpEntity<>(body, HTTP_HEADERS), responseType);
    }

    public static <T, R> ResponseEntity<T> put(String url, R body, Class<T> responseType) {
        return REST_TEMPLATE.exchange(url, HttpMethod.PUT, new HttpEntity<>(body, HTTP_HEADERS), responseType);
    }

    public static <T> ResponseEntity<T> delete(String url, Class<T> responseType) {
        return REST_TEMPLATE.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, responseType);
    }
}
