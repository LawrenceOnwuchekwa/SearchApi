package com.api.searchtool.DocumentRepresentation;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentRepository {

    String saveFile(MultipartFile file, String destinationDirectory);
    void removeExistingFiles();
}
