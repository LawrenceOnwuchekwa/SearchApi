package com.api.searchtool.Controllers;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("pdf", "doc",
            "docx", "ppt", "ppts", "xls", "xlsx", "txt", "html", "xml");
    public String handlefileUpload(@RequestParam("file")MultipartFile file){


    }
}
