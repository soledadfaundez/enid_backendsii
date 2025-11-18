package com.prueba.mantenedor.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Data // Genera getters, setters, toString, equals, hashCode
public class PautaPoc {
    private Integer numPoc;
    private String codTratamiento;
    private String nombrePauta;
    private String areaSolicitante;
    private String actCarga;
    private String infoSpsReg;
    private String observaciones;
}
