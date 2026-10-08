package studying.notification.factory;

import studying.notification.enums.NotificationChannel;
import studying.notification.model.Notification;
import studying.notification.model.NotificationDraft;

/** Фабрика. */
public interface NotificationFactory {
    /** @param draft черновик
     *  @return уведомление */
    Notification create(NotificationDraft draft);

    /** @return канал */
    NotificationChannel getChannel();
}
