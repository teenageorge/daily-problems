package gc.eleg.daily.tempfilecsv.client;

import gc.eleg.daily.tempfilecsv.repository.CsvTempFileRepository;
import gc.eleg.daily.tempfilecsv.repository.FileRepository;
import gc.eleg.daily.tempfilecsv.usecase.CsvTempFileUsecase;

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
