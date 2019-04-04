package com.functional.tutorial.application.manager.immutability.bad;

import com.functional.tutorial.application.data.functional.immutability.bad.OperationReport;
import com.functional.tutorial.application.data.functional.laziness.eval.ReportType;

public class OperationReportService {

    private FileService fileService;

    public OperationReportService(final FileService fileService) {
        this.fileService = fileService;
    }

    public OperationReport createOperationReport(long id, String content, String location, ReportType type) {
        OperationReport operationReport = new OperationReport();

        fileService.writeOperationFile(operationReport,content,id,location);
        operationReport.setOperationId(id);
        operationReport.setType(type);

        return operationReport;
    }
}
