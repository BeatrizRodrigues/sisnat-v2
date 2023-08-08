package com.sisnat.api.app.morfometriaAve.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaAves.MorfometriaAves;
import com.sisnat.util.message.Message;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MorfAveDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataMorf;

    private Double peso;
    private Double cb;
    private Double h;
    private Double cc;
    private Double ca;
    private Double ct;
    private String observacao;
    private Animal animal;

    private Message mensagem;

    public MorfAveDTO(final MorfometriaAves morfometriaAves){
        this.id = morfometriaAves.getId();
        this.dataMorf = morfometriaAves.getDataMorf();
        this.peso = morfometriaAves.getPeso();
        this.cb = morfometriaAves.getCb();
        this.h = morfometriaAves.getH();
        this.cc = morfometriaAves.getCc();
        this.ca = morfometriaAves.getCa();
        this.ct = morfometriaAves.getCt();
        this.observacao = morfometriaAves.getObservacao();
        this.animal = morfometriaAves.getAnimal();
    }

}
