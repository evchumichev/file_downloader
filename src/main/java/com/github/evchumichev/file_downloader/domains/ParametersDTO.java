package com.github.evchumichev.file_downloader.domains;

import java.util.List;

public class ParametersDTO {
    private List<String> urls;
    private String filePath;

    public ParametersDTO(List<String> urls, String filePath) {
        this.urls = urls;
        this.filePath = filePath;
    }

    public List<String> getUrls() {
        return urls;
    }

    public String getFilePath() {
        return filePath;
    }
}
