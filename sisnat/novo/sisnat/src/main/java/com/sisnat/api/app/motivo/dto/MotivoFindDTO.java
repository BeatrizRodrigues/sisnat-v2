package com.sisnat.api.app.motivo.dto;

import com.sisnat.domain.motivo.Motivo;
import com.sisnat.util.pagination.FindAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotivoFindDTO extends FindAbstract<Motivo> {

    private String motivo;

    @Override
    protected Motivo getProbe() {
        return Motivo.builder()
                .motivo(this.motivo)
                .build();
    }
}
