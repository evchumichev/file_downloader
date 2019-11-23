package com.github.evchumichev.file_downloader.services;


public class DownloadThreadStarter {

    public void start() {
        String url = "https://upload.wikimedia.org/wikipedia/ru/thumb/3/39/Java_logo.svg/1200px-Java_logo.svg.png";
        String path = "";
        try {
            new Thread(new HTTPDownloader(url, path)).start();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }
}
