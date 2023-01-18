package me.teenageorge.daily.tempfilecsv.usecase;

import me.teenageorge.daily.tempfilecsv.repository.FileRepository;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CsvTempFileUsecase {

    private final FileRepository fileRepository;

    public CsvTempFileUsecase(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * This method is useful when you want to download a file, but you don't want to create a temp file.
     * A use case is when you don't want to create a huge temp file on the server  which rarely restarts, and you wouldn't want to use up the disk space.
     * Also, useful when your server is running as a docker container, and you shouldn't be writing to the disk.
     * @return a ByteArrayResource that can be used to download the file
     */
    public ByteArrayResource createByteArrayResource() {
        var csvString = fileRepository.getCsvString();
        return new ByteArrayResource(csvString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * This method is useful when you want to download a file, but you don't want to create a temp file on the server.
     * Instead, you want to create an in-memory file system and write to the file there.
     * Note that the file system is not persisted, and it will be deleted on invoking the close() method.
     * A use case is when you don't want to create a huge temp file on the server which rarely restarts, and you wouldn't want to use up the disk space.
     * Also, useful when your server is running as a docker container, and you shouldn't be writing to the disk.
     * Note that the {@link FileSystemResource} takes a {@link Path} instead of {@link File} as constructor argument
     * because invocation of ZipPath.toFile() will throw an UnsupportedOperationException.
     * @return FileSystemResource that can be used to download the file
     */
    @SneakyThrows(IOException.class)
    public FileSystemResource createFileSystemResource() {
        var csvString = fileRepository.getCsvString();
        Map<String, Object> env = new HashMap<>();
        env.put("create", "true");

        URI uri = URI.create("jar:file:/tmp/temp-fs");

        try (FileSystem inMemoryFs = FileSystems.newFileSystem(uri, env)) {
            String prefix = "tempfile";
            String suffix = ".csv";
            Path tempFile = Files.createTempFile(inMemoryFs.getPath("/"), prefix, suffix);
            Files.writeString(tempFile, csvString);
            return new FileSystemResource(tempFile);
        }
    }
}
