package com.goldenraspberryawards.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {

    private List<AwardDTO> min;
    private List<AwardDTO> max;
}
