package com.sisnat.api.app.ecdise.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.ecdise.Ecdise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EcdiseInputDTO {

    private Date dataEcdise;
    private String ecdise;
    private AnimalAtribuirDTO animal;

    public Ecdise toModel(){
        return Ecdise
                .builder()
                .dataEcdise(this.dataEcdise)
                .animal(this.animal.toModel())
                .ecdise(this.ecdise)
                .build();
    }

}
