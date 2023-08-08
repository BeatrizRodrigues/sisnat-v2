package com.sisnat.api.app.atendimento.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.atendimento.Atendimento;
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
public class AtendimentoFindDTO extends FindAbstract<Atendimento> {

    private Date dataProcedimento;
    private Animal animal;

    @Override
    protected Atendimento getProbe() {
        return Atendimento.builder()
                .dataProcedimento(dataProcedimento)
                .animal(animal)
                .build();
    }

}
