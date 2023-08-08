package com.sisnat.api.app.statusAnimal.dto;

import com.sisnat.domain.statusAnimal.StatusAnimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusAnimalInputDTO {

    private String status;

    public StatusAnimal toModel(){
        return StatusAnimal
                .builder()
                .status(this.status)
                .build();
    }
}
