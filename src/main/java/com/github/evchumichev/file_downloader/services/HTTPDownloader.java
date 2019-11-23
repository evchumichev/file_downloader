package com.github.evchumichev.file_downloader.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;


public class HTTPDownloader implements Runnable {
    private FileCreator fileCreator;
    private String fileURL;
    private String filePath;
    private URLConnection connection;

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
            URL url = new URL(fileURL);
            connection = url.openConnection();
            File file = fileCreator.create(connection, filePath);
            System.out.println(file.getAbsolutePath());
            try (BufferedInputStream stream = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                byte[] dataBuffer = new byte[1024];
                int bytes;
                while ((bytes = stream.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytes);
                }
            }

        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
