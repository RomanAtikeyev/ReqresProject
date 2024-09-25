package api.reqres.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseGetResponseDto {
    protected Support support;

    @Getter
    @Setter
    public static class Support {
        protected String url;
        protected String text;
    }
}
