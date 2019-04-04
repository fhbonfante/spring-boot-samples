package com.functional.tutorial.application.manager.immutability.good;

import com.functional.tutorial.application.data.functional.immutability.good.OperationReport;
import com.functional.tutorial.application.data.functional.laziness.eval.ReportType;

import java.io.File;

public class OperationReportService {

    private FileService fileService;

    public OperationReportService(FileService fileService) {
        this.fileService = new FileService();
    }

    public OperationReport createOperationReport(long id, String content, String location, ReportType type) {

        File operationFile = fileService.writeOperationFile(content,id,location);

        return  new OperationReport(id,operationFile,type);
    }

}
