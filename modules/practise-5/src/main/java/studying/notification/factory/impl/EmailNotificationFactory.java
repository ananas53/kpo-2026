package studying.notification.factory.impl;

import org.springframework.stereotype.Component;
import studying.notification.enums.NotificationChannel;
import studying.notification.factory.NotificationFactory;
import studying.notification.model.EmailNotification;
import studying.notification.model.Notification;
import studying.notification.model.NotificationDraft;

/** Фабрика email. */
@Component
public final class EmailNotificationFactory implements NotificationFactory {
    @Override
    public Notification create(final NotificationDraft draft) {
        return EmailNotification.builder()
            .recipient(draft.recipient())
            .text(draft.text())
            .createdAt(draft.createdAt())
            .priority(draft.priority())
            .subject("Уведомление")
            .build();
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.EMAIL;
    }
}
