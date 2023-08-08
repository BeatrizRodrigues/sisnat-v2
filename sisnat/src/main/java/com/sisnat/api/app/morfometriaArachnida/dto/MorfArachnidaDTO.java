package com.sisnat.api.app.morfometriaArachnida.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaArachnida.MorfometriaArachnida;
import com.sisnat.util.message.Message;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MorfArachnidaDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataMorf;

    private Double peso;
    private Double cc;
    private Double cp;
    private Double cpp;
    private String observacao;
    private Animal animal;

    private Message mensagem;

    public MorfArachnidaDTO(final MorfometriaArachnida morfometriaArachnida){
        this.id = morfometriaArachnida.getId();
        this.dataMorf = morfometriaArachnida.getDataMorf();
        this.peso = morfometriaArachnida.getPeso();
        this.cc = morfometriaArachnida.getCc();
        this.cp = morfometriaArachnida.getCp();
        this.cpp = morfometriaArachnida.getCpp();
        this.observacao = morfometriaArachnida.getObservacao();
        this.animal = morfometriaArachnida.getAnimal();
    }

}
