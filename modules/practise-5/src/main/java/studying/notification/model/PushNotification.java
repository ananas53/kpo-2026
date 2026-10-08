package studying.notification.model;

import lombok.Builder;
import studying.notification.enums.NotificationPriority;
import java.time.LocalDateTime;

/**
 * Push.
 * @param deviceId девайс
 * @param text текст
 * @param createdAt время
 * @param priority приоритет
 */
@Builder
public record PushNotification(
    String deviceId,
    String text,
    LocalDateTime createdAt,
    NotificationPriority priority
) implements Notification { }
