package com.project_merge.jigu_travel.api.image.controller;

import com.project_merge.jigu_travel.api.image.dto.ImageResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    private static final String FASTAPI_URL = "http://127.0.0.1:8000/ImageSearch/";
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @PostMapping("/ImageSearch")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        File tempFile = null;
        try {
            // 1. MultipartFile -> File 변환
            tempFile = convertToFile(file);
            logger.info("File converted: {}", tempFile.getName());

            // 2. FastAPI 호출
            WebClient webClient = WebClient.builder()
                    .baseUrl(FASTAPI_URL)
                    .build();

            ImageResponseDto response = webClient.post()
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData("file", new FileSystemResource(tempFile)))
                    .retrieve()
                    .bodyToMono(ImageResponseDto.class)
                    .block();

            logger.info("FastAPI response received successfully.");
            // 3. 응답 반환
            return ResponseEntity.ok(response);

        } catch (WebClientResponseException e) {
            logger.error("FastAPI returned an error", e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (IOException e) {
            logger.error("File processing error", e);
            return ResponseEntity.status(500).body("File processing error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        } finally {
            // 4. 임시 파일 삭제
            if (tempFile != null && tempFile.exists()) {
                boolean deleted = tempFile.delete();
                logger.info("Temporary file deleted: {}", deleted);
            }
        }
    }

    private File convertToFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }
        return tempFile;
    }
}
