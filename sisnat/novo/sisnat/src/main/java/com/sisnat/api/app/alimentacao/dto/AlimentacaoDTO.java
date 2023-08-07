package com.sisnat.api.app.alimentacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sisnat.domain.alimentacao.Alimentacao;
import com.sisnat.domain.animal.Animal;
import com.sisnat.util.message.Message;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlimentacaoDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataAlimentacao;
    private BigDecimal quantidade;
    private String alimento;
    private Animal animal;

    private Message mensagem;

    public AlimentacaoDTO(final Alimentacao alimentacao){
        this.id = alimentacao.getId();
        this.dataAlimentacao = alimentacao.getDataAlimentacao();
        this.quantidade = alimentacao.getQuantidade();
        this.alimento = alimentacao.getAlimento();
        this.animal = alimentacao.getAnimal();
    }

}
