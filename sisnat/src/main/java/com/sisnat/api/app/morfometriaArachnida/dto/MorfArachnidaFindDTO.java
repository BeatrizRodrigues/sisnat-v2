package com.sisnat.api.app.morfometriaArachnida.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.atendimento.Atendimento;
import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import com.sisnat.util.pagination.FindAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorfArachnidaFindDTO extends FindAbstract<MorfometriaArachnida> {

    private Date dataMorf;
    private Animal animal;

    @Override
    protected MorfometriaArachnida getProbe() {
        return MorfometriaArachnida.builder()
                .dataMorf(dataMorf)
                .animal(animal)
                .build();
    }

}
