package com.goldenraspberryawards.event;

import com.goldenraspberryawards.dto.MovieDTO;
import com.goldenraspberryawards.model.Movie;
import com.goldenraspberryawards.repository.MoviesRepository;
import com.goldenraspberryawards.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.List;

public class EventListner {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListner.class);

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @EventListener(ContextRefreshedEvent.class)
    private void lerCsv() throws IOException {
        LOGGER.info("Lendo Arquivo CSV.");
        saveList(FileUtil.lerCsv());

    }

    private void saveList(List<MovieDTO> list){
        LOGGER.info("Salvando dados na base.");
        list.forEach(item -> moviesRepository.save(convertToEntity(item)));
    }

    public Movie convertToEntity(MovieDTO movieDTO)  {
        return modelMapper.map(movieDTO, Movie.class);
    }

}
