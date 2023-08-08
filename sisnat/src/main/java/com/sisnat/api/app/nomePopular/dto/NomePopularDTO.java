package com.sisnat.api.app.nomePopular.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.nomeCientifico.NomeCientifico;
import com.sisnat.domain.nomePopular.NomePopular;
import com.sisnat.util.message.Message;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NomePopularDTO {

    private Long id;
    private String nomePopular;
    private NomeCientifico nomeCientifico;

    private Message mensagem;

    public NomePopularDTO(final NomePopular nomePopular){
        this.id = nomePopular.getId();
        this.nomePopular = nomePopular.getNomePopular();
        this.nomeCientifico = nomePopular.getNomeCientifico();
    }

}
