package com.goldenraspberryawards.util;

import com.goldenraspberryawards.dto.MovieDTO;
import com.goldenraspberryawards.repository.MoviesRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    @Autowired
    private MoviesRepository moviesRepository;

    public static final char SEPARATOR = ';';

    public static List<MovieDTO> lerCsv() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get("movielist.csv"));

        CsvToBean<MovieDTO> csvToBean = new CsvToBeanBuilder<MovieDTO>(reader)
                .withType(MovieDTO.class)
                .withSeparator(SEPARATOR)
                .build();

        return csvToBean.parse();

    }

}
