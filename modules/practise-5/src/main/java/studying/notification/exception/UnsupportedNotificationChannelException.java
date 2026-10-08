package studying.notification.exception;

/** Ошибка канала. */
public final class UnsupportedNotificationChannelException
    extends RuntimeException {
    /** @param message сообщение */
    public UnsupportedNotificationChannelException(final String message) {
        super(message);
    }
}
