package com.goldenraspberryawards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
	
    private Integer year;
    private String title;
    private String studios;
    private String producers;
    private String winner;
    
}
