package com.prueba.mantenedor.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Constructor vacío
@Data // Genera getters, setters, toString, equals, hashCode
public class ApiResponse<T> {

    // SFC: Omitir este campo si es null
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensaje;

    // SFC: Omitir este campo si es null
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private Integer statusCode;

    private boolean indError;

    // Constructor para casos de error
    public ApiResponse(String mensaje, int statusCode, boolean indError) {
        this.mensaje = mensaje;
        this.data = null; // No se incluye el campo data en los errores
        this.statusCode = statusCode;
        this.indError = indError;
    }

    // Constructor para éxito
    public ApiResponse(T data, Integer statusCode, String mensaje, boolean indError) {
        this.data = data;
        this.mensaje = mensaje;
        this.statusCode = statusCode;
        this.indError = indError;
    }

}
