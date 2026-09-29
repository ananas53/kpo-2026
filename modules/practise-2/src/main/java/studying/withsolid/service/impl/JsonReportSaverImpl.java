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
 * Реализация сохранителя отчетов в формате JSON.
 */
public class JsonReportSaverImpl implements ReportSaver {
    @Override
    public void save(Report report) {
        String content = String.format("""
                {
                  "title": "%s",
                  "date": "%s",
                  "time": "%s",
                  "carsSold": %d,
                  "motorcyclesSold": %d
                }
                """, 
                report.title(), report.date(), report.time(), 
                report.carsSold(), report.motorcyclesSold());
        
        Path dirPath = Path.of("reports");
        String fileName = "report-" + report.date().toString() + "-" + 
                report.time().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".json";
        Path filePath = dirPath.resolve(fileName);
        
        try {
            Files.createDirectories(dirPath);
            Files.writeString(filePath, content);
        } catch (IOException e) {
            throw new ApplicationException(ApplicationErrorCode.FILE_WRITE_ERROR, "Не удалось сохранить JSON-отчёт в файл " + filePath, e);
        }
    }
}
