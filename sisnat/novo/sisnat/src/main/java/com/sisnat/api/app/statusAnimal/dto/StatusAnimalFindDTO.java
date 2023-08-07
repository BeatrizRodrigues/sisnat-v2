package com.sisnat.api.app.statusAnimal.dto;

import com.sisnat.domain.statusAnimal.StatusAnimal;
import com.sisnat.util.pagination.FindAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusAnimalFindDTO extends FindAbstract<StatusAnimal> {

    private String status;

    @Override
    protected StatusAnimal getProbe() {
        return StatusAnimal.builder()
                .status(this.status)
                .build();
    }
}
