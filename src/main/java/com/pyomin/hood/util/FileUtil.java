package com.pyomin.hood.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Component
public class FileUtil {

    @Value("${file.image.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void setupUploadDirectory() {
        File tempFolder = new File(uploadPath);

        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }

        uploadPath = tempFolder.getAbsolutePath();
    }

    public String saveImageFile(MultipartFile file) throws RuntimeException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("파일이 등록되지 않았습니다.");
        }

        String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        Path savePath = Paths.get(uploadPath, savedName);

        try {
            Files.copy(file.getInputStream(), savePath);
            String contentType = file.getContentType();

            if (contentType != null && contentType.startsWith("image")) {
                Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName);
                Thumbnails.of(savePath.toFile())
                        .size(600, 600)
                        .crop(Positions.CENTER)
                        .toFile(thumbnailPath.toFile());
            }

            return savedName;
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }
}
