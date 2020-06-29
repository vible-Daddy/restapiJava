package com.daeyun.response;

import com.daeyun.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Value;
import reactor.core.publisher.Mono;

@Data
@Value
public class ApiResponseUtils {
    private ApiResponse data = new ApiResponse();
    private Header header = new Header();

    @Data
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static public class Header {
        Integer code = 200;
        String message = "OK";
    }

    public void setException(ApiException e) {
        this.header.setCode(e.getErrorCode().getId());
        this.header.setMessage(e.getMessage());
    }

    public void setException(ApiException e, String message) {
        this.header.setCode(e.getErrorCode().getId());
        this.header.setMessage(message);
    }

    public void put(final String key, final Object value) {
        data.put(key, value);
    }

    public ApiResponse get() {
        data.put("header", header);
        return (ApiResponse) data;
    }

    public Mono<ApiResponse> getMono() {
        data.put("header", header);
        return Mono.just(data);
    }
}
