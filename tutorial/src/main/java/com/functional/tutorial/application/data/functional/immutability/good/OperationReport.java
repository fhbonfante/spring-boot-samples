package com.functional.tutorial.application.data.functional.immutability.good;

import com.functional.tutorial.application.data.functional.laziness.eval.ReportType;
import cyclops.control.Eval;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Wither;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Getter
@Wither
public class OperationReport {

    private final long operationId;
    private final File operationFile;
    private final ReportType type;
    private final Eval<String> reportContent = Eval.later(this::loadContent);

    public OperationReport(long operationId, File operationFile, ReportType type) {
        this.operationId = operationId;
        this.operationFile = operationFile;
        this.type = type;
    }

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

