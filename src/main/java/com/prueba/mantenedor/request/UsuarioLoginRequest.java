package com.prueba.mantenedor.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Data // Genera getters, setters, toString, equals, hashCode
public class UsuarioLoginRequest {

    @NotNull(message = "{usuario.email.required}")
    @NotBlank(message = "{usuario.email.not.blank}")
    private String email;

    @NotNull(message = "{usuario.password.required}")
    @NotBlank(message = "{usuario.password.not.blank}")
    private String password;

}
