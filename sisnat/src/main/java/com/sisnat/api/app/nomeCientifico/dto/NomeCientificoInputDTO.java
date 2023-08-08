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
public class NomeCientificoInputDTO {

    private String nomeCientifico;

    public NomeCientifico toModel(){
        return NomeCientifico
                .builder()
                .nomeCientifico(this.nomeCientifico)
                .build();
    }
}
