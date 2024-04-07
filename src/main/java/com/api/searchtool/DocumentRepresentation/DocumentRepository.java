package com.api.searchtool.DocumentRepresentation;

import org.springframework.core.io.Resource;

public interface DocumentRepository {
    Resource getDocument(String filename);
    void addDocument();
}
