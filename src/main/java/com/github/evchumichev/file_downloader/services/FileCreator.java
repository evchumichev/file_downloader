package com.github.evchumichev.file_downloader.services;

import java.io.File;

public class FileCreator {
    private static FileCreator fileCreator;

    public static synchronized FileCreator getInstance() {
        if (fileCreator == null) {
            fileCreator = new FileCreator();
        }
        return fileCreator;
    }

    private FileCreator() {
    }

    public File create(String url, String directoryPath) {
        String fileName = url.substring(url.lastIndexOf('/'));
        return new File(directoryPath + fileName);
    }
}
