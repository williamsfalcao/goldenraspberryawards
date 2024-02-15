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
@RequestMapping(value = "/producers")
public class ProducerController {

    public ProducerController(MovieService movieService) {
        this.movieService = movieService;
    }

    private MovieService movieService;

    @GetMapping
    ResponseEntity<ResponseDTO> listAward() {

        ResponseDTO response = movieService.listProducers();

        if (Objects.isNull(response)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);
    }
}
