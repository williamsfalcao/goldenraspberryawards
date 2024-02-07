package com.goldenraspberryawards.controller;

import com.goldenraspberryawards.dto.ResponseDTO;
import com.goldenraspberryawards.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/awards")
public class AwardController {

    public AwardController(MovieService movieService) {
        this.movieService = movieService;
    }

    private MovieService movieService;

    @GetMapping
    ResponseEntity<ResponseDTO> listAward() {

        ResponseDTO response = movieService.listAwards();

        if (Objects.isNull(response)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(movieService.listAwards());
    }
}
