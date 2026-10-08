package studying.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import studying.notification.enums.NotificationChannel;
import studying.notification.enums.NotificationPriority;
import studying.notification.exception.NotificationValidationException;
import studying.notification.factory.impl.EmailNotificationFactory;
import studying.notification.factory.impl.InAppNotificationFactory;
import studying.notification.factory.impl.PushNotificationFactory;
import studying.notification.model.EmailNotification;
import studying.notification.model.InAppNotification;
import studying.notification.model.Notification;
import studying.notification.model.NotificationDraft;
import studying.notification.model.PushNotification;
import studying.notification.services.NotificationFactoryResolver;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NotificationTest {

    private static final int YEAR = 2026;
    private static final int MONTH = 10;
    private static final int DAY = 1;
    private static final int HOUR = 10;
    private static final int MINUTE = 15;

    private final LocalDateTime fixedTime =
        LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE);

    @Test
    @DisplayName("Builder формирует черновик со всеми полями")
    void testBuilderFormsDraft() {
        final NotificationDraft draft = NotificationDraft.builder()
            .channel(NotificationChannel.EMAIL)
            .recipient("user@example.org")
            .text("Ваш заказ готов")
            .createdAt(fixedTime)
            .priority(NotificationPriority.HIGH)
            .build();

        assertThat(draft.channel()).isEqualTo(NotificationChannel.EMAIL);
        assertThat(draft.recipient()).isEqualTo("user@example.org");
        assertThat(draft.text()).isEqualTo("Ваш заказ готов");
        assertThat(draft.createdAt()).isEqualTo(fixedTime);
        assertThat(draft.priority()).isEqualTo(NotificationPriority.HIGH);
    }

    @Test
    @DisplayName("Builder отклоняет пустой текст и недопустимый приоритет")
    void testBuilderRejectsEmpty() {
        assertThatThrownBy(() -> NotificationDraft.builder()
            .channel(NotificationChannel.EMAIL)
            .recipient(null)
            .text("Текст")
            .createdAt(fixedTime)
            .priority(NotificationPriority.NORMAL)
            .build())
            .isInstanceOf(NotificationValidationException.class);

        assertThatThrownBy(() -> NotificationDraft.builder()
            .channel(NotificationChannel.EMAIL)
            .recipient("user")
            .text("   ")
            .createdAt(fixedTime)
            .priority(NotificationPriority.NORMAL)
            .build())
            .isInstanceOf(NotificationValidationException.class);

        assertThatThrownBy(() -> NotificationDraft.builder()
            .channel(NotificationChannel.EMAIL)
            .recipient("user")
            .text("Текст")
            .createdAt(fixedTime)
            .priority(null)
            .build())
            .isInstanceOf(NotificationValidationException.class);
    }

    @Test
    @DisplayName("Каждая фабрика переносит общие поля")
    void testFactoryTransfersFields() {
        final NotificationDraft draft = NotificationDraft.builder()
            .channel(NotificationChannel.EMAIL)
            .recipient("user")
            .text("Msg")
            .createdAt(fixedTime)
            .priority(NotificationPriority.NORMAL)
            .build();

        final Notification email = new EmailNotificationFactory().create(draft);
        assertThat(email.text()).isEqualTo("Msg");

        final Notification push = new PushNotificationFactory().create(draft);
        assertThat(push.text()).isEqualTo("Msg");

        final Notification inApp = new InAppNotificationFactory().create(draft);
        assertThat(inApp.text()).isEqualTo("Msg");
    }

    @Test
    @DisplayName("Фабрики добавляют специфичные поля")
    void testFactoriesAddSpecificFields() {
        final NotificationDraft draft = NotificationDraft.builder()
            .channel(NotificationChannel.EMAIL)
            .recipient("user")
            .text("Msg")
            .createdAt(fixedTime)
            .priority(NotificationPriority.NORMAL)
            .build();

        final EmailNotification email =
            (EmailNotification) new EmailNotificationFactory().create(draft);
        assertThat(email.recipient()).isEqualTo("user");

        final PushNotification push =
            (PushNotification) new PushNotificationFactory().create(draft);
        assertThat(push.deviceId()).isEqualTo("user");

        final InAppNotification inApp =
            (InAppNotification) new InAppNotificationFactory().create(draft);
        assertThat(inApp.userId()).isEqualTo("user");
    }

    @Test
    @DisplayName("Интеграционный сценарий")
    void testIntegration() {
        final NotificationFactoryResolver srv =
            new NotificationFactoryResolver(java.util.List.of(
                new PushNotificationFactory()));

        final NotificationDraft draft = NotificationDraft.builder()
            .channel(NotificationChannel.PUSH)
            .recipient("dev-123")
            .text("Push")
            .createdAt(fixedTime)
            .priority(NotificationPriority.CRITICAL)
            .build();

        final Notification notif = srv.processDraft(draft);

        assertThat(notif).isInstanceOf(PushNotification.class);
    }
}
