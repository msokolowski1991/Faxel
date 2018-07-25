package faxel;

/**
 * Creates FaxelParser instance.
 */
public class FaxelFactory {
    /**
     * Creates default FaxelParser instance
     * @param clazz destination class
     * @param <D> destination type
     * @return new FaxelParser instance
     */
    static <D> FaxelParser create(Class<D> clazz) {
        return new DefaultParser<>(clazz);
    }
}
