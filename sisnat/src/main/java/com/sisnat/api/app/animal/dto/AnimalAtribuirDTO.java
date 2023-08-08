package com.sisnat.api.app.animal.dto;

import com.sisnat.domain.animal.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalAtribuirDTO {

    private Long codigo;

    public Animal toModel(){
        return Animal
                .builder()
                .id(this.codigo)
                .build();
    }

}
