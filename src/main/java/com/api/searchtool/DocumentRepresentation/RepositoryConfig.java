package com.api.searchtool.DocumentRepresentation;
;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "repository")
/**
 * This is a class that represents the repository to be used.
 * It simply requires the path of the repository
 */
public class RepositoryConfig {
    private String path;


    public RepositoryConfig() {
    }

    /**
     *
     *This initializes the value for the path to the repository
     * @param path The path of the repository(folder)
     */
    public RepositoryConfig(String path) {
        this.path = path;
    }

    /**
     *This returns the path of the repository
     * @return it returns the path of the repository
     */
    // Getter and setter methods
    public String getPath() {
        return path;
    }

    /**
     * This sets the path of the repository
     * @param path The path of the repository(folder)
     */
    public void setPath(String path) {
        this.path = path;
    }
}
