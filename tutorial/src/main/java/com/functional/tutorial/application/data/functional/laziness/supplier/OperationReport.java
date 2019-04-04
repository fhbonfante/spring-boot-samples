package com.functional.tutorial.application.data.functional.laziness.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Supplier;

@AllArgsConstructor
@Getter
@Setter
public class OperationReport {

    private long operationId;
    private File operationFile;
    private Supplier<String> reportContent = this::loadContent;

    public String getReportContent() {
        return reportContent.get();
    }

    private String loadContent() {
        try {
            return loadContentFromFile();
        } catch (IOException e) {
            return null;
        }
    }

    private String loadContentFromFile() throws IOException {
        return new String(Files.readAllBytes(operationFile.toPath()));
    }
}
