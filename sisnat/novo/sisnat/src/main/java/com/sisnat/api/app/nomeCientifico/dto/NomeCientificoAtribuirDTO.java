package com.sisnat.api.app.nomeCientifico.dto;

import com.sisnat.domain.nomeCientifico.NomeCientifico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NomeCientificoAtribuirDTO {

    private Long codigo;

    public NomeCientifico toModel(){
        return NomeCientifico
                .builder()
                .id(this.codigo)
                .build();
    }

}
