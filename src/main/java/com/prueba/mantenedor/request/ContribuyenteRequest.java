package com.prueba.mantenedor.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Data // Genera getters, setters, toString, equals, hashCode
public class ContribuyenteRequest {

    @NotNull(message = "{usuario.rut.required}")
    @NotBlank(message = "{usuario.rut.not.blank}")
    private String rut;

    @NotNull(message = "{usuario.nombre.required}")
    @NotBlank(message = "{usuario.nombre.not.blank}")
    private String nombre;

    @NotNull(message = "{usuario.email.required}")
    @NotBlank(message = "{usuario.email.not.blank}")
    private String email;

}