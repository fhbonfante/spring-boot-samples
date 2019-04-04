package com.functional.tutorial.application.data.functional.nonulls;

import com.functional.tutorial.application.data.functional.laziness.eval.ReportType;
import cyclops.control.Eval;
import cyclops.control.Option;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Wither;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Function;

@Getter
@Setter
@Wither
public class OperationReport {

    private final long operationId;
    private final File operationFile;
    private final ReportType type;
    private final Eval<String> reportContent = Eval.later(this::loadContent)
                                                   .concatMap(Function.identity());

    public OperationReport(long operationId, File operationFile, ReportType type) {
        this.operationId = operationId;
        this.operationFile = operationFile;
        this.type = type;
    }

    public String getReportContent() {
        return reportContent.get();
    }

    public Option<String> loadContent() {
        try {
            return Option.of(loadContentFromFile());
        } catch (IOException e) {
            return Option.none();
        }
    }

    private String loadContentFromFile() throws IOException {
        return new String(Files.readAllBytes(operationFile.toPath()));
    }

}
