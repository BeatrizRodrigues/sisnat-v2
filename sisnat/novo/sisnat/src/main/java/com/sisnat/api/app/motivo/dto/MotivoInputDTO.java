package com.sisnat.api.app.motivo.dto;

import com.sisnat.domain.motivo.Motivo;
import com.sisnat.domain.statusAnimal.StatusAnimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotivoInputDTO {

    private String motivo;

    public Motivo toModel(){
        return Motivo
                .builder()
                .motivo(this.motivo)
                .build();
    }
}
