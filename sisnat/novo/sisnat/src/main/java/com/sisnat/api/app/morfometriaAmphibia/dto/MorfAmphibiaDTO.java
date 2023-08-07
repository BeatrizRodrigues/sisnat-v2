package com.sisnat.api.app.morfometriaAmphibia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaAmphibia.MorfometriaAmphibia;
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
public class MorfAmphibiaDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataMedicao;

    private Double peso;
    private Double crc;
    private Double cc;
    private Double don;
    private Double cf;
    private Double ct;
    private Double cta;
    private Double cp;
    private String observacao;
    private Animal animal;

    private Message mensagem;

    public MorfAmphibiaDTO(final MorfometriaAmphibia morfometriaAmphibia){
        this.id = morfometriaAmphibia.getId();
        this.dataMedicao = morfometriaAmphibia.getDataMedicao();
        this.peso = morfometriaAmphibia.getPeso();
        this.crc = morfometriaAmphibia.getPeso();
        this.cc = morfometriaAmphibia.getPeso();
        this.don = morfometriaAmphibia.getPeso();
        this.cf = morfometriaAmphibia.getPeso();
        this.ct = morfometriaAmphibia.getPeso();
        this.cta = morfometriaAmphibia.getPeso();
        this.cp = morfometriaAmphibia.getPeso();
        this.observacao = morfometriaAmphibia.getObservacao();
        this.animal = morfometriaAmphibia.getAnimal();
    }

}
