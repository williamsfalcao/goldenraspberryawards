package com.goldenraspberryawards.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.goldenraspberryawards.dto.MovieDTO;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class FileUtil {

	public static final char SEPARATOR = ';';

	public static List<MovieDTO> lerCsv(String path) throws IOException {

		Reader arquivo = lerArquivo(path);

		CsvToBean<MovieDTO> csvToBean = new CsvToBeanBuilder<MovieDTO>(arquivo).withType(MovieDTO.class)
				.withSeparator(SEPARATOR).build();

		return csvToBean.parse();

	}

	public static Reader lerArquivo(String path) throws IOException {
		return Files.newBufferedReader(Paths.get(path));
	}

    public static List<String[]> readCsvFile(String filePath) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(SEPARATOR).build())
                .build()) {
            return csvReader.readAll();
        }
    }
}
