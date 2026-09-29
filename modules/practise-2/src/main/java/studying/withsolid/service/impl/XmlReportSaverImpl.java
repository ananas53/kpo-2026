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
 * Реализация сохранителя отчетов в формате XML.
 */
public class XmlReportSaverImpl implements ReportSaver {
    @Override
    public void save(Report report) {
        String content = String.format("""
                <?xml version="1.0" encoding="UTF-8"?>
                <report>
                    <title>%s</title>
                    <date>%s</date>
                    <time>%s</time>
                    <carsSold>%d</carsSold>
                    <motorcyclesSold>%d</motorcyclesSold>
                </report>
                """, 
                report.title(), report.date(), report.time(), 
                report.carsSold(), report.motorcyclesSold());
        
        Path dirPath = Path.of("reports");
        String fileName = "report-" + report.date().toString() + "-" + 
                report.time().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".xml";
        Path filePath = dirPath.resolve(fileName);
        
        try {
            Files.createDirectories(dirPath);
            Files.writeString(filePath, content);
        } catch (IOException e) {
            throw new ApplicationException(ApplicationErrorCode.FILE_WRITE_ERROR, "Не удалось сохранить XML-отчёт в файл " + filePath, e);
        }
    }
}
