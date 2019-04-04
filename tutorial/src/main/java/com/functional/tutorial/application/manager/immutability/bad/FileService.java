package com.functional.tutorial.application.manager.immutability.bad;

import com.functional.tutorial.application.data.functional.immutability.bad.OperationReport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

    private static final String FILE_EXTENSION = ".data";

    public void writeOperationFile(OperationReport operationReport, String content, long id, String location) {
        String fileName = location.concat(id+ FILE_EXTENSION);
        File operationFile = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(operationFile)){
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace(); //don't do this at home, seriously D:
        }
        operationReport.setOperationFile(operationFile);

    }
}
