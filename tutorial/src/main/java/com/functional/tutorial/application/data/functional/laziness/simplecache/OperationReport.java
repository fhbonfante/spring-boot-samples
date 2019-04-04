package com.functional.tutorial.application.data.functional.laziness.simplecache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@AllArgsConstructor
@Getter
@Setter
public class OperationReport {

    private long operationId;
    private File operationFile;
    private String reportContent;

    public String getReportContent() {
        if (reportContent == null) {
            loadContent();
        }

        return reportContent;
    }

    private void loadContent() {
        try {
            reportContent = loadContentFromFile();
        } catch (IOException e) {
            reportContent = null;
        }
    }

    private String loadContentFromFile() throws IOException {
        return new String(Files.readAllBytes(operationFile.toPath()));
    }
}
