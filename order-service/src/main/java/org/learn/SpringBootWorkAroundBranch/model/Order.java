package org.learn.SpringBootWorkAroundBranch.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @NotNull
    @NotEmpty
    private List<String> products;

    @NotNull
    @NotEmpty
    @NotBlank
    private String username;

    @NotNull
    private int payableAmount;
}
