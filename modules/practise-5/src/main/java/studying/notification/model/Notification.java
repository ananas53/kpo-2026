package studying.notification.model;

import studying.notification.enums.NotificationPriority;
import java.time.LocalDateTime;

/** Уведомление. */
public interface Notification {
    /** @return текст */
    String text();
    /** @return время */
    LocalDateTime createdAt();
    /** @return приоритет */
    NotificationPriority priority();
}
