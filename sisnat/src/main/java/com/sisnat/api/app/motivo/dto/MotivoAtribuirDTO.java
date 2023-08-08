package com.sisnat.api.app.motivo.dto;

import com.sisnat.domain.motivo.Motivo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotivoAtribuirDTO {

    private Long codigo;

    public Motivo toModel(){
        return Motivo
                .builder()
                .id(this.codigo)
                .build();
    }

}
