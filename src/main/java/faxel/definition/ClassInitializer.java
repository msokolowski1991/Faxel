package faxel.definition;

import static java.lang.String.format;

final class ClassInitializer {

    private ClassInitializer() {
        throw new RuntimeException("You should not create instance of this class");
    }

    static <T> T createSilently(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable cause) {
            throw new IllegalArgumentException(
                    format("Cold not create object of class %s. Probably it does not have public non args constructor", clazz.getName()), cause
            );
        }
    }
}
