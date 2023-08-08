package com.sisnat.api.app.morfometriaReptilia.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
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
public class MorfReptiliaFindDTO extends FindAbstract<MorfometriaReptilia> {

    private Date dataMorf;
    private Animal animal;

    @Override
    protected MorfometriaReptilia getProbe() {
        return MorfometriaReptilia.builder()
                .dataMorf(dataMorf)
                .animal(animal)
                .build();
    }
}
