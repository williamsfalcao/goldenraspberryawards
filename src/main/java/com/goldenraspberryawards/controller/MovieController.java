package com.goldenraspberryawards.controller;

import com.goldenraspberryawards.dto.MovieDTO;
import com.goldenraspberryawards.model.Movie;
import com.goldenraspberryawards.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    public MovieController(MovieService movieService, ModelMapper modelMapper ){
        this.movieService = movieService;
        this.modelMapper = modelMapper;

    }
    private MovieService movieService;

    private ModelMapper modelMapper;

    @PostMapping
    @Operation(
            summary = "Endpoint para cadastrar um filme",
            description = "Endpoint que salva um filma na lista.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Filme cadastrado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno.",
                            content = @Content(mediaType = "application/json")
                    ),
            })
    ResponseEntity<Void> add(@RequestBody
                          @Validated
                          MovieDTO request) {
        movieService.save(convertToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    @Operation(
            summary = "Endpoint para listar todos os filmes",
            description = "Endpoint que retorna uma lista contendo todos os filmes.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista dos filmes cadastrados.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                    responseCode = "204",
                    description = "Sem resultado para a consulta.",
                    content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno.",
                            content = @Content(mediaType = "application/json")
                    ),
            })
    ResponseEntity<List<MovieDTO>> getAll(){
        List<Movie> list = movieService.getListMovies();

        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list.stream().map(this::convertToDto).toList());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint para retornar um filmes pelo seu identificador",
            description = "Endpoint que retorna um filmes.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Filme encontrado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Sem resultado para a consulta.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno.",
                            content = @Content(mediaType = "application/json")
                    ),
            })
    ResponseEntity<MovieDTO> getById( @PathVariable(value = "id")
                           @Parameter( description = "Identificado único do filme", required = true, allowEmptyValue = false)
                           Long id){

        Optional<Movie> movie = movieService.getMovie(id);

        if(movie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(convertToDto(movie.get()));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint para deletar um filme",
            description = "Endpoint que apaga um filme de acordo com o id informado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Filme apagado.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno.",
                            content = @Content(mediaType = "application/json")
                    ),
            })
    ResponseEntity<Void> del(
            @PathVariable(value = "id")
            @Parameter( description = "Identificado único do filme", required = true, allowEmptyValue = false)
            Long id){
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Endpoint para atualizar um filme",
            description = "Endpoint que atualiza um filme de acordo com o id informado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Filme Atualizado.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno.",
                            content = @Content(mediaType = "application/json")
                    ),
            })
    ResponseEntity<Void> put(
            @PathVariable(value = "id")
                          @Parameter( description = "Identificado único do filme", required = true, allowEmptyValue = false)
                          Long id,
                        @RequestBody MovieDTO body){
        movieService.update(id, body);
        return ResponseEntity.ok().build();
    }

    private MovieDTO convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }

    private Movie convertToEntity(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }
}
