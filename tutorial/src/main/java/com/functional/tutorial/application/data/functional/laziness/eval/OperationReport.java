package com.functional.tutorial.application.data.functional.laziness.eval;

import cyclops.control.Eval;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationReport {

    private long operationId;
    private File operationFile;
    private ReportType reportType;
    private Eval<String> reportContent = Eval.later(this::loadContent);

    public String getReportContent() {
        return reportContent.get();
    }

    public String loadContent() {
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
