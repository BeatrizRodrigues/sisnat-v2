package com.sisnat.api.app.nomePopular.dto;

import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoAtribuirDTO;
import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.util.pagination.FindAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NomePopularFindDTO extends FindAbstract<NomePopular> {

    private String nomePopular;
    private NomeCientifico nomeCientifico;

    @Override
    protected NomePopular getProbe() {
        return NomePopular.builder()
                .nomePopular(this.nomePopular)
                .nomeCientifico(this.nomeCientifico)
                .build();
    }
}
