package com.github.evchumichev.file_downloader.services;

import java.io.File;
import java.net.URLConnection;

public class FileCreator {
    private static FileCreator fileCreator;
    private NameGenerator nameGenerator;

    public static synchronized FileCreator getInstance() {
        if (fileCreator == null) {
            fileCreator = new FileCreator();
        }
        return fileCreator;
    }
    private FileCreator() {
        nameGenerator = NameGenerator.getInstance();
    }

    public File create(URLConnection urlConnection, String directoryPath) {
        String path = prepareDirectoryPath(directoryPath);
        String fileName = urlConnection.getHeaderField("Content-Disposition");
        if (fileName != null && fileName.contains("filename=")) {
            fileName = fileName.split("=")[1];
        } else {
            fileName = nameGenerator.generate(urlConnection.getContentType());
        }
        return new File(path + fileName);
    }
    private String prepareDirectoryPath(String path) {
        if (path.isEmpty())
            return path;
        if (path.charAt(path.length() - 1) != '/') {
            path = path + "/";
        }
        return path;
    }
}
