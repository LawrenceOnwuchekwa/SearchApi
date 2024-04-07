package com.api.searchtool.Controllers;

import com.api.searchtool.DocumentRepresentation.FileDocumentRepository;
import com.api.searchtool.StaticHelperMethods.StaticHelperMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    FileDocumentRepository fileDocumentRepository;

    @GetMapping("/upload")
    public ResponseEntity<String> handlefileUpload(@RequestParam("file")MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = StaticHelperMethods.getFileExtension(originalFilename);
        List<String> extension = fileDocumentRepository.getAllowedExtensions();
        if(fileExtension == null || !extension.contains(fileExtension.toLowerCase())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported File type");
        }
        Path targetPath = Paths.get(fileDocumentRepository.getPath(),originalFilename);
        file.transferTo(targetPath.toFile());
        return ResponseEntity.ok("File uploaded successfully: " + originalFilename);

    }

}
