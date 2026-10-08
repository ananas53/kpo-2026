package studying.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import studying.notification.enums.NotificationChannel;
import studying.notification.enums.NotificationPriority;
import studying.notification.model.Notification;
import studying.notification.model.NotificationDraft;
import studying.notification.services.NotificationFactoryResolver;

import java.time.LocalDateTime;

/**
 * Точка входа.
 */
@SpringBootApplication
public class Main {

    /** Конструктор. */
    public Main() {
    }

    /**
     * Main метод.
     * @param args аргументы
     */
    public static void main(final String[] args) {
        final ApplicationContext context =
            SpringApplication.run(Main.class, args);
        final NotificationFactoryResolver resolver =
            context.getBean(NotificationFactoryResolver.class);

        final NotificationDraft draft = NotificationDraft.builder()
            .channel(NotificationChannel.EMAIL)
            .recipient("test@example.com")
            .text("Ваш заказ готов!")
            .priority(NotificationPriority.HIGH)
            .createdAt(LocalDateTime.now())
            .build();
        final Notification notification = resolver.processDraft(draft);
        System.out.println("Создано: " + notification);
    }
}
