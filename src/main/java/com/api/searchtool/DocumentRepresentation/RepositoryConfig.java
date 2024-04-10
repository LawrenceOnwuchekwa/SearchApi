package com.api.searchtool.DocumentRepresentation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "repository")
public class RepositoryConfig {
    private String path;

    // Getter and setter methods
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
