package com.github.evchumichev.file_downloader.services;


import com.github.evchumichev.file_downloader.domains.ParametersDTO;

import java.util.List;

public class DownloadThreadStarter {

    private ParametersParser parametersParser;
    String[] args;

    public DownloadThreadStarter(String[] args) {
        parametersParser = new ParametersParser();
        this.args = args;
    }

    public void start() {
        try {
            ParametersDTO parameters = parametersParser.prepare(args);
            List<String> urls = parameters.getUrls();
            String filePath = parameters.getFilePath();
            for (String url : urls) {
                new Thread(new HTTPDownloader(url, filePath)).start();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
