package com.goldenraspberryawards.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardDTO {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
