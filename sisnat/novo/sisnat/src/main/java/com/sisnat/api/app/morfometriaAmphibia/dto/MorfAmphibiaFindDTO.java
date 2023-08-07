package com.sisnat.api.app.morfometriaAmphibia.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
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
public class MorfAmphibiaFindDTO extends FindAbstract<MorfometriaAmphibia> {

    private Date dataMedicao;
    private Animal animal;

    @Override
    protected MorfometriaAmphibia getProbe() {
        return MorfometriaAmphibia.builder()
                .dataMedicao(dataMedicao)
                .animal(animal)
                .build();
    }
}
