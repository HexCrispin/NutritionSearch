package com.nutrition.dataLoader;

public enum FileTypeEnum {
    CSV("csv", ".csv"),
    JSON("json", ".json"),
    XML("xml", ".xml");
    private final String name;
    private final String fileSignature;

    FileTypeEnum(String name, String fileSignature){
        this.name = name;
        this.fileSignature = fileSignature;
    }

    public String getName() {
        return name;
    }

    public String getFileSignature() {
        return fileSignature;
    }

    public static FileTypeEnum fromFileSignature(String fileSignature) {
        for (FileTypeEnum type : values()) {
            if (type.fileSignature.equals(fileSignature)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported file type: " + fileSignature);
    }

    @Override
    public String toString() {
        return name;
    }
}
