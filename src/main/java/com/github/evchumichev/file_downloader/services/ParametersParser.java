package com.github.evchumichev.file_downloader.services;

import com.github.evchumichev.file_downloader.domains.ParametersDTO;

import java.util.*;

public class ParametersParser {
    public ParametersDTO prepare(String[] args) {
        List<String> urls = new LinkedList<>();
        String filePath = null;
        for (String s : args) {
            if (s.contains("url")) {
                if (s.charAt(s.length() - 1) == '=') {
                    throw new RuntimeException("URL has empty parameter!");
                }
                urls.add(s.split("=")[1]);
                continue;
            }
            if (s.contains("folder")) {
                if (filePath != null) {
                    throw new RuntimeException("Your input parameters contain more then one folder value!");
                }
                if (s.charAt(s.length() - 1) == '=') {
                    filePath = "";
                    continue;
                }
                filePath = s.split("=")[1];
                System.out.println(filePath);
                continue;
            }
            throw new RuntimeException(String.format("Incorrect input of parameter: %s", s));
        }
        if (urls.isEmpty()) {
            throw new RuntimeException("Parameters does not contain any urls!");
        }
        if (filePath == null) {
            throw new RuntimeException("Parameters does not contain save folder!");
        }
        return new ParametersDTO(urls, filePath);
    }
}
