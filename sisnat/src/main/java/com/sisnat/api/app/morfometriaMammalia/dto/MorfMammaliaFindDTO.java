package com.sisnat.api.app.morfometriaMammalia.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
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
public class MorfMammaliaFindDTO extends FindAbstract<MorfometriaMammalia> {

    private Date dataMorf;
    private Animal animal;

    @Override
    protected MorfometriaMammalia getProbe() {
        return MorfometriaMammalia.builder()
                .dataMorf(dataMorf)
                .animal(animal)
                .build();
    }
}
