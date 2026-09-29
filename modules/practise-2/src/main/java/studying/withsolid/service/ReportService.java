package studying.withsolid.service;

import lombok.RequiredArgsConstructor;
import studying.withsolid.model.Report;

/**
 * Основной сервис для обработки отчетов.
 * Координирует процесс сохранения и отправки.
 */
@RequiredArgsConstructor
public class ReportService {
    private final ReportSaver reportSaver;
    private final ReportSender reportSender;

    public void process(Report report, String recipient) {
        reportSaver.save(report);
        reportSender.send(report, recipient);
    }
}
