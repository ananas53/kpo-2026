package studying.withsolid.service;

import studying.withsolid.model.Report;

/**
 * Контракт для отправки отчета получателю.
 */
@FunctionalInterface
public interface ReportSender {
    /**
     * Отправляет отчет указанному получателю.
     *
     * @param report    отчет для отправки
     * @param recipient email получателя
     */
    void send(Report report, String recipient);
}
