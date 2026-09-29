package studying.withsolid.service;

import studying.withsolid.model.Report;

/**
 * Контракт для сохранения отчета.
 */
@FunctionalInterface
public interface ReportSaver {
    /**
     * Сохраняет сформированный отчет.
     *
     * @param report отчет для сохранения
     */
    void save(Report report);
}
