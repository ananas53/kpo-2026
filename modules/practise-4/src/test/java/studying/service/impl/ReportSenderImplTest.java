package studying.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import studying.exception.ApplicationErrorCode;
import studying.exception.ApplicationException;
import studying.model.Report;

/** Unit tests for the safe in-memory report sender. */
@DisplayName("Unit-тесты отправителя отчётов")
class ReportSenderImplTest {
    private static final int YEAR = 2026;
    private static final int MONTH = 9;
    private static final int DAY = 29;
    private static final int CARS = 12;

    private static final Report REPORT = Report.builder()
            .title("Продажи")
            .date(LocalDate.of(YEAR, MONTH, DAY))
            .carsSold(CARS)
            .build();

    @Test
    @DisplayName("Отправитель запоминает отправленный отчёт и получателя")
    void remembersDeliveredReportAndRecipient() {
        final ReportSenderImpl sender = new ReportSenderImpl();

        sender.send(REPORT, "student@hse.ru");

        assertEquals(REPORT, sender.getLastDelivery().report());
        assertEquals("student@hse.ru", sender.getLastDelivery().email());
    }

    @Test
    @DisplayName("Отправитель отклоняет отсутствующий email")
    void rejectsMissingEmail() {
        final ReportSenderImpl sender = new ReportSenderImpl();

        final ApplicationException exception = assertThrows(
                ApplicationException.class, () -> sender.send(REPORT, null));

        assertEquals(ApplicationErrorCode.VALIDATION_ERROR,
                exception.getCode());
    }
}
