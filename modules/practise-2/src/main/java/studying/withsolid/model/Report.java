package studying.withsolid.model;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record Report(
        String title,
        LocalDate date,
        LocalTime time,
        int carsSold,
        int motorcyclesSold
) {
    @Override
    public String toString() {
        return "Отчёт\n" +
               "Заголовок: " + title + "\n" +
               "Дата: " + date + "\n" +
               "Время: " + time + "\n" +
               "--------------------------------\n" +
               "Продано автомобилей: " + carsSold + " шт.\n" +
               "Продано мотоциклов: " + motorcyclesSold + " шт.\n" +
               "--------------------------------\n";
    }
}
