package com.functional.tutorial.application.data.functional.laziness.memoized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@AllArgsConstructor
@Getter
@Setter
public class OperationReport {

    private long operationId;
    private File operationFile;
    private Supplier<String> reportContent = memoizeSupplier(this::loadContent);

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

    private static <T> Supplier<T> memoizeSupplier(final Supplier<T> s) {
        final Map<Long,T> lazy = new ConcurrentHashMap<>();
        return () -> lazy.computeIfAbsent(1l, i-> s.get());
    }
}
