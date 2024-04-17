package com.api.searchtool.DocumentRepresentation;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * This is the contract for the operations that can be performed on the repository
 */
public interface DocumentRepository {
    /**
     * This saves the file to the repository
     * @param file The file to be added to the repository
     */
    void saveFile(MultipartFile file);

    /**
     * This removes files that are of a particular extension type
     */
    void removeExistingFiles();
}
