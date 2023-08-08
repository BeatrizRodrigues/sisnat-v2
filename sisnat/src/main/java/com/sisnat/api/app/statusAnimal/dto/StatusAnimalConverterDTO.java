package com.sisnat.api.app.statusAnimal.dto;

import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.util.message.MessageStack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatusAnimalConverterDTO {

    private final MessageStack messageStack;

    public StatusAnimalDTO converter(final StatusAnimal statusAnimal){
        log.debug("c=StatusAnimalConverterDTO, m=converter, statusAnimal={}", statusAnimal);

        final var dto = new StatusAnimalDTO();

        dto.setId(statusAnimal.getId());
        dto.setStatus(statusAnimal.getStatus());

        dto.setMensagem(messageStack.getMensagem());

        return dto;
    }
}
