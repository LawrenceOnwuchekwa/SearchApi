package com.api.searchtool.DocumentRepresentation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService implements DocumentRepository {
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("pdf", "doc", "docx", "ppt", "ppts", "xls", "xlsx", "txt", "html", "xml");

    private final RepositoryConfig repositoryConfig;

    public FileService(RepositoryConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
    }
    // Path to the repository


    public String saveFile(MultipartFile file, String destinationDirectory) {
        String fileName = file.getOriginalFilename();
        File file1 = new File(fileName);
        if (!isAllowedFileType(file1)) {
            return "File type not allowed";
        }

//        if (file.isEmpty()) {
//            return "Please select a file to upload";
//        }

        String filePath = repositoryConfig.getPath() + File.separator + file.getOriginalFilename();
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
            byte[] bytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = file.getInputStream().read(bytes)) != -1) {
                stream.write(bytes, 0, bytesRead);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return "successful upload";
    }



    public void removeExistingFiles() {
        File repositoryDir = new File(repositoryConfig.getPath());
        File[] files = repositoryDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && FileService.isAllowedFileType((new File(file.getName())))){
                    file.delete();
                }
            }
        }
    }

    public static boolean isAllowedFileType(File fileName) {
        String filr = fileName.getName();
        String extension = filr.substring(filr.lastIndexOf(".") + 1);
        return ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }
}

