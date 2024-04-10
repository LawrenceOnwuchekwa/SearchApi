package com.api.searchtool.DocumentRepresentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class Indexer {
    private final RepositoryConfig repositoryConfig;
    private static final Set<String> excludedWords = new HashSet<>();

    static {
        // Add words to be excluded (e.g., prepositions, articles, etc.) to the set
        excludedWords.addAll(Arrays.asList("a", "an", "the", "in", "on", "at", "of", "to", "for", "with", "by", "and", "or", "but"));
    }

    public Indexer(RepositoryConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
    }

    public Map<String, Set<String>> indexAllFiles() {
        Map<String, Set<String>> invertedIndex = new HashMap<>();

        File repositoryDir = new File(repositoryConfig.getPath());
        File[] files = repositoryDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    Indexer.indexFile(file, invertedIndex);
                }
            }
        }
        return invertedIndex;
    }
    public static void indexFile(File file, Map<String, Set<String>> invertedIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String docId = file.getName(); // Use filename as document ID
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase().replaceAll("[^a-zA-Z0-9]", ""); // Remove non-alphanumeric characters
                    if (!excludedWords.contains(word)) {
                        invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(docId);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}