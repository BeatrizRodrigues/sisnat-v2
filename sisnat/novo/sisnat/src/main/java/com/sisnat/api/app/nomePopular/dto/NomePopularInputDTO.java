package com.sisnat.api.app.nomePopular.dto;

import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoAtribuirDTO;
import com.sisnat.domain.nomePopular.NomePopular;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NomePopularInputDTO {

    private String nomePopular;
    private NomeCientificoAtribuirDTO nomeCientifico;

    public NomePopular toModel(){
        return NomePopular
                .builder()
                .nomePopular(this.nomePopular)
                .nomeCientifico(this.nomeCientifico.toModel())
                .build();
    }
}
