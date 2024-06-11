package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public Resource downloadFile(String origFilename) {
        System.out.println("Searching for file: " + origFilename);
        try {
            Optional<File> fileOptional = fileRepository.findByOrigFilename(origFilename);
            if (fileOptional.isEmpty()) {
                System.out.println("File not found in database: " + origFilename);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found: " + origFilename);
            }
            File file = fileOptional.get();
            System.out.println("File path from DB: " + file.getFilePath());

            Path filePath = Paths.get(file.getFilePath()).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            System.out.println("Resolved file path: " + filePath.toString());
            if (!resource.exists() || !resource.isReadable()) {
                System.out.println("File not readable or does not exist.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not read file: " + origFilename);
            }

            return resource;
        } catch (MalformedURLException ex) {
            System.out.println("MalformedURLException: " + ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found: " + origFilename, ex);
        }
    }

    public String getContentType(Path path) {
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            return "application/octet-stream";
        }
    }
}
