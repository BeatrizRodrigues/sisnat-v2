package com.sisnat.api.app.procedencia.dto;

import com.sisnat.domain.procedencia.Procedencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedenciaInputDTO {

    private String procedencia;

    public Procedencia toModel(){
        return Procedencia
                .builder()
                .procedencia(this.procedencia)
                .build();
    }
}
