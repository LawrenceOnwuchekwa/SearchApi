package com.api.searchtool.DocumentRepresentation;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentRepository {
    Resource getDocument(String filename);
    boolean addDocument(String filename, MultipartFile file) throws IOException;
}
