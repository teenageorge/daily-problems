package me.teenageorge.daily.tempfilecsv.client;

import me.teenageorge.daily.tempfilecsv.repository.CsvTempFileRepository;
import me.teenageorge.daily.tempfilecsv.repository.FileRepository;
import me.teenageorge.daily.tempfilecsv.usecase.CsvTempFileUsecase;

public class TempFileWithoutFile {
    private final FileRepository fileRepository = new CsvTempFileRepository();
    private final CsvTempFileUsecase csvFileUseCase = new CsvTempFileUsecase(fileRepository);

    /**
     * Demonstrates how to return a resource without creating a temp file on the server.
     * e.g. ResponseEntity<{org.springframework.core.io.ByteArrayResource}>,
     * ResponseEntity<{org.springframework.core.io.FileSystemResource}>
     */
    public static void main(String[] args) {
        TempFileWithoutFile main = new TempFileWithoutFile();
        main.csvFileUseCase.createByteArrayResource();
        main.csvFileUseCase.createFileSystemResource();
    }
}
