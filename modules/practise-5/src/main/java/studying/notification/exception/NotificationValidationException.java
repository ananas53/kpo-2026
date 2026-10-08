package studying.notification.exception;

/** Ошибка валидации. */
public final class NotificationValidationException extends RuntimeException {
    /** @param message сообщение */
    public NotificationValidationException(final String message) {
        super(message);
    }
}
