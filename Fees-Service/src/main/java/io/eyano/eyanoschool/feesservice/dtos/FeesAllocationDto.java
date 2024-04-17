package io.eyano.eyanoschool.feesservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * FeesAllocationDto class is used to represent the fees allocation data transfer object
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 */
@Data @ToString @NoArgsConstructor
public class FeesAllocationDto {
    private Long id;

    @NotEmpty(message = "Empty field not allowed")
    @NotBlank(message = "White field not allowed")
    @Size(max = 50, min=5, message = "The length of the field must be between 4 and 50 characters." )
    @NotNull (message = "The field is required")
    private String designation;

    @Max(value = 100, message = "The value must be less than or equal to 100")
    @Min(value = 1, message = "The value must be greater than or equal to 1")
    @NotNull (message = "The field is required")
    private double percentage;

    private SchoolYear schoolYear;

    @NotNull (message = "The field is required")
    private Long idSchoolYear;

    private TypeFeesDto typeFees;

    @NotNull (message = "The field is required")
    @JsonIgnore
    private boolean remove;
}
