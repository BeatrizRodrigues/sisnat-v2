package com.sisnat.api.app.alimentacao.dto;

import com.sisnat.api.app.animal.dto.AnimalAtribuirDTO;
import com.sisnat.domain.alimentacao.Alimentacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlimentacaoInputDTO {

    private Date dataAlimentacao;
    private BigDecimal quantidade;
    private String alimento;
    private AnimalAtribuirDTO animal;

    public Alimentacao toModel(){
        return Alimentacao
                .builder()
                .dataAlimentacao(this.dataAlimentacao)
                .quantidade(this.quantidade)
                .animal(this.animal.toModel())
                .alimento(this.alimento)
                .build();
    }
}
