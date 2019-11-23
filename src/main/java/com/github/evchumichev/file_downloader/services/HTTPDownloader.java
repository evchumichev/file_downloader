package com.github.evchumichev.file_downloader.services;

import java.io.*;
import java.net.URL;


public class Downloader {

    public void downloadFile(String url, String path) {
        try {
            File file = new File(path);
            file.mkdirs();
            file.createNewFile();
            try (BufferedInputStream stream = new BufferedInputStream(new URL(url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                byte[] dataBuffer = new byte[1024];
                int bytes;
                while ((bytes = stream.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytes);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
