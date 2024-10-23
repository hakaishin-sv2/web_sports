package com.example.web_sports.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class UploadApi {

    private final String UPLOAD_DIR = "src/main/resources/uploads/";

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("folder") String folder) {
        // Tạo thư mục uploads nếu chưa tồn tại
        createFolderIfNotExists(UPLOAD_DIR);

        String folderPath = UPLOAD_DIR + folder;
        createFolderIfNotExists(folderPath);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = generateUniqueFileName(fileName); // Tạo tên file duy nhất
        Path filePath = Paths.get(folderPath, uniqueFileName);

        try {
            Files.copy(file.getInputStream(), filePath);

            // Trả về đường dẫn tệp đã upload (không bao gồm src/main/resources/)
            String fileUrl = "uploads/" + folder + "/" + uniqueFileName;
            return ResponseEntity.ok(fileUrl); // Trả về đường dẫn tệp
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tải lên tệp: " + e.getMessage());
        }
    }

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<List<String>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                                            @RequestParam("folder") String folder) {
        // Tạo thư mục uploads nếu chưa tồn tại
        createFolderIfNotExists(UPLOAD_DIR);

        String folderPath = UPLOAD_DIR + folder;
        createFolderIfNotExists(folderPath);

        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueFileName = generateUniqueFileName(fileName); // Tạo tên file duy nhất
            Path filePath = Paths.get(folderPath, uniqueFileName);

            try {
                Files.copy(file.getInputStream(), filePath);

                // Lưu đường dẫn vào danh sách (không bao gồm src/main/resources/)
                String fileUrl = "uploads/" + folder + "/" + uniqueFileName;
                fileUrls.add(fileUrl);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonList("Lỗi khi tải lên tệp: " + e.getMessage()));
            }
        }

        return ResponseEntity.ok(fileUrls); // Trả về danh sách đường dẫn tệp
    }

    private void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // Hàm tạo tên file duy nhất
    private String generateUniqueFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String uniqueFileName = baseName + "_" + System.currentTimeMillis() + extension; // Hoặc UUID.randomUUID()
        return uniqueFileName;
    }
}
