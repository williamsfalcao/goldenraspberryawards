package com.goldenraspberryawards.controller;

import com.goldenraspberryawards.dto.ResponseDTO;
import com.goldenraspberryawards.model.Movie;
import com.goldenraspberryawards.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class MoviesServiceTest {

    @Mock
    private MovieService serviceMock;

    @Test
    void getMovieTest(){
        Optional<Movie> mv = Mockito.mock(Optional.class);
        when(serviceMock.getMovie(any())).thenReturn(mv);
        Assertions.assertNotEquals(null, serviceMock.getMovie(any()));
    }


    @Test()
    void saveTest(){
        Optional<Movie> mv = Mockito.mock(Optional.class);
        serviceMock.save(mv.get());
        when(serviceMock.getMovie(any())).thenReturn(mv);
        Assertions.assertEquals(serviceMock.getMovie(any()),mv);
    }

    @Test()
    void deleteTest() throws SQLException {
        Optional<Movie> mv = Mockito.mock(Optional.class);
        serviceMock.delete(any());
        when(serviceMock.getMovie(any())).thenReturn(mv);
        Assertions.assertEquals(Optional.empty(), serviceMock.getMovie(any()));
    }

    @Test()
    void updateTest(){
        Optional<Movie> mv = Mockito.mock(Optional.class);
        serviceMock.update(any(), any());
        when(serviceMock.getMovie(any())).thenReturn(mv);
        Assertions.assertEquals(serviceMock.getMovie(any()),mv);
    }

    @Test
    void listarProducers(){
        ResponseDTO response = Mockito.mock(ResponseDTO.class);
        when(serviceMock.listProducers()).thenReturn(response);
        Assertions.assertNotEquals(null,response);
    }
    
    @Test
    void listarStudios(){
        ResponseDTO response = Mockito.mock(ResponseDTO.class);
        when(serviceMock.listStudios()).thenReturn(response);
        Assertions.assertNotEquals(null,response);
    }

}
