package com.sisnat.api.app.ecdise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.ecdise.Ecdise;
import com.sisnat.util.message.Message;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcdiseDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataEcdise;

    private String ecdise;
    private Animal animal;

    private Message mensagem;

    public EcdiseDTO(final Ecdise ecdise){
        this.id = ecdise.getId();
        this.dataEcdise = ecdise.getDataEcdise();
        this.ecdise = ecdise.getEcdise();
        this.animal = ecdise.getAnimal();
    }

}
