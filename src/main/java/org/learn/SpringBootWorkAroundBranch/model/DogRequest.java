package org.learn.SpringBootWorkAroundBranch.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DogRequest {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String dogName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String dogBreed;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private int dogGender;
}
