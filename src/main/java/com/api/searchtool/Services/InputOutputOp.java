package com.api.searchtool.Services;

import com.api.searchtool.DocumentRepresentation.RepositoryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
public class InputOutputOp {
    private final RepositoryConfig repositoryConfig;

    public InputOutputOp(RepositoryConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
    }
    public List<File> doSomethingWithRepository() throws FileNotFoundException {
        File file = ResourceUtils.getFile(repositoryConfig.getPath());
        File[] files = file.listFiles();

        return Arrays.asList(files);

    }
}
