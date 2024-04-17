package com.api.searchtool.Controllers;

import com.api.searchtool.DocumentRepresentation.FileService;
import com.api.searchtool.DocumentRepresentation.Indexer;
import com.api.searchtool.DocumentRepresentation.RepositoryConfig;
import com.api.searchtool.Services.InputOutputOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class Controllers {

    private final Indexer indexer;

    private final FileService fileService;

    private final InputOutputOp inputOutputOp;

    private final RepositoryConfig repositoryConfig;


    @Autowired
    public Controllers(Indexer indexer, FileService fileService, InputOutputOp inputOutputOp, RepositoryConfig repositoryConfig) {
        this.indexer = indexer;
        this.fileService = fileService;
        this.inputOutputOp = inputOutputOp;
        this.repositoryConfig = repositoryConfig;
    }

    @GetMapping("/result")
    public List<File> showDocs() throws FileNotFoundException {
        return inputOutputOp.doSomethingWithRepository();
    }

    /**
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        fileService.removeExistingFiles();
        fileService.saveFile(file);
    }

    @GetMapping("/indexes")
    public Map<String, Set<String>> showIndex(){
        return indexer.indexAllFiles();
    }
}