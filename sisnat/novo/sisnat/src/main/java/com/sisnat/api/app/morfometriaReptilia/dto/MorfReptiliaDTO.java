package com.sisnat.api.app.morfometriaReptilia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaReptilia.MorfometriaReptilia;
import com.sisnat.util.message.Message;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MorfReptiliaDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataMorf;

    private Double peso;
    private Double crc;
    private String observacao;
    private Animal animal;

    private Message mensagem;

    public MorfReptiliaDTO(final MorfometriaReptilia morfometriaReptilia){
        this.id = morfometriaReptilia.getId();
        this.dataMorf = morfometriaReptilia.getDataMorf();
        this.peso = morfometriaReptilia.getPeso();
        this.crc = morfometriaReptilia.getCrc();
        this.observacao = morfometriaReptilia.getObservacao();
        this.animal = morfometriaReptilia.getAnimal();
    }

}
