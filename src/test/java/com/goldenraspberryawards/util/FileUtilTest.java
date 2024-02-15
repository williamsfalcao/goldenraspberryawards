package com.goldenraspberryawards.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.opencsv.exceptions.CsvException;

class FileUtilTest {

	private static final String PATH_SUCCESS = "movielist.csv";
	private static final String PATH_SUCCESS_01 = "sucesso01.csv";
	private static final String PATH_SUCCESS_02 = "sucesso02.csv";
	private static final String PATH_ERRO = "erro.csv";
	private static final String PATH_ERRO_CONVERSION = "movielist_tst.csv";
	private static final String PATH_NOT_FOUND = "invalid.csv";
	private static final String PATH_NOT_CONTENT = "notContent.csv";
	private static final String PATH_NOT_FORMAT = "notFormat.csv";
	

	@Test
	void lerCsvTest() {
		assertDoesNotThrow(() -> FileUtil.lerCsv(PATH_SUCCESS));
	}

	@Test
	void lerCsvErrorConversionTest() {
		assertThrows(RuntimeException.class, () -> FileUtil.lerCsv(PATH_ERRO_CONVERSION));
	}
	
	@Test
	void testReadCsvFileSucesso() throws IOException, CsvException {
		List<String[]> records = FileUtil.readCsvFile(PATH_SUCCESS_01);

		assertEquals(2, records.size());
		assertEquals("year", records.get(0)[0]);
		assertEquals("title", records.get(0)[1]);
		assertEquals("studios", records.get(0)[2]);
		assertEquals("producers", records.get(0)[3]);
		assertEquals("winner", records.get(0)[4]);

		assertEquals(1980, Integer.parseInt(records.get(1)[0]));
		assertEquals("Can't Stop the Music", records.get(1)[1]);
		assertEquals("Associated Film Distribution", records.get(1)[2]);
		assertEquals("Allan Carr", records.get(1)[3]);
		assertEquals("yes", records.get(1)[4]);
	}
	
	@Test
	void testReadCsvFileSucesso2() throws IOException, CsvException {
		List<String[]> records = FileUtil.readCsvFile(PATH_SUCCESS_02);

		assertEquals(2, records.size());
		assertEquals("year", records.get(0)[0]);
		assertEquals("title", records.get(0)[1]);
		assertEquals("studios", records.get(0)[2]);
		assertEquals("producers", records.get(0)[3]);
		assertEquals("winner", records.get(0)[4]);

		assertEquals(1985, Integer.parseInt(records.get(1)[0]));
		assertEquals("Can't Stop the Music", records.get(1)[1]);
		assertEquals("Associated Film Distribution", records.get(1)[2]);
		assertEquals("Allan Carr", records.get(1)[3]);
		assertEquals("", records.get(1)[4]);
	}
	
	@Test
	void testReadCsvFileErro() throws IOException, CsvException {
		List<String[]> records = FileUtil.readCsvFile(PATH_ERRO);
		assertNotEquals("year", records.get(0)[0]);
	}

	@Test
	void testReadCsvFileWithInvalidFilePath() {
		assertThrows(IOException.class, () -> FileUtil.readCsvFile(PATH_NOT_FOUND));
	}
	
	@Test
	void testReadCsvFileNotContent() throws IOException, CsvException {
		assertThrows(RuntimeException.class, () -> FileUtil.readCsvFile(PATH_NOT_CONTENT));
	}
	
	@Test
	void testReadCsvFileNotFormat() throws IOException, CsvException {
		assertThrows(RuntimeException.class, () -> FileUtil.readCsvFile(PATH_NOT_FORMAT));
	}
}
