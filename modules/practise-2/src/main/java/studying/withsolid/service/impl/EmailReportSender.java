package studying.withsolid.service.impl;

import studying.withsolid.exception.ApplicationErrorCode;
import studying.withsolid.exception.ApplicationException;
import studying.withsolid.model.Report;
import studying.withsolid.service.ReportSender;

/**
 * Имитация реализации отправителя отчетов по email.
 */
public class EmailReportSender implements ReportSender {
    @Override
    public void send(Report report, String recipient) {
        if (recipient == null || recipient.isBlank()) {
            throw new ApplicationException(ApplicationErrorCode.VALIDATION_ERROR, "Email получателя не задан");
        }
        if (report == null) {
            throw new ApplicationException(ApplicationErrorCode.VALIDATION_ERROR, "Отчет не задан");
        }
        System.out.printf("Отправка отчёта '%s' на email: %s%n", report.title(), recipient);
    }
}
