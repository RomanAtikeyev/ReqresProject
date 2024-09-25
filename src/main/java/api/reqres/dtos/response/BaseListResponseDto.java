package api.reqres.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseListResponseDto extends BaseGetResponseDto {
    protected Integer page;
    @JsonProperty("per_page")
    protected Integer perPage;
    @JsonProperty("total")
    protected Integer total;
    @JsonProperty("total_pages")
    protected Integer totalPages;
}
