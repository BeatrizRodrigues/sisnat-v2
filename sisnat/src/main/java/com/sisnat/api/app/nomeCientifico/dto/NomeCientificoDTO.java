package com.sisnat.api.app.nomeCientifico.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.util.message.Message;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NomeCientificoDTO {

    private Long id;
    private String nomeCientifico;

    private Message mensagem;

    public NomeCientificoDTO(final NomeCientifico nomeCientifico){
        this.id = nomeCientifico.getId();
        this.nomeCientifico = nomeCientifico.getNomeCientifico();
    }

}
