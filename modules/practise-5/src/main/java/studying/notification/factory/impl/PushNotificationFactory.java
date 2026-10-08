package studying.notification.factory.impl;

import org.springframework.stereotype.Component;
import studying.notification.enums.NotificationChannel;
import studying.notification.factory.NotificationFactory;
import studying.notification.model.Notification;
import studying.notification.model.NotificationDraft;
import studying.notification.model.PushNotification;

/** Фабрика push. */
@Component
public final class PushNotificationFactory implements NotificationFactory {
    @Override
    public Notification create(final NotificationDraft draft) {
        return PushNotification.builder()
            .deviceId(draft.recipient())
            .text(draft.text())
            .createdAt(draft.createdAt())
            .priority(draft.priority())
            .build();
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.PUSH;
    }
}
