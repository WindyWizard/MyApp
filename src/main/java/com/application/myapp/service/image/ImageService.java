package com.application.myapp.service.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileSystemUtils;
import com.application.myapp.exception.image.ImageNotUploadedException;

@Service
public class ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder! - " + uploadPath);
        }
    }

    public void save(MultipartFile image) throws ImageNotUploadedException {
        try {
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                init();
            }

            String fullPathToImage = uploadPath + "/" + image.getOriginalFilename().toString();

            if (!Files.exists(Paths.get(fullPathToImage))) {
                Files.copy(image.getInputStream(), root.resolve(image.getOriginalFilename()));
            }
        } catch (Exception e) {
            throw new ImageNotUploadedException(String.format(
                "Failed to upload image. Details: %s", e.toString()));
        }
    }
}