package com.api.searchtool.DocumentRepresentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * This indexes all the files in the repository
 * This performs inverted index on the files
 */
@Component
public class Indexer {
    private final RepositoryConfig repositoryConfig;
    private static final Set<String> excludedWords = new HashSet<>();

    static {
        // Add words to be excluded (e.g., prepositions, articles, etc.) to the set
        excludedWords.addAll(Arrays.asList("a", "an", "the", "in", "on", "at", "of", "to", "the", "for", "with", "by", "and", "or", "but"));
    }

    /**
     * This injects the configuration of the repository into the Indexer.
     * This gives the indexer access to the repository to perform
     * the inverted index operations on files contained in the repository.
     * @param repositoryConfig
     */
    public Indexer(RepositoryConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
    }

    /**
     * This performs the inverted index operation on the files in the repository
     * @return a Map, with the indexes as keys and the values and the values are a set of documents
     */

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

    /**
     * A static helper method
     * This indexes each file while removing words that are excluded from being part of the index
     * @param file The file to be indexed
     * @param invertedIndex
     */
    public static void indexFile(File file, Map<String, Set<String>> invertedIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String docId = file.getName(); // Use filename as document ID
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Remove non-alphanumeric characters
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