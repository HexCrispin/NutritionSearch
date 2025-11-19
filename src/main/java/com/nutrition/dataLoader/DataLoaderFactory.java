package com.nutrition.dataLoader;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DataLoaderFactory {

    private final CSVDataLoader csvDataLoader;
    private final JSONDataLoader jsonDataLoader;
    private final XMLDataLoader xmlDataLoader;

    public DataLoaderFactory(CSVDataLoader csvDataLoader,
                             JSONDataLoader jsonDataLoader,
                             XMLDataLoader xmlDataLoader) {
        this.csvDataLoader = csvDataLoader;
        this.jsonDataLoader = jsonDataLoader;
        this.xmlDataLoader = xmlDataLoader;
    }

    public DataLoader getLoader(File file) {
        String fileName = file.getName().toLowerCase();
        String fileSignatureString = fileName.substring(fileName.lastIndexOf('.'));

        FileTypeEnum fileSignature = FileTypeEnum.fromFileSignature(fileSignatureString);
        return switch (fileSignature) {
            case CSV -> csvDataLoader;
            case JSON -> jsonDataLoader;
            case XML -> xmlDataLoader;
        };
    }
}
