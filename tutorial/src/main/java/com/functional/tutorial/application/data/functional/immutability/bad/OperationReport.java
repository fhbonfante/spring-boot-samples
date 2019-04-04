package com.functional.tutorial.application.data.functional.immutability.bad;

import com.functional.tutorial.application.data.functional.laziness.eval.ReportType;
import cyclops.control.Eval;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Getter
@Setter
public class OperationReport {

    private long operationId;
    private File operationFile;
    private ReportType type;
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
