package studying.notification.model;

import lombok.Builder;
import studying.notification.enums.NotificationPriority;
import java.time.LocalDateTime;

/**
 * Email.
 * @param recipient получатель
 * @param subject тема
 * @param text текст
 * @param createdAt время
 * @param priority приоритет
 */
@Builder
public record EmailNotification(
    String recipient,
    String subject,
    String text,
    LocalDateTime createdAt,
    NotificationPriority priority
) implements Notification { }
