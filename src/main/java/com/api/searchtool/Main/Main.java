package com.api.searchtool.Main;

import com.api.searchtool.DocumentRepresentation.FileService;
import com.api.searchtool.DocumentRepresentation.Indexer;
import com.api.searchtool.DocumentRepresentation.RepositoryConfig;
import com.api.searchtool.Services.InputOutputOp;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {



        //A hash map to store words, mapping them to their respective documents after being indexed
        Map<String, Set<String>> invertedIndex = new HashMap<>();
//       file is a directory where the documents are stored
        File file = ResourceUtils.getFile("src/main/java/com/api/searchtool/repository");
//        An array to hold files contained in the document
        File[] files = file.listFiles();
//        Loop through the array of files and perform inverted index
//        then store the terms and document in a hashmap
        for (File arr : files) {
            Indexer.indexFile(arr, invertedIndex);
        }
//        Loop through the hashmap and print out the result
        for (Map.Entry<String, Set<String>> entry : invertedIndex.entrySet()) {
            String key = entry.getKey();
            Set<String> values = entry.getValue();

            System.out.println("Key: " + key);
            System.out.println("Values:");
            for (String value : values) {
                System.out.println(value);
            }
        }
        System.out.println("Hello world");
        for(File arr:files){
            System.out.println(FileService.isAllowedFileType(arr));
        }

    }
}
