package com.sisnat.api.app.ecdise.dto;

import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.ecdise.Ecdise;
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
public class EcdiseFindDTO extends FindAbstract<Ecdise> {

    private Date dataEcdise;
    private Animal animal;

    @Override
    protected Ecdise getProbe() {
        return Ecdise.builder()
                .dataEcdise(dataEcdise)
                .animal(animal)
                .build();
    }
}
