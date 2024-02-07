package com.goldenraspberryawards.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    @Valid
    @NotNull(message = "CAMPO_ERROR_SCHEMA")
    private Integer year;
    @Valid
    @NotNull(message = "CAMPO_ERROR_SCHEMA")
    private String title;
    @Valid
    @NotNull(message = "CAMPO_ERROR_SCHEMA")
    private String studios;
    @Valid
    @NotNull(message = "CAMPO_ERROR_SCHEMA")
    private String producers;
    @Valid
    @NotNull(message = "CAMPO_ERROR_SCHEMA")
    private String winner;
}
