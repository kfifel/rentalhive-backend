package com.rentalhive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EdocumentDto {
    @NotNull(message = "Model name cannot be null")
    @NotBlank(message = "Model name cannot be blank")
    private String modelName;
    @NotNull(message = "Model reference cannot be null")
    private Long modelId;
    @NotNull(message = "Classpath cannot be null")
    @NotBlank(message = "Classpath cannot be blank")
    private String classpath;
}
