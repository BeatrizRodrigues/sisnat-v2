package com.sisnat.api.app.nomeCientifico.dto;

import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.util.pagination.FindAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NomeCientificoFindDTO extends FindAbstract<NomeCientifico> {

    private String nomeCientifico;

    @Override
    protected NomeCientifico getProbe() {
        return NomeCientifico.builder()
                .nomeCientifico(this.nomeCientifico)
                .build();
    }
}
