package com.api.searchtool.DocumentRepresentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class FileService implements DocumentRepository {
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("pdf", "doc", "docx", "ppt", "ppts", "xls", "xlsx", "txt", "html", "xml");

    @Autowired
    private final RepositoryConfig repositoryConfig;

    private final Path root;

    public FileService(@Value("${repository.path}") RepositoryConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
        root = Paths.get(repositoryConfig.getPath());
    }
    // Path to the repository



    public void saveFile(MultipartFile file) {
        try{
            Files.copy(file.getInputStream(),root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
            throw new RuntimeException("A file of that name already exists.");
        }
            throw new RuntimeException(e.getMessage());
        }
        String fileName = file.getOriginalFilename();
        File file1 = new File(fileName);
        if (!isAllowedFileType(file1)) {
            throw new RuntimeException("file not allowed");
        }

    }



    public void removeExistingFiles() {
        File repositoryDir = new File(String.valueOf(repositoryConfig.getPath()));
        File[] files = repositoryDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && FileService.isAllowedFileType((new File(file.getName())))){
                    file.delete();
                }
            }
        }
    }

    /**
     *
     * @param fileName the name of the file
     * @return A boolean value that returns true if the extension of that file is
     * contained in the list of extensions allowed.
     */
    public static boolean isAllowedFileType(File fileName) {
        String filr = fileName.getName();
        String extension = filr.substring(filr.lastIndexOf(".") + 1);
        return ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }
}

