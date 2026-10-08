package studying.notification.model;

import lombok.Builder;
import studying.notification.enums.NotificationPriority;
import java.time.LocalDateTime;

/**
 * InApp.
 * @param userId юзер
 * @param text текст
 * @param createdAt время
 * @param priority приоритет
 */
@Builder
public record InAppNotification(
    String userId,
    String text,
    LocalDateTime createdAt,
    NotificationPriority priority
) implements Notification { }
