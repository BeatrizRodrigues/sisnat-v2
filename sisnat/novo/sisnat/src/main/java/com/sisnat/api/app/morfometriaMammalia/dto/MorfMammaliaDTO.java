package com.sisnat.api.app.morfometriaMammalia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.animal.Animal;
import com.sisnat.domain.morfometriaMammalia.MorfometriaMammalia;
import com.sisnat.util.message.Message;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MorfMammaliaDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataMorf;

    private Double peso;
    private Double cra;
    private Double cc;
    private Double cm;
    private Double cp;
    private Double ho;
    private String observacao;
    private Animal animal;

    private Message mensagem;

    public MorfMammaliaDTO(final MorfometriaMammalia morfometriaMammalia){
        this.id = morfometriaMammalia.getId();
        this.dataMorf = morfometriaMammalia.getDataMorf();
        this.peso = morfometriaMammalia.getPeso();
        this.cra = morfometriaMammalia.getCra();
        this.cc = morfometriaMammalia.getCc();
        this.cm = morfometriaMammalia.getCm();
        this.cp = morfometriaMammalia.getCp();
        this.ho = morfometriaMammalia.getHo();
        this.observacao = morfometriaMammalia.getObservacao();
        this.animal = morfometriaMammalia.getAnimal();
    }

}
