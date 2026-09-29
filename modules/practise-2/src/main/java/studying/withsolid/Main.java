package studying.withsolid;

import studying.withsolid.model.Report;
import studying.withsolid.service.ReportService;
import studying.withsolid.service.impl.EmailReportSender;
import studying.withsolid.service.impl.TextReportSaver;
import studying.withsolid.service.impl.JsonReportSaverImpl;
import studying.withsolid.service.impl.XmlReportSaverImpl;

import java.time.LocalDateTime;

public class Main {
    /**
     * Runs the report creation, persistence, and delivery demonstration.
     */
    public static void main(String[] args) {
        var now = LocalDateTime.now();
        var report = Report.builder()
                .title("Отчёт")
                .date(now.toLocalDate())
                .time(now.toLocalTime())
                .carsSold(100)
                .motorcyclesSold(50)
                .build();

        // Пример использования текстового сохранителя:
        var textReportService = new ReportService(
                new TextReportSaver(),
                new EmailReportSender()
        );
        textReportService.process(report, "example@example.com");

        // Пример использования JSON сохранителя:
        var jsonReportService = new ReportService(
                new JsonReportSaverImpl(),
                new EmailReportSender()
        );
        jsonReportService.process(report, "example@example.com");

        // Пример использования XML сохранителя:
        var xmlReportService = new ReportService(
                new XmlReportSaverImpl(),
                new EmailReportSender()
        );
        xmlReportService.process(report, "example@example.com");
    }
}
