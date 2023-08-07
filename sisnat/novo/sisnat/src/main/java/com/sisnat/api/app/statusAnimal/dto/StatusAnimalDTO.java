package com.sisnat.api.app.statusAnimal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.util.message.Message;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusAnimalDTO {

    private Long id;
    private String status;

    private Message mensagem;

    public StatusAnimalDTO(final StatusAnimal statusAnimal){
        this.id = statusAnimal.getId();
        this.status = statusAnimal.getStatus();
    }

}
