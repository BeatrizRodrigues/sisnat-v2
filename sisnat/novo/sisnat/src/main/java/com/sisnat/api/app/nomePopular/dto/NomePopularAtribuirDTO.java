package com.sisnat.api.app.nomePopular.dto;

import com.sisnat.domain.nomePopular.NomePopular;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NomePopularAtribuirDTO {

    private Long codigo;

    public NomePopular toModel(){
        return NomePopular
                .builder()
                .id(this.codigo)
                .build();
    }

}
