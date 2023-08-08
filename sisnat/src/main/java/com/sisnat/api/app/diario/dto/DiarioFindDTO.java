package com.sisnat.api.app.diario.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.diario.Diario;
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
public class DiarioFindDTO extends FindAbstract<Diario> {

    private Date dataDiario;
    private Animal animal;

    @Override
    protected Diario getProbe() {
        return Diario.builder()
                .dataDiario(dataDiario)
                .animal(animal)
                .build();
    }
}
