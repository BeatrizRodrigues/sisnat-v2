package com.sisnat.api.app.morfometriaAve.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaAves.MorfometriaAves;
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
public class MorfAveFindDTO extends FindAbstract<MorfometriaAves> {

    private Date dataMorf;
    private Animal animal;

    @Override
    protected MorfometriaAves getProbe() {
        return MorfometriaAves.builder()
                .dataMorf(dataMorf)
                .animal(animal)
                .build();
    }

}
