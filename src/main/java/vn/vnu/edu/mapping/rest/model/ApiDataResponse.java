package vn.vnu.edu.mapping.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiDataResponse<T> {
    private T data;
    private int status;
    private String message;

    public ApiDataResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ApiDataResponse ok(Object data) {
        return new ApiDataResponse(data, HttpStatus.OK.value(), "successful");
    }

    public static ApiDataResponse error() {
        return new ApiDataResponse(null, HttpStatus.BAD_REQUEST.value(), "Something errors");
    }

    public static ApiDataResponse error(int statusCode, String message) {
        return new ApiDataResponse(null, statusCode, message);
    }

    public static ApiDataResponse error(Object data, int statusCode) {
        return new ApiDataResponse(data, statusCode, "");
    }

    public static ApiDataResponse error(Object data, int statusCode, String message) {
        return new ApiDataResponse(data, statusCode, message);
    }
}
