package com.github.evchumichev.file_downloader;

import com.github.evchumichev.file_downloader.services.DownloadThreadStarter;

public class Starter {
    public static void main(String[] args) {
        new DownloadThreadStarter().start();
    }
}
