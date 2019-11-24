package com.github.evchumichev.file_downloader;

import com.github.evchumichev.file_downloader.services.DownloadPreparator;

public class Starter {
    public static void main(String[] args) {
        new DownloadPreparator(args).start();
    }
}
