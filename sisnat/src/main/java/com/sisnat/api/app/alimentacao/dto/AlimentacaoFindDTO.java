package com.sisnat.api.app.alimentacao.dto;

import com.sisnat.domain.alimentacao.Alimentacao;
import com.sisnat.domain.animal.Animal;
import com.sisnat.util.pagination.FindAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlimentacaoFindDTO extends FindAbstract<Alimentacao> {

    private Date dataAlimentacao;
    private Animal animal;

    @Override
    protected Alimentacao getProbe() {
        return Alimentacao.builder()
                .dataAlimentacao(dataAlimentacao)
                .animal(animal)
                .build();
    }
}
