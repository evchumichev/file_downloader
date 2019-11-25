package com.github.evchumichev.file_downloader.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPDownloader implements Runnable {
    private static final int RANGE_NOT_SATISFIABLE = 416;
    private FileCreator fileCreator;
    private String fileURL;
    private String filePath;
    private HttpURLConnection connection;

    public HTTPDownloader(String url, String path) {
        fileURL = url;
        filePath = path;
        fileCreator = FileCreator.getInstance();
    }

    @Override
    public void run() {
        downloadFile();
    }

    private void downloadFile() {
        try {
            File file = fileCreator.create(fileURL, filePath);
            connection = (HttpURLConnection) new URL(fileURL).openConnection();
            if (file.exists()) {
                connection.setRequestProperty("Range", "bytes=" + file.length() + "-");
            }
            if (connection.getResponseCode() == HTTPDownloader.RANGE_NOT_SATISFIABLE) {
                throw new IOException(String.format("File %s already downloaded!", file.getName()));
            }
                try (BufferedInputStream stream = new BufferedInputStream(connection.getInputStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
                    byte[] dataBuffer = new byte[1024];
                    int bytes;
                    while ((bytes = stream.read(dataBuffer, 0, 1024)) != -1) {
                        fileOutputStream.write(dataBuffer, 0, bytes);
                    }
                }
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
