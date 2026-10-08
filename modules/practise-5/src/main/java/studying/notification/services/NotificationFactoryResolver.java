package studying.notification.services;

import org.springframework.stereotype.Service;
import studying.notification.enums.NotificationChannel;
import studying.notification.exception.UnsupportedNotificationChannelException;
import studying.notification.factory.NotificationFactory;
import studying.notification.model.Notification;
import studying.notification.model.NotificationDraft;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/** Сервис резолва. */
@Service
public final class NotificationFactoryResolver {
    private final Map<NotificationChannel, NotificationFactory> factories;

    /** @param factoryList список фабрик */
    public NotificationFactoryResolver(
        final List<NotificationFactory> factoryList) {
        factories = new EnumMap<>(NotificationChannel.class);
        for (final NotificationFactory f : factoryList) {
            factories.put(f.getChannel(), f);
        }
    }

    /** @param draft черновик
     *  @return уведомление */
    public Notification processDraft(final NotificationDraft draft) {
        final NotificationFactory factory = factories.get(draft.channel());
        if (factory == null) {
            throw new UnsupportedNotificationChannelException("Нет фабрики");
        }
        return factory.create(draft);
    }
}
