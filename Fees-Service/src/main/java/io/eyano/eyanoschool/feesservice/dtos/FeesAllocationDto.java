package io.eyano.eyanoschool.feesservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @NoArgsConstructor
public class FeesAllocationDto {
    private Long id;
    private String designation;
    private double percentage;
    private SchoolYear schoolYear;

    private Long idSchoolYear;

    private TypeFeesDto typeFees;

    @JsonIgnore
    private boolean remove;
}
