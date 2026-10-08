package studying.notification.model;

import studying.notification.enums.NotificationChannel;
import studying.notification.enums.NotificationPriority;
import studying.notification.exception.NotificationValidationException;
import java.time.LocalDateTime;

/**
 * Черновик.
 * @param channel канал
 * @param recipient получатель
 * @param text текст
 * @param createdAt время
 * @param priority приоритет
 */
public record NotificationDraft(
    NotificationChannel channel,
    String recipient,
    String text,
    LocalDateTime createdAt,
    NotificationPriority priority
) {
    /** @return билдер */
    public static NotificationDraftBuilder builder() {
        return new NotificationDraftBuilder();
    }

    /** Билдер. */
    public static final class NotificationDraftBuilder {
        private NotificationChannel channel;
        private String recipient;
        private String text;
        private LocalDateTime createdAt;
        private NotificationPriority priority;

        /** @param val канал
         *  @return builder */
        public NotificationDraftBuilder channel(final NotificationChannel val) {
            this.channel = val;
            return this;
        }
        /** @param val получатель
         *  @return builder */
        public NotificationDraftBuilder recipient(final String val) {
            this.recipient = val;
            return this;
        }
        /** @param val текст
         *  @return builder */
        public NotificationDraftBuilder text(final String val) {
            this.text = val;
            return this;
        }
        /** @param val время
         *  @return builder */
        public NotificationDraftBuilder createdAt(final LocalDateTime val) {
            this.createdAt = val;
            return this;
        }
        /** @param val приоритет
         *  @return builder */
        public NotificationDraftBuilder priority(
            final NotificationPriority val) {
            this.priority = val;
            return this;
        }

        /** @return черновик */
        public NotificationDraft build() {
            if (channel == null) {
                throw new NotificationValidationException(
                    "Канал обязателен");
            }
            if (recipient == null || recipient.isBlank()) {
                throw new NotificationValidationException("Получатель");
            }
            if (text == null || text.trim().isEmpty()) {
                throw new NotificationValidationException("Текст не пустой");
            }
            if (createdAt == null) {
                throw new NotificationValidationException("Время обязательно");
            }
            if (priority == null) {
                throw new NotificationValidationException(
                    "Приоритет обязателен");
            }
            return new NotificationDraft(channel, recipient,
                text, createdAt, priority);
        }
    }
}
