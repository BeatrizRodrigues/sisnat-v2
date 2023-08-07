package com.sisnat.api.app.procedencia.dto;

import com.sisnat.domain.procedencia.Procedencia;
import com.sisnat.util.pagination.FindAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedenciaFindDTO extends FindAbstract<Procedencia> {

    private String procedencia;

    @Override
    protected Procedencia getProbe() {
        return Procedencia.builder()
                .procedencia(this.procedencia)
                .build();
    }
}
