package com.github.evchumichev.file_downloader.services;


import com.github.evchumichev.file_downloader.domains.ParametersDTO;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadPreparator {

    private ParametersParser parametersParser;
    private ExecutorService executorService;
    private String[] args;

    public DownloadPreparator(String[] args) {
        parametersParser = new ParametersParser();
        executorService = Executors.newFixedThreadPool(5);
        this.args = args;
    }

    public void start() {
        try {
            ParametersDTO parameters = parametersParser.prepare(args);
            List<String> urls = parameters.getUrls();
            String filePath = parameters.getFilePath();
            File file = new File(filePath);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    throw new RuntimeException("Could not create directory!");
                }
            }
            for (String url : urls) {
                executorService.submit(new HTTPDownloader(url, filePath));
            }
            executorService.shutdown();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
