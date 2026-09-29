package studying.withsolid.service.impl;

import studying.withsolid.exception.ApplicationErrorCode;
import studying.withsolid.exception.ApplicationException;
import studying.withsolid.model.Report;
import studying.withsolid.service.ReportSaver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

/**
 * Реализация сохранителя отчетов в обычном текстовом формате.
 */
public class TextReportSaver implements ReportSaver {
    @Override
    public void save(Report report) {
        if (report == null) {
            throw new ApplicationException(ApplicationErrorCode.VALIDATION_ERROR, "Отчет не задан");
        }
        
        Path dirPath = Path.of("reports");
        String fileName = "report-" + report.date().toString() + "-" + 
                report.time().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".txt";
        Path filePath = dirPath.resolve(fileName);
        
        try {
            Files.createDirectories(dirPath);
            Files.writeString(filePath, report.toString());
        } catch (IOException e) {
            throw new ApplicationException(ApplicationErrorCode.FILE_WRITE_ERROR, "Не удалось сохранить отчёт в файл " + filePath, e);
        }
    }
}
